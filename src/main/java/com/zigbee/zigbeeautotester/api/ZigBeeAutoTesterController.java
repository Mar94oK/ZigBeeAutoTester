package com.zigbee.zigbeeautotester.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ZigBeeAutoTesterController {

    @GetMapping("/api/v1.0/zat/greetings")
    public String sayHello() {

        return "Hello, I am ZigBee Automated Tester!";

    }

}
