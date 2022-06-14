package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
        private final MemberRepository memberRepository = new MemoryMemberRepository();

        /**
         * 회원가입
         * @param member
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
         * @return
         */
        public List<Member> findMembers(){
                return memberRepository.findAll();
        }

        public Optional<Member> findOne(Long memberId){
                return memberRepository.findById(memberId);
        }
}
