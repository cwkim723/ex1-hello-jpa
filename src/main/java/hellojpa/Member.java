package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Member") // jpa가 관리하는 객체, db과 매핑해서 씀 / 기본적으로 name은 클래스 이름으로 쓰기 때문에 굳이 name을 쓸 일은 별로 없음
@Table(name = "MBR") // 클래스 이름과 db 테이블명이 다를 경우 매핑하기 위해 씀 -> 이걸 쓰면 from MBR로 됨
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
