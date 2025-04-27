package com.rnyd.rnyd.dto.stripe;
import com.stripe.model.checkout.Session;

public class StripeSessionResponse {
    private String id;
    private String url;

    // Constructor, getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StripeSessionResponse(Session session) {
        this.id = session.getId();
        this.url = session.getUrl();
    }
}
