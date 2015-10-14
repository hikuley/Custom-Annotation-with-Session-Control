package com.ibrahimkuley.cotroller;

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

    @RequiredLogin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("message", "welcome to home page");
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

    @RequiredLogin
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanel(ModelMap model) {
        model.addAttribute("message", "here is admin panel,session available");
        return "page";
    }


    @RequiredLogin
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPanel(ModelMap model) {
        model.addAttribute("message", "here is admin panel,session available");
        return "page";
    }
}