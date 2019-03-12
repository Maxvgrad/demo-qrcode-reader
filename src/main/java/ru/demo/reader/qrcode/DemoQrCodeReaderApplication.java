package ru.demo.reader.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoQrCodeReaderApplication {

	public static final String APP_ROOT_PATH = "/v1/app/";

	public static void main(String[] args) {
		SpringApplication.run(DemoQrCodeReaderApplication.class, args);
	}

}
