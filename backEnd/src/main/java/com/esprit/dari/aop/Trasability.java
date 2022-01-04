package com.esprit.dari.aop;

import com.esprit.dari.services.user.TrasabilityService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Trasability {

    private final Environment env;
    private final TrasabilityService trasabilityService;

    public Trasability(Environment env, TrasabilityService trasabilityService) {
        this.env = env;
       this.trasabilityService = trasabilityService;
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointCut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    @Before("controllerPointCut()")
    public Object logAround(JoinPoint joinPoint)  {
        System.out.println("************************************************");
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       trasabilityService.save(joinPoint,auth);
        return joinPoint;
    }
}

