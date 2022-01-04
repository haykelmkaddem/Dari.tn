package com.esprit.dari.entities.userentity;

import com.esprit.dari.entities.CallEtats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
    public class Task implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String taskName;
        private CallEtats callRequest=CallEtats.encour;
        private LocalDateTime dateTask;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserDari userDaris ;

    public Task(Long id, String taskName, CallEtats callRequest, LocalDateTime dateTask, UserDari userDaris) {
        this.id = id;
        this.taskName = taskName;
        this.callRequest = callRequest;
        this.dateTask = dateTask;
        this.userDaris = userDaris;
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public CallEtats getCallRequest() {
        return callRequest;
    }

    public void setCallRequest(CallEtats callRequest) {
        this.callRequest = callRequest;
    }

    public LocalDateTime getDateTask() {
        return dateTask;
    }

    public void setDateTask(LocalDateTime dateTask) {
        this.dateTask = dateTask;
    }

    public UserDari getUserDaris() {
        return userDaris;
    }

    public void setUserDaris(UserDari userDaris) {
        this.userDaris = userDaris;
    }
}
