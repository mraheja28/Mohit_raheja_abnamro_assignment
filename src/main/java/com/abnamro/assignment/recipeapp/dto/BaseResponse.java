package com.abnamro.assignment.recipeapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BaseResponse {

    private String code;
    private String message;
    private String trace;
    @JsonIgnore
    private String userId;

    public BaseResponse() {
    }

    public BaseResponse(String message, String trace) {
        this.message = message;
        this.trace = trace;
    }
}
