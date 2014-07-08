package controllers;

import java.util.List;

import play.libs.F;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;

public class Application extends Controller {

    public static Promise<Result> index() {
//        Jedis jedis = new Jedis("localhost");
//        String value = jedis.get("gon");
//        WSResponse reponse = (WSResponse) WS.url("https://api.mercadolibre.com/items/MLA496272362").get().get(20000);
        
    	final Promise<String> promiseOne = WS.url("https://api.mercadolibre.com/items/MLA496272362").get().map(
                 new Function<WSResponse, String>() {
                     public String apply(WSResponse response) {
                     	Jedis jedis = new Jedis("localhost");
                         String value = jedis.get("gon");
                         return "Feed title:" + response.asJson().findPath("site_id")+" "+value;
                     }
                 }
         );
    	
        final Promise<String> promiseTwo = WS.url("https://api.mercadolibre.com/items/MLA496272362").get().map(
                new Function<WSResponse, String>() {
                    public String apply(WSResponse response) {
                    	Jedis jedis = new Jedis("localhost");
                        String value = jedis.get("gon");
                        return "Feed title:" + response.asJson().findPath("title")+" "+value;
                    }
                }
        );
        Promise<List<String>> responses = F.Promise.sequence(promiseOne, promiseTwo);
        return responses.map(
        		new Function<List<String>, Result>() {
                    public Result apply(List<String> response) {
                    	StringBuilder b = new StringBuilder();
                    	for (String string : response) {
							b.append(string);
						}
                    	return ok(b.toString());
                    }
                }
        		);
         
        
    }

}
