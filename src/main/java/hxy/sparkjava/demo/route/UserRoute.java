package hxy.sparkjava.demo.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hxy.sparkjava.demo.service.UserService;
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
	
	@Autowired
	UserService userService;

	@Override
	public Object handle(Request request, Response response) throws Exception {

		userService.select();
		
		return "自定义router";
	}
    
}