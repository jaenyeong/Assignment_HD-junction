package com.hdjunction.dto;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private T data;

    public CommonResponse(final T data) {
        this.data = data;
    }
}
