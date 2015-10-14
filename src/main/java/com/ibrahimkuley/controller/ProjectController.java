package com.ibrahimkuley.controller;

import com.ibrahimkuley.aspect.RequiredLogin;
import com.ibrahimkuley.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hikuley on 14.10.2015.
 */

@Controller
@RequestMapping("/")
public class ProjectController {

    @Autowired
    private HttpServletRequest context;

    @RequiredLogin // require login for page or url
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("message", "welcome to home page");
        return "page";
    }


    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public String homePage2(ModelMap model) {
        model.addAttribute("message", "welcome to home page2");
        return "page";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        User user = new User();
        user.setId("123");
        user.setName("Halil");
        user.setSurname("Küley");

        HttpSession session = context.getSession();
        session.setAttribute("user", user);

        model.addAttribute("message", "Logined");
        return "page";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        model.addAttribute("message", "Logout");
        HttpSession session = context.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "page";
    }
}