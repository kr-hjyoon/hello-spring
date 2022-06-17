package com.example.hellospring.controller;

import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller         // 컨테이너가 Controller 어노테이션을 인식하고 객체를 생성해서 관리한다.
public class MemberController {

    // 서비스를 스프링 컨테이너에게 등록하고 그 객체를 가져다 쓰기위해 직접 생성 안함
    // private final MemberService memberService = new MemberService();
     private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;

    }

}
