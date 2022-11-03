package com.example.energyutility.service;

import com.example.energyutility.dto.ConsumptionDTO;
import com.example.energyutility.dto.builder.ConsumptionBuilder;
import com.example.energyutility.model.Consumption;
import com.example.energyutility.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public List<ConsumptionDTO> findAll() {
        List<ConsumptionDTO> consumtionDTOS = consumptionRepository.findAll()
                .stream()
                .map(ConsumptionBuilder::toConsumptionDTO)
                .collect(Collectors.toList());
        return consumtionDTOS;
    }

    public List<ConsumptionDTO> findAllByDeviceAndDate(Long deviceId, LocalDate date) {
        List<ConsumptionDTO> consumptionDTOS =
                consumptionRepository.findAllByMeteringDevice_MeteringDeviceIdAndDate(deviceId, date)
                        .stream()
                        .map(ConsumptionBuilder::toConsumptionDTO)
                        .collect(Collectors.toList());
        return consumptionDTOS;
    }
}
