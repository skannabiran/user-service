package com.maveric.techhub.user.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ServiceResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object response;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}
