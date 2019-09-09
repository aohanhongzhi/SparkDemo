package hxy.sparkjava.demo;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SparkJava App!
 *
 */
public class App {
	
	private final static Logger log = LoggerFactory.getLogger(App.class);
	
	/**
	 * Description:
	 * <br>SparkJava框架的程序启动入口
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		start();
	}

	/**
	 * 各种方法
	 * @throws InterruptedException
	 */
	static void start() throws InterruptedException {

		System.out.println("http://localhost:4567/");
		/**
		 * 首页
		 */
		get("/", (req, res) -> "Hello SparkJava!");

		get("/hello", (req, res) -> "Hello World!");

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
		
//		path("/api", () -> {
//		    before("/*", (q, a) -> log.info("Received api call"));
//		    path("/email", () -> {
//		        post("/add",       EmailApi.addEmail);
//		        put("/change",     EmailApi.changeEmail);
//		        delete("/remove",  EmailApi.deleteEmail);
//		    });
//		    path("/username", () -> {
//		        post("/add",       UserApi.addUsername);
//		        put("/change",     UserApi.changeUsername);
//		        delete("/remove",  UserApi.deleteUsername);
//		    });
//		});
		
		// Using string/html
		notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
		/**
		 * Description:<br>404 for json
		 */
		// Using Route
		notFound((req, res) -> {
		    res.type("application/json");
		    return "{\"message\":\"Custom 404\"}";
		});
		
		// Using string/html
		internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");
		
		/**
		 * Description:<br>505 for json
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
