package org.zerock.club.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.club.security.util.JWTUtil;

import static org.junit.jupiter.api.Assertions.*;

class JWTTests {

    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore(){ //test하기 전에 초기화 시킨 것임 ! bean은 안만들고
        System.out.println("testBefore............");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncoded() throws Exception{
        String email = "user10@ds.com";
        String str = jwtUtil.generateToken(email);
        System.out.println(str);
    }

    @Test
    public void testValidate() throws Exception {
        String email = "user1@ds.com";
        String str = jwtUtil.generateToken(email);
        Thread.sleep(5000); //5초 뒤에 (클래스 throws Exception하면 sleep에서도 따로 안해줘도 됨)
        String resultEmail = jwtUtil.validateAndExtract(str);
        System.out.println(resultEmail);
    }


}