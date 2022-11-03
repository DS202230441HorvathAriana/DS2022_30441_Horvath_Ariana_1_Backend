package com.example.energyutility.repository;

import com.example.energyutility.model.Consumption;

import java.time.LocalDate;

import java.util.List;

public interface ConsumptionRepository extends AbstractRepository<Consumption>{

    List<Consumption> findAllByMeteringDevice_MeteringDeviceIdAndDate(Long deviceId, LocalDate date);
}
