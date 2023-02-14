package com.zigbee.zigbeeautotester.service;

import com.fazecast.jSerialComm.SerialPort;
import com.zigbee.zigbeeautotester.model.ZigbeeTestPreset;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class ZigBeeAutoTesterService {

    private final ZigbeeTestPreset testPreset;

    public ZigBeeAutoTesterService(ZigbeeTestPreset testPreset) {
        this.testPreset = testPreset;
    }

    public void StartTest() {

        var future = CompletableFuture.supplyAsync(() -> {
            try {
                return ZigBeeHubLogsReader();
            } catch (IOException e) {
                return 0;
            }
        }).thenApply(result -> {
            log.info("Port was closed");
            return 0;
        });

    }


    private double ZigBeeHubLogsReader() throws IOException {

        SerialPort comPort = null;
        for (var port : SerialPort.getCommPorts()) {
            log.info("Current Port: {} Preset Port: {}", port.getSystemPortName(), testPreset.getExpectedSerialPort());
            if (port.getSystemPortName().equals(testPreset.getExpectedSerialPort())) {
                comPort = port;
                break;
            }
        }

        if (comPort == null)
            return 0;

        log.info("Trying to open");

        comPort.setComPortParameters(115200, 8, 1, 0);

        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        comPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);

        double readBytes = 0;
        try (InputStream in = comPort.getInputStream()) {
            StringBuilder current = new StringBuilder();
            while (true) {
                var ch = (char) in.read();
                current.append(ch);
                if (ch == '\n') {
                    log.info(current.toString());
                    current = new StringBuilder();
                }
                readBytes++;
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            comPort.closePort();
        }

        return readBytes;
    }

}
