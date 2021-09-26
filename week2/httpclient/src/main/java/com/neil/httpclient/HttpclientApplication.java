package com.neil.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HttpclientApplication {

	public static void main(String[] args) {
		String response = "";
		SpringApplication.run(HttpclientApplication.class, args);
		try {
			 response = OkHttpUtils.getAsString("http://localhost:8801");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("connect localhost,the reponse string is: " + response);
	}

}
