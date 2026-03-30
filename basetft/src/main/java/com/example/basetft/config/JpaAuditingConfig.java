package com.example.basetft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Trả về tên của người đang thao tác.
        // Hiện tại chưa làm Security Login, nên  fix cứng là "SYSTEM".
        // Sau này làm xong Login, sẽ lấy username từ SecurityContextHolder ra nhét vào đây.
        return () -> Optional.of("SYSTEM");
    }
}