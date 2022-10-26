package com.example.energyutility.repository;

import com.example.energyutility.model.MeteringDevice;

import java.util.List;

public interface DeviceRepository extends AbstractRepository<MeteringDevice>{
    MeteringDevice findByMeteringDeviceId(Long id);
    List<MeteringDevice> findAllByClient_Username(String username);
}
