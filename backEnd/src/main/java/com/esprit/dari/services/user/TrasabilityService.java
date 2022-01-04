package com.esprit.dari.services.user;

import com.esprit.dari.entities.userentity.Trasability;
import org.aspectj.lang.JoinPoint;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TrasabilityService {
    void save(JoinPoint joinPoint, Authentication authentication);
    List<Trasability> findAllByRequest(String request);
    List<Trasability> findAllTrassability();
}
