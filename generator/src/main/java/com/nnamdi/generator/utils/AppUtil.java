package com.nnamdi.generator.utils;

import com.nnamdi.generator.domain.response.AppResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum AppUtil {
    AppUtil() {

    };

    public static AppResponse buildAppResponse(String message, boolean success, Object data, Object error,
                                               int statusCode) {
        return AppResponse.builder()
                .data(data)
                .error(error)
                .message(message)
                .success(success)
                .status(statusCode)
                .build();
    }
}
