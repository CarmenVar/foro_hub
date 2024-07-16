package foro.project.VargasAPI.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foro")
public class ForoController {
    @GetMapping
    public String helloWorld(){
        return "Hello world!";
    }
}
