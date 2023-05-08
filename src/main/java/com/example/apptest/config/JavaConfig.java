package com.example.apptest.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import com.example.apptest.profile.DevProfile;
import com.example.apptest.profile.ProductionProfile;
import com.example.apptest.profile.SystemProfile;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(
            value = "example.profile.dev",
            havingValue = "true",
            matchIfMissing = true
    )
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            value = "example.profile.dev",
            havingValue = "false"
    )
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
