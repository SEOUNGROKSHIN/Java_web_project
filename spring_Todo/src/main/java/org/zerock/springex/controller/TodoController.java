package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

   /* @GetMapping("/list")
    public void list(Model model) {

        log.info("todo list.....");

        model.addAttribute("dtoList" , todoService.getAll());

    }*/
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO , BindingResult bindingResult, Model model) {

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.....");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult ,
                               RedirectAttributes redirectAttributes) {
        log.info("POST todo register....");

        if(bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors" , bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long tno , Model model) {

        TodoDTO  todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto" , todoDTO);
    }
    @PostMapping("/remove")
    public String remove(Long tno , RedirectAttributes redirectAttributes){

        log.info("--------------------remove-------------------------");
        log.info("tno : " + tno);

        todoService.delete(tno);
        
        return "redirect:/todo/list";
    }
    @PostMapping("/modfiy")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("Modify Post Error....");
            redirectAttributes.addFlashAttribute("errors" , bindingResult.getAllErrors() );

            redirectAttributes.addAttribute("tno" , todoDTO.getTno() );
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }
}