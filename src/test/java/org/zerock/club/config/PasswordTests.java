package org.zerock.club.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

//  1111을 내부적으로 암호화 하고 해당 문자열을 암호화한 결과가 1111에 맞는지(match) 확인한다.
    @Test
    public void testEncode(){
        String password = "1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw: " + enPw);
        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult: " + matchResult);
    }
    //결과가 매번 다른데 이에 대한 matches()의 결과는 늘 true로 나온다(인코딩된 값은 매번 다르게 만들어지기때문)
}