package com.okanciftci.cukatify;

import com.okanciftci.cukatify.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CukatifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CukatifyApplication.class, args);
    }

}
