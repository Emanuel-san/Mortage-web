package app.mortageweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MortageController {

    @GetMapping("/")
    public String index(){
        //ModelAndView
        return "response";
    }
}
