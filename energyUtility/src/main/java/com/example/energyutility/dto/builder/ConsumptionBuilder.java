package com.example.energyutility.dto.builder;

import com.example.energyutility.dto.ConsumptionDTO;
import com.example.energyutility.model.Consumption;
import com.example.energyutility.model.MeteringDevice;

public class ConsumptionBuilder {
    public ConsumptionBuilder() {
    }
    public static ConsumptionDTO toConsumptionDTO(Consumption consumption) {
        return new ConsumptionDTO(consumption.getDate(),
                consumption.getTime(),
                consumption.getEnergyConsumption(),
                consumption.getMeteringDevice().getMeteringDeviceId());
    }
    public static Consumption toConsumption(ConsumptionDTO consumptionDTO, MeteringDevice device) {
        return new Consumption(consumptionDTO.getDate(), consumptionDTO.getTime(), consumptionDTO.getEnergyConsumption(), device);
    }
}
