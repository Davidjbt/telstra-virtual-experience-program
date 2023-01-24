package controller;

import json.ActuatorResult;
import json.SIMCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIMCardController {

    @PostMapping("/activeSIM")
    public void activateSIMCard(@RequestBody SIMCard simCard) {
        ActuatorResult actuatorResult = new RestTemplateBuilder().build().postForObject("http://localhost:8444/actuate", simCard, ActuatorResult.class);

        System.out.println(actuatorResult);
    }
}
