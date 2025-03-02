package com.rnyd.rnyd.exceptionHandler;

import java.util.Date;

public class ErrorResponse {
    private String errorMsg;

    public ErrorResponse(String errorMsg, Date timestamp, String details) {
        this.errorMsg = errorMsg;
        this.timestamp = timestamp;
        this.details = details;
    }

    private Date timestamp;

    private String details;

}
