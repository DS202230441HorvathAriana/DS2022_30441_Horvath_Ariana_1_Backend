package com.example.energyutility.dto;

public class TextMessageDTO {

    private String message;
    private long deviceId;
    private String clientUsername;

    public TextMessageDTO(String message, long deviceId, String clientUsername) {
        this.message = message;
        this.deviceId = deviceId;
        this.clientUsername = clientUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}