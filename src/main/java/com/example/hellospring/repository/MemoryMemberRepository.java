package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository             // 스프링 컨테이너에 구동시 등록
public class MemoryMemberRepository implements  MemberRepository{

    public static Map<Long,Member>  store = new HashMap<>();  // 실무에서는 Concurrent HashMap
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence );
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }

}
