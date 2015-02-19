package com.sample.dto;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class StepDto {

    enum Type {CANCEL, COPY, CREATE, TRANSFER, PAY}

    private Type type;

    public static List<StepDto> buildAll() {
        return EnumSet.allOf(Type.class)
                .stream()
                .map(StepDto::of)
                .collect(Collectors.toList());
    }

    public static StepDto of(Type type) {
        StepDto stepDto = new StepDto();
        stepDto.setType(type);

        return stepDto;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
