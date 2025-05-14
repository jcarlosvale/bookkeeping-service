package com.bookkeeping.interfaces.rest.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        final List<Map<String, String>> errors = exception.getConstraintViolations()
                .stream()
                .map(violation -> Map.of(
                        "field", extractSimpleFieldName(violation),
                        "message", violation.getMessage()
                ))
                .collect(Collectors.toList());

        final Map<String, Object> response = Map.of(
                "error", "Validation failed",
                "code", 400,
                "details", errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }

    private String extractSimpleFieldName(final ConstraintViolation<?> violation) {
        final String[] parts = violation.getPropertyPath().toString().split("\\.");
        return parts[parts.length - 1];
    }
}

