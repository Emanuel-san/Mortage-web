package app.mortageweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MortageController {

    @GetMapping
    public String index(){
        return "response";
    }
}
