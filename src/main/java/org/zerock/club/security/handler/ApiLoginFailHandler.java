package org.zerock.club.security.handler;

import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        log.info("login fail handler................");
        log.info(exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//json리턴
        response.setContentType("application/json;charset=utf-8");
        //response.setContentType("text/html;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = exception.getMessage();
        json.put("code","401");
        json.put("message", message);
        PrintWriter out = response.getWriter();

        out.print("out.print" + json); //자격 증명에 실패하였습니다.
        log.info("log.info" + json); //{"code":"401","message":"자격 증명에 실패하였습니다."}
        /* text/html;
        out.print("<script>");
        out.print("alert('로그인에 실패했습니다.');");
        out.print("</script>");*/
    }
}
