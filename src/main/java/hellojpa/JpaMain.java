package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        /* JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 애플리케이션 로딩 시 딱 하나만 만들어놔야함

        EntityManager em = emf.createEntityManager();
        // 한 단위를 실행할 때마다 만들어줘야함

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1. 회원 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); // 저장할 때 persist

//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());
            /*
                Hibernate:
                    select
                        member0_.id as id1_0_0_,
                        member0_.name as name2_0_0_
                    from
                        Member member0_
                    where
                        member0_.id=?
                findMember = 1
                findMember = HelloA
            */

            // 2. 회원 수정
//            findMember.setName("HelloJPA");
            // em.persist(member); // 안 해도 됨
            /*
                update
                    hellojpa.Member update
                                Member
                        set
                        name=?
                        where
                        id=?
            */

            // 단순 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
            /*
                Hibernate:
                select
                    m
                from
                    Member as m select
                        member0_.id as id1_0_,
                                member0_.name as name2_0_
                        from
                        Member member0_

                member.name = HelloJPA
                member.name = HelloB
            */

            // 조건부 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(5).setMaxResults(8).getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            /*
                Hibernate:
                select
                    m
                from
                    Member as m select
                        member0_.id as id1_0_,
                                member0_.name as name2_0_
                        from
                        Member member0_ limit ? offset ?
            */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
