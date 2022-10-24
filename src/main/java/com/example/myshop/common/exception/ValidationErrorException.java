package com.example.myshop.common.exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorException extends RuntimeException {

    private List<ValidationError> errors;
}
