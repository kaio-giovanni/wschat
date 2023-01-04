package com.example.wschat;

import com.example.wschat.utils.PropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WschatApplication {

	public static void main(String[] args) {
		PropertiesUtils.loadByEnv();
		SpringApplication.run(WschatApplication.class, args);
	}

}
