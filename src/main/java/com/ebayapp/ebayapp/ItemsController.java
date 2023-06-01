package com.ebayapp.ebayapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.ebayapp.ebayapp.helpers.Requests.makeRequest;

@RestController
@RequestMapping("/inventory")
public class ItemsController {

    public Settings settings = new Settings();

    @PostMapping(value="create-items", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createItems(
            @RequestBody String body,
            @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        HttpResponse<String> response = makeRequest(
                settings.ebayCreateItemsUrl,
                HttpRequest.BodyPublishers.ofString(body),
                "POST",
                "Authorization", authToken,
                "Content-Type", "application/json"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }

    @PostMapping(value="create-offers", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createOffers(
            @RequestBody String body,
            @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        HttpResponse<String> response = makeRequest(
                settings.ebayCreateOffersUrl,
                HttpRequest.BodyPublishers.ofString(body),
                "POST",
                "Authorization", authToken,
                "Content-Type", "application/json"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }

    @PutMapping(value="update-offer/{offerId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> updateOffer(
            @PathVariable String offerId,
            @RequestBody String body,
            @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {

        HttpResponse<String> response = makeRequest(
                settings.ebayOfferUrl + offerId,
                HttpRequest.BodyPublishers.ofString(body),
                "PUT",
                "Authorization", authToken,
                "Content-Type", "application/json",
                "Content-Language", "en-US"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }

    @PostMapping(value="withdraw-offer/{offerId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> withdrawOffer(
            @PathVariable String offerId,
            @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {

        HttpResponse<String> response = makeRequest(
                settings.ebayOfferUrl + offerId + "/withdraw",
                HttpRequest.BodyPublishers.noBody(),
                "POST",
                "Authorization", authToken,
                "Content-Type", "application/json",
                "Content-Language", "en-US"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }

    @PutMapping(value="update-item/{sku}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> updateItem(
            @PathVariable String sku,
            @RequestBody String body,
            @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        HttpResponse<String> response = makeRequest(
                settings.ebayInventoryItemUrl + sku,
                HttpRequest.BodyPublishers.ofString(body),
                "PUT",
                "Authorization", authToken,
                "Content-Type", "application/json",
                "Content-Language", "en-US"
        );

        return new ResponseEntity<String>(
                response.body(),
                HttpStatus.valueOf(response.statusCode())
        );
    }
}
