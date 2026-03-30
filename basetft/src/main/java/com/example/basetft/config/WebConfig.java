package com.example.basetft.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

                // CẤU HÌNH DOMAIN ĐƯỢC PHÉP GỌI API

                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:3001"
                )

                // Các HTTP Method được phép gọi
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")

                // Các Header được phép gửi lên
                .allowedHeaders("Authorization", "Content-Type", "Accept-Language")

                // Cho phép gửi kèm Cookie/Thông tin xác thực
                .allowCredentials(true)

                // Thời gian cache cấu hình CORS trên trình duyệt
                .maxAge(3600); // 1 giờ
    }
    }

