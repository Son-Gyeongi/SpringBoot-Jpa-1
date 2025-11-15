package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

/*
    @PersistenceContext // JPA 사용, JPA 가 제공하는 표준 어노테이션
    private EntityManager em;
*/
    // @Autowired - spring data JPA 가 Autowired injection 될 수 있게 도와준다.
    public final EntityManager em;

/*
    public MemberRepository(EntityManager em) {
        this.em = em;
    }
*/

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // JPQL - from 대상이 엔티티 객체로 쿼리 사용
        return em.createQuery("select m from Member m", Member.class) // JPQL, 반환타입
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
