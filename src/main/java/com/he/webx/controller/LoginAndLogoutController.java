package com.he.webx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.he.webx.domain.Person;
import com.he.webx.service.PersonService;
import com.he.webx.utils.TimeUtils;
import com.he.webx.utils.ValidUtils;
import com.he.webx.utils.constants.WebxConstants;

@Controller
public class LoginAndLogoutController {

	@Autowired
	PersonService ps;

	/**
	 * @description 登录提交方法
	 * @param resp
	 * @param session
	 * @param req
	 * @param model
	 * @param mail
	 * @param password
	 * @param back
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletResponse resp, HttpSession session,
			HttpServletRequest req, Model model, String mail, String password) {
		Person person = ps.getPersonByMail(mail);
		if (password.equals(person.getPassword())) {
			person.setLoginTime(TimeUtils.getDateTime());
			ps.updatePerson(person);
			session.setAttribute(WebxConstants.PERSON_SESSION, person);
			return WebxConstants.REDIRECT + "/";
		} else {
			String error = "请输入正确的用户名或密码";
			model.addAttribute("error", error);
		}
		return "/person/login.vm";
	}

	/**
	 * @description 退出
	 * @param resp
	 * @param session
	 * @param req
	 * @param model
	 * @param back
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletResponse resp, HttpSession session,
			HttpServletRequest req, Model model, String back) {
		session.removeAttribute(WebxConstants.PERSON_SESSION);
		if (ValidUtils.isNotNull(back)) {
			back = back.replaceAll("@@", "?");
			back = back.replaceAll("@", "&");
			return WebxConstants.REDIRECT + "/" + back;
		}
		return WebxConstants.REDIRECT + "/";
	}

	/**
	 * @description 登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request,
			HttpServletResponse response) {
		return "/person/login.vm";
	}

	/**
	 * @description 注册页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registPage(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		return "/person/regist.vm";
	}

	/**
	 * @description 注册提交方法
	 * @param req
	 * @param resp
	 * @param session
	 * @param mail
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest req, HttpServletResponse resp,
			HttpSession session, String mail, String password,String name , Model model) {
		if (ValidUtils.isNull(mail)) {
			model.addAttribute("err-code", WebxConstants.REGIST_ERR_1);
			return "/person/regist.vm";
		}
		if (ValidUtils.isNull(password)) {
			model.addAttribute("err-code", WebxConstants.REGIST_ERR_2);
			return "/person/regist.vm";
		}
		if (!ValidUtils.isEmailAddress(mail)) {
			model.addAttribute("err-code", WebxConstants.REGIST_ERR_3);
			return "/person/regist.vm";
		}
		if (!ValidUtils.isNull(name)) {
			model.addAttribute("err-code", WebxConstants.REGIST_ERR_5);
			return "/person/regist.vm";
		}
		Person p = ps.getPersonByMail(mail);
		if (p != null) {
			model.addAttribute("err-code", WebxConstants.REGIST_ERR_4);
			return "/person/regist.vm";
		}
		Person person = new Person();
		person.setMail(mail);
		person.setPassword(password);
		person.setName(name);
		person.setCreateTime(TimeUtils.getDateTime());
		person.setLoginTime(TimeUtils.getDateTime());
		ps.insetPerson(person);
		Person newP = ps.getPersonByMail(mail);
		session.setAttribute(WebxConstants.PERSON_SESSION, newP);
		return WebxConstants.REDIRECT + "/";
	}
}
