package hxy.sparkjava.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hxy.sparkjava.demo.App;
import hxy.sparkjava.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public String select() {
		log.info("UserServiceImpl测试");
		
		return "UserServiceImpl测试";
	}

}
