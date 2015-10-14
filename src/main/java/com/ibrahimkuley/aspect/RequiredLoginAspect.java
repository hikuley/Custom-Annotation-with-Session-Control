package com.ibrahimkuley.aspect;

import com.ibrahimkuley.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hikuley on 13.10.2015.
 */
@Aspect
public class RequiredLoginAspect {

    @Autowired
    private HttpServletRequest context;

    @Around("execution(@com.ibrahimkuley.aspect.RequiredLogin * *(..))&&@annotation(RequiredLogin)&&args(modelMap)")
    public Object sessionControl(ProceedingJoinPoint joinPoint, ModelMap modelMap) throws Throwable {
        System.out.println("Session control");
        HttpSession session = context.getSession();
        Object result = joinPoint.proceed();
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                result = "failure";
                modelMap.addAttribute("message", "require login");
            } else {
                result = "page";
            }
        } else {
            result = "page";
            modelMap.addAttribute("message", "require login");
        }
        return result;
    }

}
