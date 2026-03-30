package com.example.basetft.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // system error
    UNCATEGORIZED_EXCEPTION(500, "error.uncategorized",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "error.invalid.key", HttpStatus.BAD_REQUEST),

    // user error
    USER_NOT_FOUND(404, "error.user.notfound", HttpStatus.NOT_FOUND),
    USER_EXISTS(400, "error.user.existed", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "error.unauthenticated", HttpStatus.UNAUTHORIZED);

    private final int code;
    private final String messageKey;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String messageKey, HttpStatus httpStatus) {
        this.code = code;
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return messageKey;
    }

    public int getStatusCode() {
        return code;
    }
}
