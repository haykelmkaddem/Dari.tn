package com.esprit.dari.controller.usercontroller;

import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.services.user.impl.CallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CallRestController {
    @Autowired
    CallServiceImpl callService;
    @GetMapping(value = "/makecall")
    public void makeCall() throws URISyntaxException {
        callService.makeCall("50713446");
    }
}
