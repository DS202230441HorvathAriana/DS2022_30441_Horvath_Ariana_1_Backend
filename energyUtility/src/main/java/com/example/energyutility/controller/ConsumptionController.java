package com.example.energyutility.controller;

import com.example.energyutility.dto.ConsumptionDTO;

import com.example.energyutility.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping(value = "/consumptions")
    ResponseEntity<List<ConsumptionDTO>> findAll() {
        List<ConsumptionDTO> consumptions = consumptionService.findAll();
        return new ResponseEntity<>(consumptions, HttpStatus.OK);
    }

    @GetMapping(value = "/consumptions/{id}/{date}")
    ResponseEntity<List<ConsumptionDTO>> findAllByDeviceAndDate(@PathVariable Long id, @PathVariable LocalDate date) {
        List<ConsumptionDTO> consumptions = consumptionService.findAllByDeviceAndDate(id, date);
        return new ResponseEntity<>(consumptions, HttpStatus.OK);
    }
}
