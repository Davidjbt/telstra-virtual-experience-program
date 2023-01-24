package controller;

import entity.SimCard;
import json.ActuatorResult;
import json.SIMCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.SimCardService;

@RestController
public class SIMCardController {

    private final SimCardService simCardService;
    private final RestTemplate restTemplate;

    public SIMCardController(SimCardService simCardService) {
        this.simCardService = simCardService;
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @PostMapping("/activateSIM")
    public void activateSIMCard(@RequestBody SIMCard simCard) {
        ActuatorResult actuatorResult = restTemplate.postForObject("http://localhost:8444/actuate", simCard, ActuatorResult.class);

        SimCard simCardEntity = new SimCard(simCard.getIccid(), simCard.getCustomerEmail(), actuatorResult.isSuccess());

        simCardService.save(simCardEntity);

        System.out.println(actuatorResult);
    }

    @GetMapping("/querySIM")
    public SimCard getSIM(@RequestParam Long id) {
        return simCardService.findById(id);
    }
}
