package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;

import java.time.LocalDate;

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
                    @RequestParam(name = "age" , defaultValue = "28") int age){
                log.info("ex2..............");
                log.info("name: " + name);
                log.info("age : " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3............");
        log.info("dueDate : " + dueDate);
    }
    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("-------------------------");
        model.addAttribute("message" , "Hello World!!");
    }
    //
    @GetMapping("/ex4_1")
    public void ex4Extra1(TodoDTO todoDTO , Model model) {

        log.info(todoDTO);

    }
    // ModelAttribute 속성을 이용한 사용자 정의 DTO
    @GetMapping("/ex4_2")
    public void ex4Extra2(@ModelAttribute("dto") TodoDTO todoDTO , Model model) {

        log.info(todoDTO);
    }
     /* addAttibute(키 , 값) : 리다이렉트할 때 쿼리 스트링이 되는 값을 지정
        addFlashAttribute(키 , 값) : 일회용으로만 데이터를 전달하고 삭제되는 값을 지정 */
    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("name" , "SHINSEOUNGROK");
        redirectAttributes.addFlashAttribute("result" , "SUCCESS");

        return "redirect:/ex6";
    }
    @GetMapping("/ex6")
    public void ex6() {

    }
    @GetMapping("/ex7")
    public void ex7(String p1 , int p2) {
        log.info("p1......." + p1);
        log.info("p2......." + p2);
    }
}
