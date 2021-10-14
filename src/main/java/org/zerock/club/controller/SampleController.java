package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDTO;
import org.zerock.club.security.util.JWTUtil;

import javax.annotation.security.PermitAll;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    @Autowired
    private JWTUtil jwtUtil; //초기화 시킴

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll...");
    }


    //@PreAuthorize("principal.username == #clubAuthMember.username")
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){
        log.info("exMember...");
        log.info("--------------------------------");
        log.info(clubAuthMember);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin...");
    }

    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq\"user95@ds.com\"")
    @GetMapping("/exOnly")
    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){
        log.info("exMemberOnly.............");
        log.info(clubAuthMember);
        return "/sample/admin";
    }

    @GetMapping("/notes")
    public void notes(Model model) throws Exception{
        String email = "user95@ds.com";
        String str = jwtUtil.generateToken(email);
        log.info("jwtValue", str);
        model.addAttribute("jwtValue", str); //여기에 값을 실어보냄
    }


}
