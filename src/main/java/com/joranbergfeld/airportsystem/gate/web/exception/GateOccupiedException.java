package com.joranbergfeld.airportsystem.gate.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GateOccupiedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Gate is already occupied.";

    public GateOccupiedException() {
        super(DEFAULT_MESSAGE);
    }
}
