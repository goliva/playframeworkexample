package controllers;

import model.FullItem;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;



public class Application extends Controller {

	@Transactional(readOnly=false)
	public static Result index(Long itemID) throws Exception {
    	FullItem response = FullItem.get(itemID);
    	return ok(Json.toJson(response));
    }

}
