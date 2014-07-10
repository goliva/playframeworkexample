package model;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

import com.typesafe.config.ConfigFactory;

public class User {
	public Long id;
	public String ranking;
	protected static String USER_URL = ConfigFactory.load().getString("api.user.url");
	
	public static Promise<User> get(Long item_id) {
		
		Promise<WSResponse> userPromise = WS.url(USER_URL+item_id).get();
		return userPromise.map(
			new Function<WSResponse, User>() {
				public User apply(WSResponse response){
					return Json.fromJson(response.asJson(), User.class);
				}
			}
		);
		
	}
}
