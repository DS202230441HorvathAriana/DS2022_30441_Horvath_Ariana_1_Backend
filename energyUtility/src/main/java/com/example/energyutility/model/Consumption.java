package com.example.energyutility.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consumption")
public class Consumption {

    @Id
    @Column(name = "consumption_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumptionId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "energy_consumption")
    private float energyConsumption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metering_device_id")
    private MeteringDevice meteringDevice;

    public Consumption(Long consumptionId, LocalDate date, LocalTime time, float energyConsumption, MeteringDevice meteringDevice) {
        this.consumptionId = consumptionId;
        this.date = date;
        this.time = time;
        this.energyConsumption = energyConsumption;
        this.meteringDevice = meteringDevice;
    }

    public Consumption() {

    }

    public Long getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Long consumptionId) {
        this.consumptionId = consumptionId;
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

    public MeteringDevice getMeteringDevice() {
        return meteringDevice;
    }

    public void setMeteringDevice(MeteringDevice meteringDevice) {
        this.meteringDevice = meteringDevice;
    }
}

