package com.kinzr.apellian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Controller
@Log
public class MainController {

	@Autowired UsersMapper userMapper;
	
	@RequestMapping("/")
    public String root()
    {
		List<Users> userList = userMapper.selectAll();
        return "Spring Boot Test";
    }
	
}
