package com.example.energyutility.dto;

public class MeteringDeviceDTO {

    private Long meteringDeviceId;
    private String description;
    private String address;
    private float maxHourlyEnergyConsumption;
    private String clientUsername;

    public MeteringDeviceDTO(Long meteringDeviceId, String description, String address, float maxHourlyEnergyConsumption, String clientUsername) {
        this.meteringDeviceId = meteringDeviceId;
        this.description = description;
        this.address = address;
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
        this.clientUsername = clientUsername;
    }

    public Long getMeteringDeviceId() {
        return meteringDeviceId;
    }

    public void setMeteringDeviceId(Long meteringDeviceId) {
        this.meteringDeviceId = meteringDeviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMaxHourlyEnergyConsumption() {
        return maxHourlyEnergyConsumption;
    }

    public void setMaxHourlyEnergyConsumption(float maxHourlyEnergyConsumption) {
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
