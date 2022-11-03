package com.example.energyutility.controller;

import com.example.energyutility.dto.MessageDTO;
import com.example.energyutility.dto.MeteringDeviceDTO;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping(value = "/devices")
    ResponseEntity<MessageDTO> saveMeteringDevice(@RequestBody MeteringDeviceDTO deviceDTO) {
        Long id = deviceService.save(deviceDTO);
        if (id == 0L)
            return new ResponseEntity<>(new MessageDTO(false, "Associated client not existent!"), HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(new MessageDTO(true, "Device successfully added!"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/device/{id}")
    ResponseEntity<MeteringDeviceDTO> findById(@PathVariable Long id) {
        MeteringDeviceDTO deviceDTO = deviceService.findById(id);
        if (deviceDTO != null)
            return new ResponseEntity<>(deviceDTO, HttpStatus.FOUND);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/devices")
    ResponseEntity<List<MeteringDeviceDTO>> findAll() {
        List<MeteringDeviceDTO> devices = deviceService.findAll();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping(value = "/devices/{username}")
    ResponseEntity<List<MeteringDeviceDTO>> findAllByClient(@PathVariable String username) {
        List<MeteringDeviceDTO> devices = deviceService.findAllByClient(username);
        if (devices != null)
            return new ResponseEntity<>(devices, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/devices/{id}")
    ResponseEntity<MessageDTO> updateDevice(@PathVariable Long id, @RequestBody MeteringDeviceDTO deviceDTO) {
        MeteringDevice updatedDevice = deviceService.updateDevice(id, deviceDTO);
        if (updatedDevice != null)
            return new ResponseEntity<>(new MessageDTO(true, "Device successfully updated."), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageDTO(false,"Associated client not existent!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/devices/{id}")
    ResponseEntity<MessageDTO> deleteDevice(@PathVariable Long id) {
        if (deviceService.deleteDevice(id))
            return new ResponseEntity<>(new MessageDTO(true, "Device successfully deleted."), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageDTO(false,"Device not existent!"), HttpStatus.NOT_FOUND);
    }
}
