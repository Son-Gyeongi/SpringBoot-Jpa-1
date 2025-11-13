package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // JPA 의 내장 타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 함부로 new 생성 못한다.
    protected Address() {
    }

    // 값 타입은 변경 불가능하게 설계해야 한다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
