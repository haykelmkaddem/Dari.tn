package com.esprit.dari.controller.usercontroller;

import com.esprit.dari.entities.CallEtats;
import com.esprit.dari.services.user.CallService;

import com.esprit.dari.entities.userentity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
public class TasksRestController {
    @Autowired
    private CallService callService;

    // URL : http://localhost:8081/dari/tasks
    @GetMapping ("/tasks")
    @ResponseBody
    public List<Task> taskList(@RequestParam(value = "callRequest", required = false) CallEtats callRequest, @RequestParam(value = "minDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate ){

        return callService.find(callRequest,LocalDateTime.of(minDate, LocalTime.MIDNIGHT));

    }
    @PostMapping ("/savetasks")
    @ResponseBody
    public Task save(@RequestBody Task task, Authentication authentication){
        return callService.saveTask(task,authentication);
    }
    //call satuts

    @PostMapping ("/tasks/treat/{id}/{status}")
    @ResponseBody
    public Task save(@PathVariable Long id, @PathVariable CallEtats status){

        return callService.treatTask(id,status);
    }


}
