package model;

import play.db.jpa.Transactional;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.typesafe.config.ConfigFactory;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	public Long id;
	public Long user_id;
	public String title;
	public Double price;
	public String currency;
	public Double stats;
	protected static String ITEM_URL = ConfigFactory.load().getString("api.item.url");


	@Transactional
	public static Promise<Item> get(Long itemID) {
		Promise<WSResponse> itemPromise = WS.url(ITEM_URL+itemID).get();
		return itemPromise.map(
			new Function<WSResponse, Item>(){
				@Override
				@Transactional
				public Item apply(WSResponse itemResponse) throws Throwable {
					Item item = Json.fromJson(itemResponse.asJson(), Item.class);
					Jedis jedis = new Jedis("localhost");
					item.stats = new Double(jedis.get(""+item.id));
					return item;
				}
				
			}
		);
	}
	
	
}
