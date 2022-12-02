package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello..........");
    }

    @GetMapping("/ex1")
    public void ex1(String name , int age) {
        log.info("ex1..............");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name" , defaultValue = "신승록") String name ,
                    @RequestParam(name = "age" , defaultValue = "22") int age){
                log.info("ex2..............");
                log.info("name: " + name);
                log.info("age : " + age);
    }
}
