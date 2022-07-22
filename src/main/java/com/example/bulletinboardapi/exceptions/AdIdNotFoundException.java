package com.example.bulletinboardapi.exceptions;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class AdIdNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Ad (id=%s) has not been found";

    private final long adId;

    public AdIdNotFoundException(long adId) {
        super(MESSAGE_FORMAT.formatted(adId));
        this.adId = adId;
    }
}
