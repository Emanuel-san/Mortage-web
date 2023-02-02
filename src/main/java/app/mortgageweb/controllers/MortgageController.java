package app.mortgageweb.controllers;

import app.mortgage.handler.DataManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;

@Controller
public class MortgageController {

    @GetMapping("/")
    public ModelAndView index(){
        DataManager mortgageManager = new DataManager();
        Path path = Path.of(System.getProperty("user.dir") + "/prospects.txt");
        mortgageManager.initDataFetch(path);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("mortgages", mortgageManager.getMortgagesAsList());
        return modelAndView;
    }
}
