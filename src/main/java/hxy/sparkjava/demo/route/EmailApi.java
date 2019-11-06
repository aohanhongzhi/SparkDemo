package hxy.sparkjava.demo.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.sparkjava.demo.App;
import spark.Request;
import spark.Response;
import spark.Route;

public class EmailApi {
	private final static Logger log = LoggerFactory.getLogger(EmailApi.class);
	public static Route addEmail = new Route() {
		
		@Override
		public Object handle(Request request, Response response) throws Exception {
			log.info("静态路由实例化");
			return null;
		}
	};
	public static Route changeEmail;
	public static Route deleteEmail;

}
