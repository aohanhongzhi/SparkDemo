package hxy.sparkjava.demo.service;

import hxy.sparkjava.demo.dao.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserModel select(Integer id);

}
