package model;

import play.libs.F.Function;
import play.libs.F.Promise;

public class FullItem {

	
	public Item item;
	public User user;
	public Picture picture;
	
	public static Promise<FullItem> get(Long itemID) {
		Promise<Item> itemPromise = Item.get(itemID);
		return itemPromise.flatMap(
			new Function<Item,Promise<FullItem>>() {
				public Promise<FullItem> apply(Item itemResponse) {
					final FullItem response = new FullItem();
					response.item =  itemResponse;
					Promise<Picture> picturePromise = Picture.get(response.item.id);
					final Promise<User> userPromise = User.get(response.item.id);
					return picturePromise.flatMap(
						new Function<Picture, Promise<FullItem>>(){
							public Promise<FullItem> apply(Picture picture) throws Throwable {
								response.picture = picture;
								return userPromise.map(
									new Function<User, FullItem>(){
										public FullItem apply(User user) throws Throwable {
											response.user = user;
											return response;
										}
									}
								);		
							};
						}
					);
				}
			}
		);
	}

}
