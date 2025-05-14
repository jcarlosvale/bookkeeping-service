package com.bookkeeping.interfaces.rest.exception;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Map;

@Provider
public class DatabaseConstraintViolationExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(final PersistenceException exception) {
        final Throwable cause = exception.getCause();
        if (cause instanceof ConstraintViolationException dbViolation) {
            final String constraintName = dbViolation.getConstraintName();
            final String message = resolveMessage(constraintName);

            final Map<String, Object> response = Map.of(
                    "error", "Database constraint violation",
                    "code", 409,
                    "details", Map.of(
                            "constraint", constraintName,
                            "message", message
                    )
            );

            return Response.status(Response.Status.CONFLICT)
                    .entity(response)
                    .build();
        }

        final Map<String, Object> genericResponse = Map.of(
                "error", "Persistence error",
                "code", 500,
                "details", exception.getMessage()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(genericResponse)
                .build();
    }

    private String resolveMessage(final String constraintName) {
        return switch (constraintName) {
            case "vehicle_pkey" -> "ID already exists";
            default -> "A database constraint was violated";
        };
    }
}
