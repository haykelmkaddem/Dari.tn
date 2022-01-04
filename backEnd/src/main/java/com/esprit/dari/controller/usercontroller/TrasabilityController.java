package com.esprit.dari.controller.usercontroller;

import com.esprit.dari.entities.CallEtats;
import com.esprit.dari.entities.userentity.Task;
import com.esprit.dari.entities.userentity.Trasability;
import com.esprit.dari.services.user.TrasabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TrasabilityController {
    @Autowired
    TrasabilityService trasabilityService;

    @GetMapping("/trasability/{request}")
    @ResponseBody
    public List<Trasability> trasabilityByRequest(@PathVariable("request") String request ){
        return trasabilityService.findAllByRequest(request);

    }
    @GetMapping("/trasability/all")
    @ResponseBody
    public List<Trasability> allTrasability(){
        return trasabilityService.findAllTrassability();

    }
}
