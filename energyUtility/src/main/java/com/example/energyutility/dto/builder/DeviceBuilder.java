package com.example.energyutility.dto.builder;

import com.example.energyutility.dto.MeteringDeviceDTO;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.model.User;

public class DeviceBuilder {

    public DeviceBuilder() {
    }

    public static MeteringDeviceDTO toDeviceDTO (MeteringDevice meteringDevice) {
        return new MeteringDeviceDTO(meteringDevice.getMeteringDeviceId(),
                meteringDevice.getDescription(),
                meteringDevice.getAddress(),
                meteringDevice.getMaxHourlyEnergyConsumption(),
                meteringDevice.getClient().getUsername());
    }

    public static MeteringDevice toDevice (MeteringDeviceDTO meteringDeviceDTO, User client) {
        return new MeteringDevice(meteringDeviceDTO.getDescription(),
                meteringDeviceDTO.getAddress(),
                meteringDeviceDTO.getMaxHourlyEnergyConsumption(),
                client);
    }
}
