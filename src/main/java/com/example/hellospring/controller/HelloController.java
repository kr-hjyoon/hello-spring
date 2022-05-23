package com.example.hellospring.controller;

import com.example.hellospring.dto.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hjyoon!!");
        return "hello";
    }

    @GetMapping ("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping ("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name", required = true) String name, Model model){
        return "hello" + name;
    }

    @GetMapping ("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name, Model model){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
}