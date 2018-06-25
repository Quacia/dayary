package com.inc.dayary;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class RootContext {
	//SpringBoot는 xml 설정을 지양한다
	
	@Bean
	//JavaMailSender 인터페이스의 구현체인 JavaMailSenderImpl을 빈으로 등록.
	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");//구글 제공 메일서버.
		mailSender.setPort(587); //smtp서버는 587번 포트를 사용한다.
		mailSender.setUsername("lycorise@gmail.com");
		mailSender.setPassword("11111111");
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.debug", "true");
		mailSender.setJavaMailProperties(props);
		return mailSender;
	}
}
