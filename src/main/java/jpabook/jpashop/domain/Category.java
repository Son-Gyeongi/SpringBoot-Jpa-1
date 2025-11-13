package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")) // 중간 테이블 매핑
    private List<Item> items = new ArrayList<>();

    // 카테고리 구조 (셀프 양방향 연관관계)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent; // 내 부모는 나다.
    // 카테고리가 자기의 자식은 여러 개 가질 수 있다.
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
