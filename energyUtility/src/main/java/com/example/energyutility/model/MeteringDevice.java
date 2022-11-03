package com.example.energyutility.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device")
public class MeteringDevice {

    @Id
    @Column(name = "metering_device_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meteringDeviceId;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "max_hourly_consumption")
    private float maxHourlyEnergyConsumption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User client;

    @OneToMany(mappedBy = "meteringDevice", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Consumption> consumptions;

    public MeteringDevice(String description, String address, float maxHourlyEnergyConsumption, User client) {
        this.description = description;
        this.address = address;
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
        this.client = client;
    }

    public MeteringDevice() {

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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
}
