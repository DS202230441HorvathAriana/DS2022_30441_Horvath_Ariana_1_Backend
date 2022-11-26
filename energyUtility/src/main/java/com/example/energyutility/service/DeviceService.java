package com.example.energyutility.service;

import com.example.energyutility.dto.MeteringDeviceDTO;
import com.example.energyutility.dto.builder.DeviceBuilder;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.model.User;
import com.example.energyutility.repository.DeviceRepository;
import com.example.energyutility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserRepository userRepository;

    public Long save (MeteringDeviceDTO meteringDeviceDTO) {
        User user = userRepository.findByUsername(meteringDeviceDTO.getClientUsername());
        if (user != null) {
            MeteringDevice device = DeviceBuilder.toDevice(meteringDeviceDTO, user);
            deviceRepository.save(device);
            return device.getMeteringDeviceId();
        } else
            return 0L;
    }

    public MeteringDeviceDTO findById(Long id) {
        MeteringDevice device = deviceRepository.findByMeteringDeviceId(id);
        if (device != null) {
            return DeviceBuilder.toDeviceDTO(device);
        } else
            return null;
    }

    public MeteringDevice findDeviceById(Long id) {
        return deviceRepository.findByMeteringDeviceId(id);
    }

    public List<MeteringDeviceDTO> findAll() {
        List<MeteringDeviceDTO> devices = deviceRepository.findAll()
                .stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
        return devices;
    }

    public List<MeteringDeviceDTO> findAllByClient(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<MeteringDeviceDTO> deviceDTOS = deviceRepository.findAllByClient_Username(username)
                    .stream()
                    .map(DeviceBuilder::toDeviceDTO)
                    .collect(Collectors.toList());
            return deviceDTOS;
        } else
            return null;
    }

    public MeteringDevice updateDevice(Long id, MeteringDeviceDTO meteringDeviceDTO) {
        MeteringDevice device = deviceRepository.findByMeteringDeviceId(id);
        if (device != null) {
            if (device.getDescription().compareTo(meteringDeviceDTO.getDescription()) != 0)
                device.setDescription(meteringDeviceDTO.getDescription());

            if (device.getAddress().compareTo(meteringDeviceDTO.getAddress()) != 0)
                device.setAddress(meteringDeviceDTO.getAddress());

            if (device.getMaxHourlyEnergyConsumption() != meteringDeviceDTO.getMaxHourlyEnergyConsumption())
                device.setMaxHourlyEnergyConsumption(meteringDeviceDTO.getMaxHourlyEnergyConsumption());

            if (device.getClient().getUsername().compareTo(meteringDeviceDTO.getClientUsername()) != 0) {
                User client = userRepository.findByUsername(meteringDeviceDTO.getClientUsername());
                if (client != null)
                    device.setClient(client);
                else
                    return null;
            }
            deviceRepository.save(device);
            return device;
        } else
            return null;
    }

    public boolean deleteDevice(Long id) {
        MeteringDevice device = deviceRepository.findByMeteringDeviceId(id);
        if (device != null) {
            deviceRepository.delete(device);
            return true;
        } else
            return false;
    }
}
