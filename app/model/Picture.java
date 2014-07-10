package model;

import com.typesafe.config.ConfigFactory;

import play.libs.Json;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

public class Picture {
	public Long id;
	public String url;
	protected static String PICTURE_URL = ConfigFactory.load().getString("api.picture.url");
	
	public static Promise<Picture> get(Long item_id) {
		Promise<WSResponse> picturePromise = WS.url(PICTURE_URL+item_id).get();
		return picturePromise.map(
				new Function<WSResponse, Picture>() {
					public Picture apply(WSResponse response){
						 return Json.fromJson(response.asJson(), Picture.class);
					}
				}
		);
	}
}
