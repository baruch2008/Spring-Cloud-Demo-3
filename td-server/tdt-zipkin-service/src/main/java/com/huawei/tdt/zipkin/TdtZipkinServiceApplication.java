package com.huawei.tdt.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class TdtZipkinServiceApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(TdtZipkinServiceApplication.class, args);
	}
}
