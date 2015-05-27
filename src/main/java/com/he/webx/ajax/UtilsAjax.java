package com.he.webx.ajax;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.he.webx.domain.Person;
import com.he.webx.service.PersonService;
import com.he.webx.utils.ValidUtils;
import com.he.webx.utils.constants.WebxConstants;

/**
 * @description  异步Controller
 * @author hexy
 *
 */
@Controller
@RequestMapping(value="/ajax")
public class UtilsAjax {
	
	@Autowired
	private PersonService ps;
	
	/**
	 * @description 查询该邮箱是否注册
	 * @param mail
	 * @return
	 */
	@RequestMapping(value="/check-mail" , method = RequestMethod.GET)
	@ResponseBody
	public String checkMail(String mail){
		Person p = ps.getPersonByMail(mail);
		if(p == null){
			return "true";
		}
		return "false";
	}
	
	/**
	 * @description 登录提交
	 * @param session
	 * @param mail
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session,String mail,String password){
		if(ValidUtils.isNull(mail) || ValidUtils.isNull(password)){
			return "err1";
		}
		Person p = ps.getPersonByMail(mail);
		if(p == null){
			return "err2";
		}
		if(!password.equals(p.getPassword())){
			return "err3";
		}
		session.setAttribute(WebxConstants.PERSON_SESSION, p);
		return "true";
	}
}
