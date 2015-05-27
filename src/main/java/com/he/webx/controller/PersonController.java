package com.he.webx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.he.webx.domain.Person;
import com.he.webx.service.PersonService;
import com.he.webx.utils.constants.WebxConstants;

@Controller
@RequestMapping(value="/person")
public class PersonController {
	
	@RequestMapping(value="/index" , method = RequestMethod.GET)
	public String personIndex(HttpServletRequest request ,HttpServletResponse response,HttpSession session){
		return "/person/index.vm";
	}
}
