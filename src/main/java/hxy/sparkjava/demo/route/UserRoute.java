package hxy.sparkjava.demo.route;

import hxy.sparkjava.demo.dao.model.UserModel;
import hxy.sparkjava.demo.entity.BaseResponse;
import hxy.sparkjava.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Description：貌似一个url一个Router很繁琐！ sparkjava这种微型框架就不需要Service这一层，直接写在Route里面就好了。
 *
 * @author eric
 */
@Component
public class UserRoute implements Route {

    private final static Logger log = LoggerFactory.getLogger(UserRoute.class);

    @Autowired
    UserService userService;

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String id = request.queryParamOrDefault("id", "1");
        log.info("访问路由！id={}", id);

        UserModel userModel = userService.select(Integer.parseInt(id));

        return BaseResponse.success(userModel);
    }

}