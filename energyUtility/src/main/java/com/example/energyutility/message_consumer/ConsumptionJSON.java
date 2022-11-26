package com.example.energyutility.message_consumer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsumptionJSON {
    private String date;
    private String time;
    private float energyConsumption;
    private long meteringDeviceId;

    public ConsumptionJSON(String date, String time, float energyConsumption, long meteringDeviceId) {
        this.date = date;
        this.time = time;
        this.energyConsumption = energyConsumption;
        this.meteringDeviceId = meteringDeviceId;
    }

    public ConsumptionJSON() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(float energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public long getMeteringDeviceId() {
        return meteringDeviceId;
    }

    public void setMeteringDeviceId(long meteringDeviceId) {
        this.meteringDeviceId = meteringDeviceId;
    }
}
