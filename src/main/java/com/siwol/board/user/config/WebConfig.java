package com.siwol.board.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인 체크 인터셉터를 등록하고, 인증이 필요한 URL에만 적용
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/boards/new", "/boards/**/edit", "/boards/**/delete")
                // 로그인과 회원가입 페이지는 제외하고
                .excludePathPatterns("/accounts/login", "/accounts/signup", "/accounts/login/**", "/boards");
    }
}