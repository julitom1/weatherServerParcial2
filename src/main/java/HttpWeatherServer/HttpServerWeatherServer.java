package HttpWeatherServer;


public class HttpServerWeatherServer {
	
	private static final HttpServerWeatherServer instance=new HttpServerWeatherServer();
	public static HttpServerWeatherServer getInstance() {
		return instance;
	}
	
	private HttpServerWeatherServer() {
		
	}

}
