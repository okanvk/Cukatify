package com.okanciftci.cukatify;

import com.okanciftci.cukatify.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CukatifyApplication {


    public static void main(String[] args) {
        SpringApplication.run(CukatifyApplication.class, args);
    }

}
