package com.example.energyutility.message_consumer;

import com.example.energyutility.dto.ConsumptionDTO;
import com.example.energyutility.dto.TextMessageDTO;
import com.example.energyutility.model.HourlyConsumptionPart;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.service.ConsumptionService;
import com.example.energyutility.service.DeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static com.example.energyutility.message_consumer.MQConfig.*;

@Component
public class MessageConsumer {
    @Autowired
    private ConsumptionService consumptionService;
    @Autowired
    private DeviceService deviceService;
    private HashMap<Long, HourlyConsumptionPart> intermediateConsumptions = new HashMap<>();

    @Autowired
    SimpMessagingTemplate template;

    @RabbitListener(queues = QUEUE)
    public void consumer(String consumptionJSON) throws JsonProcessingException {
        System.out.println(consumptionJSON);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        ObjectMapper mapper = new ObjectMapper();
        ConsumptionJSON consumption = mapper.readValue(consumptionJSON, ConsumptionJSON.class);
        long deviceId = consumption.getMeteringDeviceId();

        if (intermediateConsumptions.containsKey(deviceId)) {
            intermediateConsumptions.get(deviceId).setValueSum(
                    intermediateConsumptions.get(deviceId).getValueSum() + consumption.getEnergyConsumption()
            );
            intermediateConsumptions.get(deviceId).setCounterMinutes(
                    intermediateConsumptions.get(deviceId).getCounterMinutes() + 1
            );

            if (intermediateConsumptions.get(deviceId).getCounterMinutes() == 6){
                LocalDate date = LocalDate.parse(consumption.getDate(), formatterDate);
                LocalTime time = LocalTime.parse(consumption.getTime(), formatterTime);
                ConsumptionDTO consumptionDTO = new ConsumptionDTO(date,
                        time,
                        intermediateConsumptions.get(deviceId).getValueSum(),
                        deviceId);
                long id = consumptionService.save(consumptionDTO);
                System.out.println("Saved consumption with id: " + id);
                intermediateConsumptions.get(deviceId).setValueSum(0);
                intermediateConsumptions.get(deviceId).setCounterMinutes(0);
                MeteringDevice associatedDevice = deviceService.findDeviceById(consumption.getMeteringDeviceId());

                if (associatedDevice.getMaxHourlyEnergyConsumption() < consumptionDTO.getEnergyConsumption()) {
                    System.out.println("Your metering device with id "+associatedDevice.getMeteringDeviceId() +
                            " registered an hourly consumption greater than the threshold. Value: "+
                            consumptionDTO.getEnergyConsumption());
                    template.convertAndSend("/topic/message",
                            new TextMessageDTO("Your metering device with id "+associatedDevice.getMeteringDeviceId() +
                                    " registered an hourly consumption greater than the threshold. Value: "+
                                    consumptionDTO.getEnergyConsumption(), consumptionDTO.getMeteringDeviceId(),
                                    associatedDevice.getClient().getUsername()));
                }
            }
        } else {
            intermediateConsumptions.put(consumption.getMeteringDeviceId(),
                    new HourlyConsumptionPart(consumption.getEnergyConsumption(), 1));
        }
    }
}
