package com.axe.todo.controller;

import com.alibaba.fastjson.JSONObject;
import com.axe.todo.entity.Todo;
import com.axe.todo.entity.TodoEnum;
import com.axe.todo.entity.User;
import com.axe.todo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserService {

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    private UserRepository repository;

    @RequestMapping("/")
    public ModelAndView index() {
        System.out.println("url /");
        return new ModelAndView("login");
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest request,
                                     @RequestBody Map<String, String> dict) {
        Map<String, String> result = new HashMap<String, String>();
        HttpSession session = request.getSession();
        String name = dict.get("name");
        String password = dict.get("password");
        List<Long> userIdList = repository.validUser(name, password);
        if (!userIdList.isEmpty()) {
            session.setAttribute("user", name);
            session.setMaxInactiveInterval(10000);
            result.put("code", "1");
            return result;
        } else {
            result.put("code", "0");
            return result;
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, String> register(HttpServletRequest request,
                                        @RequestBody Map<String, String> dict) {
        Map<String, String> result = new HashMap<String, String>();
        String name = dict.get("name");
        String password = dict.get("password");
        List<Long> userIdList = repository.validUser(name, password);
        if (userIdList.isEmpty()) {
            repository.save(new User(name, password));
            result.put("code", "1");
            return result;
        } else {
            result.put("code", "0");
            return result;
        }
    }
}
