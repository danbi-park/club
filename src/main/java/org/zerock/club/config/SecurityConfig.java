package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){ //인터페이스
        return new BCryptPasswordEncoder(); //spring bean을 하나 만든 것과 같음, password를 implement함 인터페이스로 넘어감(형변환)서로 관련이 없는데도 불구하고 연관되지 않은 클래스를 연결해줌 interface -> 독립적으로 사용가능
    }

 /*   //자주 쓰이진 않음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$qdPCUyA42QYY025yebqJ6uKeX7WHkHZBa4O4pUvk4xZxWDR4OhWVS")
                .roles("USER"); //USER라는 권한을 임의로 만든 것임
    }*/

    //url에 대해서 선별
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/sample/all").permitAll()//권한 조건만 설정
                .antMatchers("/sample/member").hasRole("USER"); //Url에 대한 설정 user상속값과 일치해야함
        http.formLogin();
        http.csrf().disable();
        http.logout();
    }

}
