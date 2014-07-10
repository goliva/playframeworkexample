package controllers;

import model.FullItem;
import play.db.jpa.Transactional;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;



public class Application extends Controller {

	@Transactional(readOnly=false)
	public static Promise<Result> index(Long itemID) {
    	Promise<FullItem> response = FullItem.get(itemID);
    	return response.map(
    		new Function<FullItem, Result>(){
    			public Result apply(FullItem fullItem) throws Throwable {
    				return ok(Json.toJson(fullItem));
    			};
    		}
    	);
    	
    }

}
