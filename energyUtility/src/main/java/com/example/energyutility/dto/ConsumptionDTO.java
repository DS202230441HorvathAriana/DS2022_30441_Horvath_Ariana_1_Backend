package com.example.energyutility.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsumptionDTO {

    private LocalDate date;
    private LocalTime time;
    private float energyConsumption;
    private long meteringDeviceId;

    public ConsumptionDTO(LocalDate date, LocalTime time, float energyConsumption, long meteringDeviceId) {
        this.date = date;
        this.time = time;
        this.energyConsumption = energyConsumption;
        this.meteringDeviceId = meteringDeviceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
