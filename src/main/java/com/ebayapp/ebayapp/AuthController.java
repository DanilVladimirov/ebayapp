package com.ebayapp.ebayapp;

import com.ebayapp.ebayapp.schemas.AuthUrlSchema;
import com.ebayapp.ebayapp.schemas.GetAccessTokenSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.ebayapp.ebayapp.helpers.Requests.getFormDataAsString;
import static com.ebayapp.ebayapp.helpers.Requests.makeRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public Settings settings = new Settings();

    @GetMapping(value="get-auth-code-url/", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthUrlSchema getAuthCodeUrl() {
        String params = String.format(
                "?client_id=%s&locale=%s&redirect_uri=%s&response_type=code&scope=%s",
                settings.clientId, settings.locale, settings.redirectUri, settings.scope
        );
        return new AuthUrlSchema(settings.ebayAuthorizeUrl + params);
    }

    @PostMapping(value="access-token", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAccessToken(@RequestBody GetAccessTokenSchema body) throws IOException, InterruptedException {

        Map<String, String> formData = new HashMap<>();

        formData.put("code", URLDecoder.decode(body.code, StandardCharsets.UTF_8));
        formData.put("grant_type", "authorization_code");
        formData.put("scope", settings.scope);
        formData.put("redirect_uri", settings.redirectUri);
        formData.put("client_id", settings.clientId);

        HttpResponse<String> response = makeRequest(
                settings.ebayTokenUrl,
                HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)),
                "POST",
                "Authorization", "Basic " + settings.authToken,
                "Content-Type", "application/x-www-form-urlencoded"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }

}
