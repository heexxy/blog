package com.he.webx.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.webx.domain.Person;
import com.he.webx.mapper.PersonMapper;
import com.he.webx.utils.CommonUtils;
import com.sun.jmx.snmp.Timestamp;

@Service
public class PersonService {
	@Autowired
	private PersonMapper personMapper;
	
	/**
	 * @description 根据邮箱获取人员信息
	 * @param mail
	 * @return
	 */
	public Person getPersonByMail(String mail){
		return personMapper.getPersonByMail(mail);
	}
	
	/**
	 * @description 新增用户
	 * @param person
	 */
	public void insetPerson(Person person){
		person.setId(CommonUtils.get32UUID());
		person.setCreateTime(new Date());
		person.setLoginTime(new Date());
		personMapper.insertPerson(person);
	}
	
	/**
	 * @description 更新人员信息
	 * @param person
	 */
	public void updatePerson(Person person){
		personMapper.updatePerson(person);
	}
}
