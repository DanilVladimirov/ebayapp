package com.ebayapp.ebayapp;

import java.util.Base64;

public class Settings {
    final public String clientId = "DaniilVl-shopik-SBX-e07af6809-1b018aff";
    final public String clientSecret = "SBX-07af68097f0a-204d-4055-95c6-9dcc";
    final public String locale = "en_US";
    final public String redirectUri = "Daniil_Vladimir-DaniilVl-shopik-tomqdkejs";
    final public String scope = (
            "https://api.ebay.com/oauth/api_scope " +
            "https://api.ebay.com/oauth/api_scope/sell.inventory " +
            "https://api.ebay.com/oauth/api_scope/sell.account " +
            "https://api.ebay.com/oauth/api_scope/sell.account.readonly"
    );

    final public String ebayAuthorizeUrl = "https://auth.sandbox.ebay.com/oauth2/authorize";
    final public String ebayTokenUrl = "https://api.sandbox.ebay.com/identity/v1/oauth2/token";

    final public String ebayCreateItemsUrl = "https://api.sandbox.ebay.com/sell/inventory/v1/bulk_create_or_replace_inventory_item";

    final public String ebayInventoryItemUrl = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";
    final public String ebayCreateOffersUrl = "https://api.sandbox.ebay.com/sell/inventory/v1/bulk_create_offer";

    final public String ebayOfferUrl = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/";

    final public String authToken = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

}
