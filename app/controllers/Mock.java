package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Mock extends Controller {

	public static Result item(Long id) throws InterruptedException {
		Thread.sleep(10);
		return ok(Json.parse("{\"id\":12324, \"user_id\": 123244, \"title\":\"Ford Focus\", \"price\":120000, \"currency\": \"$\"}"));
	}
	
	public static Result picture(Long id) throws InterruptedException {
		Thread.sleep(10);
		response().setHeader("Cache-Control", "max-age=86400");
		return ok(Json.parse("{\"id\":12324, \"url\":\"http://mla-s2-p.mlstatic.com/ford-focus-20-full-16529-MLA20123253177_072014-O.jpg\"}"));
	}
	
	public static Result user(Long id) throws InterruptedException {
		Thread.sleep(10);
		return ok(Json.parse("{\"id\":123244, \"ranking\":\"green\"}"));
	}

}
