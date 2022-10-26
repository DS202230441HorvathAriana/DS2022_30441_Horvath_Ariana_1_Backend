package com.example.energyutility.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumption")
public class Consumption {

    @Id
    @Column(name = "consumption_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumptionId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "energy_consumption")
    private float energyConsumption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metering_device_id")
    private MeteringDevice meteringDevice;

    public Consumption(Long consumptionId, LocalDateTime timestamp, float energyConsumption, MeteringDevice meteringDevice) {
        this.consumptionId = consumptionId;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

