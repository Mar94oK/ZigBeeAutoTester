package com.zigbee.zigbeeautotester.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ZigbeeTestPreset {

    @JsonProperty("expected_serial_port")
    private String expectedSerialPort = "Port-Not-Set";
    @JsonProperty("log_test_patterns")
    private List<LogExpectedPattern> logTestPatterns = Stream.of(new LogExpectedPattern("tauren", StringExpectedCondition.SHOULD_BE_PRESENT)).collect(Collectors.toList());

}
