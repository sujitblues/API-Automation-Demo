package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.user;
import io.restassured.response.Response;

import com.github.javafaker.Faker;

public class userTest {
	Faker faker;
	user userpayload;
	public static Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userpayload=new user();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
			
	}
	@Test(priority=1)
	public void testPostUser()
	{
		
		Response response=UserEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.contentType(), "application/json");
		
		
		
	}
	@Test(priority=2)
	public void testGetUserByNmae()
	{
			
		Response response=UserEndPoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	@Test(priority=4)
	public void testDeleteUser()
	{
			
		Response response=UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 204);
		
	}
}
