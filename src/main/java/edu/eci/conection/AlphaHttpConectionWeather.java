package edu.eci.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class AlphaHttpConectionWeather {
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final AlphaHttpConectionWeather instance=new AlphaHttpConectionWeather();
	
    private AlphaHttpConectionWeather() {
    	
    }
    public static AlphaHttpConectionWeather getInstance() {
    	return instance;
    }
	/**
     *  Hace conexión con la API externa que se le indico
     * @return Devuelve los datos de esa API externa
     * @throws IOException
     */
    public String getDates(String country) throws IOException {

    	
        
    		String responseStr="";
            

            URL obj = new URL(getUrlCountry(country));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                responseStr = response.toString();
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
        
        return responseStr;
    }
    private String getUrlCountry(String Country) {
    	//System.out.println(Country);
    	return "https://api.openweathermap.org/data/2.5/weather?q="+Country+"&appid=8805fcd7d0439565c8dda8eb5eedbbad";
    }

}
