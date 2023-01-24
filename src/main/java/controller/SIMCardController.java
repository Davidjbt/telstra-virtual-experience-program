package controller;

import entity.SimCard;
import json.ActuatorResult;
import json.SIMCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import service.SimCardService;

@RestController
public class SIMCardController {

    private final SimCardService simCardService;

    public SIMCardController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @PostMapping("/activeSIM")
    public void activateSIMCard(@RequestBody SIMCard simCard) {
        ActuatorResult actuatorResult = new RestTemplateBuilder().build().postForObject("http://localhost:8444/actuate", simCard, ActuatorResult.class);

        SimCard simCardEntity = new SimCard(simCard.getIccid(), simCard.getCustomerEmail(), actuatorResult.isSuccess());

        simCardService.save(simCardEntity);

        System.out.println(actuatorResult);
    }

    @GetMapping("/querySIM")
    public SimCard getSIM(@RequestParam Long id) {
        return simCardService.findById(id);
    }
}
