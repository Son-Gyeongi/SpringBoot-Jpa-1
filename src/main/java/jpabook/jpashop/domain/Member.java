package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함
    private Address address;

    // 하나의 회원이 여러개 상품 주문 일대다 관계
    @OneToMany(mappedBy = "member") // 연관관계 주인이 아니라 거울이다.
    private List<Order> orders = new ArrayList<>();
}
