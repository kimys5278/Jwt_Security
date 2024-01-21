package com.springboot.jwt_securityprac.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
    //계정이 가지고 있는 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    //계정의 비밀번호 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword(){
        return this.password;
    }
    //계정의 이름 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.getEmail();
    }

    //계정이 만료됐는지 리턴 - true면 만료 X
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨 있는지 리턴 -> true면 안잠김.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정의 비밀번호가 만료됐는지 리턴 -> ture면 만료 X
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화돼 있는지 리턴 -> true면 활성화
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Board> board;

}
