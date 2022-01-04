package com.esprit.dari.services.user.impl;

import com.esprit.dari.dao.userrepository.TaskRepository;
import com.esprit.dari.dao.userrepository.UserDariRepository;
import com.esprit.dari.entities.CallEtats;
import com.esprit.dari.entities.userentity.Task;
import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.security.CallConstants;
import com.esprit.dari.services.user.AccountService;
import com.esprit.dari.services.user.CallService;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class CallServiceImpl implements CallService {
    @Autowired
    private CallConstants callConstant;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    CallService callService;


    @Override
    public void makeCall(String numPhonne) throws URISyntaxException {
        Call.creator(new PhoneNumber("+216"+numPhonne), new PhoneNumber(callConstant.FROM_NUMBER),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
    }

    @Override
    public Task saveTask(Task task,Authentication authentication) {
        String userName = (String) authentication.getPrincipal();
        UserDari user = accountService.loadUserByUsername(userName);
        task.setDateTask(LocalDateTime.now());
        task.setUserDaris(user);
        return taskRepository.save(task);
    }

    @Override
    public Task treatTask(Long taskId, CallEtats callEtats) {
        Task task = taskRepository.findById(taskId).get();
        task.setCallRequest(callEtats);
        if(CallEtats.called.equals(callEtats)){
            try {
                makeCall(task.getUserDaris().getPhoneNumber());
            } catch (URISyntaxException e) {
                task.setCallRequest(CallEtats.FAILED_CAll);
            }
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> find(CallEtats callRequest, LocalDateTime minDate) {
        List<Task> result;
        if(callRequest==null&&minDate==null){
            result  = taskRepository.findAll();
        }else if(minDate == null){
            result = taskRepository.findAllByCallRequest(callRequest.toString());
        }else if(callRequest ==null) {
            result = taskRepository.findAllByDateTaskGreaterThanEqual(minDate);
        }else {
            result = taskRepository.findAllByDateTaskGreaterThanEqualAndCallRequest(minDate,callRequest.toString());
        }
        return result;
    }

}

