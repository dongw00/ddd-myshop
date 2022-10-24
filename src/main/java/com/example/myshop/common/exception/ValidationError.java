package com.example.myshop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {

    private String name;
    private String code;

    public static ValidationError of(String code) {
        return new ValidationError(null, code);
    }

    public static ValidationError of(String name, String code) {
        return new ValidationError(name, code);
    }

    public boolean hasName() {
        return name != null;
    }
}
