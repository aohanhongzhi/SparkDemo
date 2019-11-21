package hxy.sparkjava.demo.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hxy.sparkjava.demo.service.UserService;
import hxy.sparkjava.demo.service.impl.UserServiceImpl;
import spark.Request;
import spark.Response;
import spark.Route;
/**
 * Description：貌似一个url一个Router很繁琐！
 * 
 * @author eric
 *
 */
@Component
public class UserRoute implements Route{
	private final static Logger log = LoggerFactory.getLogger(UserRoute.class);
	@Autowired
	UserService userService;

	@Override
	public Object handle(Request request, Response response) throws Exception {
		log.info("访问路由！");

		
		
		return "自定义router，"+userService.select();
	}
    
}