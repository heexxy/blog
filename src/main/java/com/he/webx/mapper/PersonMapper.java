package com.he.webx.mapper;

import com.he.webx.domain.Person;

public interface PersonMapper extends IMapper<Person>{
	/**
	 * @description 通过邮箱查询人员信息
	 * @param mail
	 * @return
	 */
	public Person getPersonByMail(String mail);
	
	/**
	 * @description 插入人员信息
	 * @param person
	 */
	public void insertPerson(Person person);
	
	/**
	 * @description 更新人员信息
	 * @param person
	 */
	public void updatePerson(Person person);
}
