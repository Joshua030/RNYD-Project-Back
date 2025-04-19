package com.rnyd.rnyd.dto.stripe;

public class StripePaymentLinkDTO {
    private String id;
    private String url;

    public StripePaymentLinkDTO(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
