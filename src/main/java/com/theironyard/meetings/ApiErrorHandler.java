package com.theironyard.meetings;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex) {
        ErrorDetails details = new ErrorDetails("InvalidData", "database constraint disallows this data");
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException ex) {
        ErrorDetails details = new ErrorDetails("AccessDenied", "Your role does not have access to this resource.");
        return new ResponseEntity<Object>(details, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ReservationSecurityException.class)
    public ResponseEntity<?> reservationSecurityException(ReservationSecurityException ex) {
        ErrorDetails details = new ErrorDetails("AccessDenied",
                "You are trying to change a reservation you do not own.");
        return new ResponseEntity<Object>(details, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ReservationOverlapException.class)
    public ResponseEntity<?> reservationOverlapException(ReservationOverlapException ex) {
        ErrorDetails details = new ErrorDetails("ReservationOverlap",
                "This reservation overlaps with an existing reservation.");
        return new ResponseEntity<Object>(details, HttpStatus.CONFLICT);
    }
}

class ErrorDetails {
    final Boolean error = true;
    String type;
    String message;

    ErrorDetails(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
