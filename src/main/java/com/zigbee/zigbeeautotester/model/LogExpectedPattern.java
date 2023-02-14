package com.zigbee.zigbeeautotester.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogExpectedPattern {

    @JsonProperty("expected_string")
    private String expectedString;
    private StringExpectedCondition condition;



}
