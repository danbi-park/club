package org.zerock.club.security.filter;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.club.security.dto.ClubAuthMemberDTO;
import org.zerock.club.security.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2                                 //인증을 위한 필터
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override               //인증시도
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("------------------ApiLoginFilter-------------------");
        log.info("attemptAuthentication");

        String email = request.getParameter("email");
        String pw = request.getParameter("pw");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);
        return getAuthenticationManager().authenticate(authToken);
    } //filter를 만들었으면 또 configure에서 쓸 수 있도록 만들어 줘야함

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        log.info("---------------------ApiLoginFilter-------------------");
        log.info("successfulAuthentication: " + authResult);
        log.info(authResult.getPrincipal());

        String email = ((ClubAuthMemberDTO)authResult.getPrincipal()).getUsername();
        String token = null;

        try {
            token = jwtUtil.generateToken(email);
            response.setContentType("text/plain");
            //client에 token을 문자열로 전송
            response.getOutputStream().write(token.getBytes());
            log.info(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
