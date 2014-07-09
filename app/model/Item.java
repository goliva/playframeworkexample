package model;

import play.db.jpa.JPA;
import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	public Long id;
	public Long user_id;
	public String title;
	public Double price;
	public String currency;
	public Double stats;
	public String brand;
	
	public void setId(Long id){
		this.id = id;
		Jedis jedis = new Jedis("localhost");
		stats = new Double(jedis.get(""+id));
		this. brand = (String) JPA.em().createNativeQuery("SELECT brand FROM brands WHERE item_id = 12324").getSingleResult();
	}
}
