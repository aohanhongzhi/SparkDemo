package hxy.sparkjava.demo;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import hxy.sparkjava.demo.route.EmailApi;
import hxy.sparkjava.demo.route.UserRoute;

/**
 * SparkJava App!
 *
 */
@ComponentScan
@Service
public class App {

	private final static Logger log = LoggerFactory.getLogger(App.class);

	@Autowired
	UserRoute userRoute;

	/**
	 * Description: <br>
	 * SparkJava框架的程序启动入口
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		start();
		springStart();
	}

	public static void springStart() throws InterruptedException {
		try (
				// 初始化IOC容器
				AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
						App.class);) {
			// 通过IOC容器获得你要执行的业务代码的类
			App springMain = applicationContext.getBean(App.class);
			// 通过IOC容器获取到的类执行你的业务代码，可以认为是整个Spring程序的入口了，所有的代码都应该写在这之后了。不能再随意使用new了。否则Spring无法接管。
			springMain.start();
		} finally {
			System.out.println("普通java程序(非Web应用)执行完成,IOC容器关闭。。。");
		}
	}

	/**
	 * 各种方法
	 * 
	 * @throws InterruptedException
	 */
	void start() throws InterruptedException {
		//指定应用端口
		port(4567);
		System.out.println("http://localhost:4567/");
		/**
		 * 首页
		 */
		get("/", (req, res) -> "Hello SparkJava!");

		get("/hello", (req, res) -> "Hello World!");
//		get("/hellos",new hxy.sparkjava.demo.route.UserRoute());
		get("/hellos", userRoute);

		// matches "GET /hello/foo" and "GET /hello/bar"
		// request.params(":name") is 'foo' or 'bar'
		get("/hello/:name", (request, response) -> {
			return "Hello: " + request.params(":name");
		});

		// matches "GET /say/hello/to/world"
		// request.splat()[0] is 'hello' and request.splat()[1] 'world'
		get("/say/*/to/*", (request, response) -> {
			return "Number of splat parameters: " + request.splat().length;
		});

		post("/post", (request, response) -> {
			// Create something
			return "post";
		});

		put("/put", (request, response) -> {
			// Update something
			return "post";
		});

		delete("/delete", (request, response) -> {
			// Annihilate something
			return "post";
		});

		options("/options", (request, response) -> {
			// Appease something
			return "post";
		});

		path("/api", () -> {
			before("/*", (q, a) -> log.info("Received api call"));
			path("/email", () -> {
				post("/add", EmailApi.addEmail);
				put("/change", EmailApi.changeEmail);
				delete("/remove", EmailApi.deleteEmail);
			});
//		    path("/username", () -> {
//		        post("/add",       UserApi.addUsername);
//		        put("/change",     UserApi.changeUsername);
//		        delete("/remove",  UserApi.deleteUsername);
//		    });
		});

		// Using string/html
		notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
		/**
		 * Description:<br>
		 * 404 for json
		 */
		// Using Route
		notFound((req, res) -> {
			res.type("application/json");
			return "{\"message\":\"Custom 404\"}";
		});

		// Using string/html
		internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");

		/**
		 * Description:<br>
		 * 505 for json
		 */
		// Using Route
		internalServerError((req, res) -> {
			res.type("application/json");
			return "{\"message\":\"Custom 500 handling\"}";
		});

		get("/throwexception", (request, response) -> {
			throw new YourCustomException();
		});

		exception(YourCustomException.class, (exception, request, response) -> {
			// Handle the exception here
		});

		Thread.sleep(1000000);
		System.out.println("stop!");
		stop();

	}

}
