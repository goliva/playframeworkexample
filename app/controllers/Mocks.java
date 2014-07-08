package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Mocks extends Controller {

	public static Result item(String id) {
		return ok(Json.parse("{\"id\":12324, \"user_id\": 123244, \"title\":\"Ford Focus\", \"price\":120000, \"currency\": \"$\"}"));
	}
	
	public static Result picture(String id) {
		return ok(Json.parse("{\"id\":12324, \"url\":\"http://mla-s2-p.mlstatic.com/ford-focus-20-full-16529-MLA20123253177_072014-O.jpg\"}"));
	}
	
	public static Result user(String id) {
		return ok(Json.parse("{\"id\":123244, \"ranking\":\"green\"}"));
	}
}
