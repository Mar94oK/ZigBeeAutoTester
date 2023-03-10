package com.zigbee.zigbeeautotester.config;

import com.zigbee.zigbeeautotester.model.ZigbeeTestPreset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ZigbeeTestPresetConstructor {

    private final ZigbeeTestPreset preset = new ZigbeeTestPreset();

    @Bean
    @Scope("singleton")
    public ZigbeeTestPreset getPreset() {
        return preset;
    }


}