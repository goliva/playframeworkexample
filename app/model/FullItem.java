package model;

import com.typesafe.config.ConfigFactory;

import play.libs.Json;
import play.libs.ws.WS;

public class FullItem {

	protected static String ITEM_URL = ConfigFactory.load().getString("api.item.url");
	protected static String USER_URL = ConfigFactory.load().getString("api.user.url");
	protected static String PICTURE_URL = ConfigFactory.load().getString("api.picture.url");
	
	public Item item;
	public User user;
	public Picture picture;
	
	public static FullItem get(Long itemID) {
		FullItem response = new FullItem();
		response.item = Json.fromJson(WS.url(ITEM_URL+itemID).get().get(10000l).asJson(), Item.class);
		response.picture = Json.fromJson(WS.url(PICTURE_URL+itemID).get().get(10000l).asJson(), Picture.class);
		response.user = Json.fromJson(WS.url(USER_URL+response.item.user_id).get().get(10000l).asJson(), User.class);
		return response;
	}

}
