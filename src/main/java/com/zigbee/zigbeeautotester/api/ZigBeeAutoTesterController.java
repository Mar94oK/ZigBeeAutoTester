package com.zigbee.zigbeeautotester.api;

import com.fazecast.jSerialComm.SerialPort;
import com.zigbee.zigbeeautotester.model.ZigbeeTestPreset;
import com.zigbee.zigbeeautotester.service.ZigBeeAutoTesterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class ZigBeeAutoTesterController {

    private final ZigbeeTestPreset testPreset;
    private final ZigBeeAutoTesterService zigBeeAutoTesterService;

    public ZigBeeAutoTesterController(ZigbeeTestPreset testPreset, ZigBeeAutoTesterService zigBeeAutoTesterService) {
        this.testPreset = testPreset;
        this.zigBeeAutoTesterService = zigBeeAutoTesterService;
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

    @PostMapping("/api/v1.0/zat/presets/port")
    public boolean setZigBeeHubLogPort(@RequestParam("port_name") String portName) {

        List<String> result = new ArrayList<>();

        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port: ports) {
            result.add(port.getSystemPortName());
        }

        log.info("Port Name: {}",  portName);

        if (result.contains(portName)) {
            testPreset.setExpectedSerialPort(portName);
            return true;
        }

        return false;
    }

    @PostMapping("/api/v1.0/zat/start")
    public ZigbeeTestPreset startTests() {

        zigBeeAutoTesterService.StartTest();
        return testPreset;

    }


}
