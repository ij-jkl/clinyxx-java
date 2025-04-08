package org.clinyx.clinyxbackend.Dtos.CommonDtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObjectJsonDto {

    @JsonProperty("response")
    private Object response;

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;
}
