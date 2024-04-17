package com.ads.adserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class AdServerApplication {

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(5);
		threadPoolTaskScheduler.setThreadNamePrefix("task-scheduler-");
		return threadPoolTaskScheduler;
	}

	public static void main(String[] args) {
		SpringApplication.run(AdServerApplication.class, args);
	}

}
