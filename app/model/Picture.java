package model;


import org.jboss.netty.handler.codec.http.HttpHeaders;

import play.cache.Cache;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

import com.typesafe.config.ConfigFactory;

public class Picture {
	public Long id;
	public String url;
	protected static String PICTURE_URL = ConfigFactory.load().getString("api.picture.url");
	
	public static Promise<Picture> get(final Long item_id) {
		final Picture cachedPicture = (Picture) Cache.get(item_id+"");
		if (cachedPicture != null){
			return Promise.promise(
				new Function0<Picture>(){
					@Override
					public Picture apply() throws Throwable {
						return cachedPicture;
					}
					
				}
			);
		}else{
			Promise<WSResponse> picturePromise = WS.url(PICTURE_URL+item_id).get();
			return picturePromise.map(
					new Function<WSResponse, Picture>() {
						public Picture apply(WSResponse response){
							Picture picture = Json.fromJson(response.asJson(), Picture.class);
							Integer timeout = new Integer(response.getHeader("Cache-Control").replaceAll(".*max-age=([0-9]+).*", "$1"));
							Cache.set(item_id+"", picture, timeout);
							return picture;
						}
					}
					);
			
		}
	}
}
