package com.example.basetft.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    //  Trả về thành công
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(200) // Hoặc bạn có thể dùng HttpStatus.OK.value()
                .message("Success")
                .data(data)
                .build();
    }

    //  Trả về thành công NHƯNG không có dữ liệu
    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .code(200)
                .message("Success")
                .build();
    }

    //  Trả về thất bại
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
