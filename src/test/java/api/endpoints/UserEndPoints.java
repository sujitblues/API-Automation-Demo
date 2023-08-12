package api.endpoints;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.user;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(user payload)
	{
		Response response=given()
				.header("Content-Type","application/json")
				//.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.post_url);
			
			return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return response;
	}	
	public static Response updateUser(String userName,user payload)
	{
		Response response=given()
				.header("Content-Type","application/json")
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				
			.when()
				.put(Routes.update_url);
			
			return response;
	}
	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
		.when()
			.get(Routes.delete_url);
		
		return response;
	}
}
