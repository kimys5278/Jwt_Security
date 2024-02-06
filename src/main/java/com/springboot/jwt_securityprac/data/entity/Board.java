package com.springboot.jwt_securityprac.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@ToString(exclude = "member")
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;


    @ManyToOne //다대일 관계, 여러 게시물이 하나의 user의 것
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;



}
