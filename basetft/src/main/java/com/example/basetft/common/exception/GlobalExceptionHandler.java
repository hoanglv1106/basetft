package com.example.basetft.common.exception;

import com.example.basetft.common.enums.ErrorCode;
import com.example.basetft.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        Locale currentLocale = LocaleContextHolder.getLocale();

        String localizedMessage = messageSource.getMessage(errorCode.getMessageKey(), null, currentLocale);

        ApiResponse<Object> apiResponse = ApiResponse.error(errorCode.getCode(), localizedMessage);

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

   // Bắt lỗi Validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ApiResponse<Object> apiResponse = ApiResponse.error(400, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //  Bắt toàn bộ các lỗi rác/hệ thống còn lại
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(Exception exception) {
        log.error("Serious system error: ", exception);

        ApiResponse<Object> apiResponse = ApiResponse.error(
                ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage()
        );
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode()).body(apiResponse);
    }
}