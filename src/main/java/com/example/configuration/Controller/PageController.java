package com.example.configuration.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String updatePage(){
        return "updateZookeeper.html";
    }

}
