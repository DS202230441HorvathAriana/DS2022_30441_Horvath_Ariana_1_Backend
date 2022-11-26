package com.example.energyutility.service;

import com.example.energyutility.dto.ConsumptionDTO;
import com.example.energyutility.dto.MeteringDeviceDTO;
import com.example.energyutility.dto.builder.ConsumptionBuilder;
import com.example.energyutility.dto.builder.DeviceBuilder;
import com.example.energyutility.model.Consumption;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.model.User;
import com.example.energyutility.repository.ConsumptionRepository;
import com.example.energyutility.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;
    @Autowired
    private DeviceRepository deviceRepository;

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
    public Long save (ConsumptionDTO consumptionDTO) {
        Optional<MeteringDevice> device = deviceRepository.findById(consumptionDTO.getMeteringDeviceId());

        if (device.isPresent()) {
            Consumption consumption = ConsumptionBuilder.toConsumption(consumptionDTO, device.get());
            consumptionRepository.save(consumption);
            return consumption.getConsumptionId();
        } else
            return 0L;
    }
}
