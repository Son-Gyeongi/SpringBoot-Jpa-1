package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em; // DB에 쿼리 날리는 거 보고 싶어

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("kim");

        // When
        Long savedId = memberService.join(member);

        // Then
        // em.flush(); // 영속성 컨텍스트에 있는 변경이나 내용을 DB에 반영
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        memberService.join(member1);
/*
        try {
            memberService.join(member2); // 예외가 발생해야 한다.
        } catch (IllegalStateException e) {
            return;
        }
*/

        // Then
        // IllegalStateException 예외가 발생하지 않으면 테스트 실패
        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }
}