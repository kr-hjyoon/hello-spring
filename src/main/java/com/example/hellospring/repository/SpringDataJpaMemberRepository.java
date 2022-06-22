package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속 받으므로 , 구현체를 직접만들어서 Bean으로 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    @Override       // name은 공통화되지 않은 개별 필드이기때문에 별도로 정의해줘야 한다.
    // select m from Member m where m.name=?
    Optional<Member> findByName(String name);
}
