package com.example.configuration.Controller;

import com.example.configuration.Model.jsonToCompare;
import com.example.configuration.Service.TextChanges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiffController {

    private TextChanges tc;

    @Autowired
    public DiffController(TextChanges tc){
        this.tc = tc;
    }

    @PostMapping("/diff")
    public String getDiffernece(@RequestBody jsonToCompare jsonToCompare){

        return tc.getChanges(jsonToCompare.getOldValue(),jsonToCompare.getNewValue());

    }

}
