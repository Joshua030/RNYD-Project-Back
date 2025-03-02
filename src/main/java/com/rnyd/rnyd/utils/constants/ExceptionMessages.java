package com.rnyd.rnyd.utils.constants;

public enum ExceptionMessages {

    USER_EMAIL_ALREADY_EXISTS("Este correo ya está en uso.");


    private final String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
