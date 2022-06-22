package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service                      // 스프링 컨테이너로 부터  MemberService 를  구동시 등록하도록 해줌
@Transactional
public class MemberService {
        private final MemberRepository memberRepository ;


        public MemberService(MemberRepository memberRepository) {
                this.memberRepository = memberRepository;
        }

        /**
         * 회원가입
         * @return long  , 생성된  member id 반환
         */
        public long join (Member member){
                validateDuplicatedMember(member);
                memberRepository.save(member);
                return member.getId();
        }

        private void validateDuplicatedMember(Member member) {
                memberRepository.findByName(member.getName())
                        .ifPresent( m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        }

        /**
         * 전체회원조회
         */
        public List<Member> findMembers(){
                return memberRepository.findAll();
        }

        public Optional<Member> findOne(Long memberId){
                return memberRepository.findById(memberId);
        }
}
