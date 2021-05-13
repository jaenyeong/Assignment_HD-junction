package com.hdjunction.exception;

public final class NotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "데이터를 찾을 수 없습니다";

    public NotFoundException() {
        this(ERROR_MESSAGE);
    }

    public NotFoundException(final String errMessage) {
        super(errMessage);
    }
}
