package org.zerock.club.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember extends BaseEntity{
    @Id
    private String email; //pk의 타입이 string이라서 generatedValue 안함

    private String password;
    private String name;
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)   //컬렉션 객체임을 JPA에게 알려주는 어노테이션 (role이 복수이기때문에)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();
    //타입맴버 롤이라는 이넘 타입으로 가져온다.(ElementCollection) 이메일로 연결이 된다.
    //roleset은 pk없음

    public void addMemberRole(ClubMemberRole clubMemberRole){
        roleSet.add(clubMemberRole); //set, list은 데이터 추가할 때 add, map은 put
    } //role은 복수로 쓸 수도 있어야한다(admin은 admin, member에 들어갈 수 있다)
}
