package com.he.webx.utils;


public class IDGenerator {
	
	public synchronized String getNextId(String maxId){
		return getId(maxId);
	}
	
	private String getId(String maxId){
		String _time = TimeUtils.getStringTimeByFormat("yyyyMMdd");
		Integer id = 0;
		if(ValidUtils.isNotNull(maxId)){
			String time_ = maxId.substring(0,8);
			if(_time.equals(time_)){
				String id_ = maxId.substring(8, maxId.length());
				id = Integer.valueOf(id_);
			}
		}
		id++;
		String id_ = id.toString();
		if(id_.length() < 6){
			id_ += "0000" + id_;
		}
		id_ = id_.substring(id_.length()-5, id_.length());
		return _time + id_;
	}
	
}
