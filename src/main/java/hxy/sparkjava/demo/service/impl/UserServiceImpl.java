package hxy.sparkjava.demo.service.impl;

import hxy.sparkjava.demo.dao.UserDao;
import hxy.sparkjava.demo.dao.model.UserModel;
import hxy.sparkjava.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserModel select(Integer id) {
        log.info("UserServiceImpl测试 {}", id);

        UserModel user = UserDao.getUser(id);

        return user;
    }

}
