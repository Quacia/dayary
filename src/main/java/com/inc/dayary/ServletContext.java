package com.inc.dayary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inc.dayary.interceptor.SignInInterceptor;

@Configuration
public class ServletContext implements WebMvcConfigurer{
	//servlet-context.xml java로 구현하기. WebMvcConfigurer는 오버라이드를 강제하지 않으므로 필요한 것만 구현하면 된다.
	@Autowired
	private SignInInterceptor signInInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(signInInterceptor)
				.addPathPatterns("/")
				.addPathPatterns("/member/mypage")
				.addPathPatterns("/diary/**");
		//하나의 인터셉터에 여러 개의 매핑을 걸어줄 수 있다.
	}
}
