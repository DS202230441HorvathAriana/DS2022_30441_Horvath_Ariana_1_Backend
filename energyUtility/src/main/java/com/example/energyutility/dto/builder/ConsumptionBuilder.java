package com.example.energyutility.dto.builder;

import com.example.energyutility.dto.ConsumptionDTO;
import com.example.energyutility.model.Consumption;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumptionBuilder {
    public ConsumptionBuilder() {
    }

    public static ConsumptionDTO toConsumptionDTO(Consumption consumption) {
        return new ConsumptionDTO(consumption.getDate(),
                consumption.getTime(),
                consumption.getEnergyConsumption(),
                consumption.getMeteringDevice().getMeteringDeviceId());
    }
}
