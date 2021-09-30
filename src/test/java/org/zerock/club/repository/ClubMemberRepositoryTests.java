package org.zerock.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClubMemberRepositoryTests {
    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Test
    public void insertDummies(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            *//*1-80까지는 USER만 지정
            81-90까지는 USER, MANAGER
            91-100까지는 USER, MANAGER, ADMIN*//*
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@ds.com")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1"))
                    .build();

            //default role
            clubMember.addMemberRole(ClubMemberRole.USER);
            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            repository.save(clubMember);
        });
    }*/

    @Test
    public void testRead(){
        Optional<ClubMember> result = repository.findByEmail("user95@ds.com", false);
        ClubMember clubMember = result.get();
        System.out.println(clubMember); //clubmember에 tostring으로 나오기 때문에 문자열로 나옴
    }
}