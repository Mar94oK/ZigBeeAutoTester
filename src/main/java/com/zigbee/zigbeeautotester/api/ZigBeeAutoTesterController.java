package com.zigbee.zigbeeautotester.api;

import com.fazecast.jSerialComm.SerialPort;
import com.zigbee.zigbeeautotester.model.ZigbeeTestPreset;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class ZigBeeAutoTesterController {

    private final ZigbeeTestPreset testPreset;

    public ZigBeeAutoTesterController(ZigbeeTestPreset testPreset) {
        this.testPreset = testPreset;
    }

    @GetMapping("/api/v1.0/zat/greetings")
    public String sayHello() {

        return "Hello, I am ZigBee Automated Tester!";

    }


    @GetMapping("/api/v1.0/zat/dev/availablePorts")
    public List<String> getAllCOMPorts() {

        List<String> result = new ArrayList<>();

        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port: ports) {
            result.add(port.getSystemPortName());
        }

        return result;
    }

    @GetMapping("/api/v1.0/zat/dev/presets")
    public ZigbeeTestPreset getPresets() {

        return testPreset;

    }



}
