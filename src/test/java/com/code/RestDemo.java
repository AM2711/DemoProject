package com.code;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestDemo {
	
	Response response;
	User userResponse;
	Posts postResponse;
	User user = new User();
	Posts post = new Posts();
  
  @BeforeMethod
  public void setUp() {
	  
	  String email = ("AutoTest" + Math.random() + "_" + "@gmail.com");
	  user.setEmail(email);
	  user.setGender("male");
	  user.setName("name");
	  user.setStatus("active");
	  post.setBody("body");
	  post.setTitle("title");
	  }
  
  @Test(priority=1)
  public void createUser() {
	  response = RestAssured.given()
			  .contentType(ContentType.JSON)
			  .baseUri("https://gorest.co.in/public/v2")
			  .filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
			  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
			  .when()
			  .body(user)
			  .post("/users"); 
	 Assert.assertEquals(201, response.getStatusCode());		  
	 userResponse = response.getBody().as(User.class);	  
	 validateResponse(userResponse);  
	  
  }


@Test(priority=2)
public void updateUser() {
	
	 user.setName("name2");
	 response = RestAssured.given()			 
			  .contentType(ContentType.JSON)
			  .baseUri("https://gorest.co.in/public/v2")
			  .filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
			  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
			  .when()
			  .body(user)
			  .put("/users/"+userResponse.getId());
	  Assert.assertEquals(200, response.getStatusCode());		  
	  userResponse = response.getBody().as(User.class);	  
	  validateResponse(userResponse); 
	  
	  
  }

@Test(priority=3)
public void createPost() {
		 response = RestAssured.given()
				  .contentType(ContentType.JSON)
				  .baseUri("https://gorest.co.in/public/v2")
				  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
				  .when()
				  .body(post)
				  .filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
				  .post("/users/"+userResponse.getId()+ "/posts");		
		  Assert.assertEquals(201, response.getStatusCode());		  
		 postResponse = response.getBody().as(Posts.class);	  
	  
}

@Test(priority=4)
public void createComments() {
		
		   JSONObject obj 	=  new 	JSONObject();
		   obj.put("name",user.getName());
		   obj.put("email",user.getEmail());
		   obj.put("body",post.getBody());		   
			
		 response = RestAssured.given()
				  .contentType(ContentType.JSON)
				  .baseUri("https://gorest.co.in/public/v2")
				  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
				  .body(obj.toString())
				  .filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
				  .post("/posts/"+postResponse.getId()+"/comments");
		Assert.assertEquals(201, response.getStatusCode());
		postResponse = response.getBody().as(Posts.class);	  
		validatePostResponse(postResponse);
	  
}

@Test(priority=5)
  public void deleteUser() {
	  response = RestAssured.given()
			  .contentType(ContentType.JSON)
			  .baseUri("https://gorest.co.in/public/v2")
			  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
			  .filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
			  .delete("/users/"+userResponse.getId());
	Assert.assertEquals(204, response.getStatusCode());
	validateDeleteResponse(); 
	  
  }
  
public void validateDeleteResponse() {
	// TODO Auto-generated method stub
	  response = RestAssured.given()
			  .contentType(ContentType.JSON)
			  .baseUri("https://gorest.co.in/public/v2")
			  .header("Authorization","Bearer 1c5c877a2566f559dda79d464039576f4d32e5702dadf13f110a3b01cab2c02e")
			  .log().all()
			  .get("/users/"+userResponse.getId());
	Assert.assertEquals(404, response.getStatusCode());		 
	
}

public void validateResponse(User userResponse) {
		// TODO Auto-generated method stub
	Assert.assertEquals(userResponse.getName(), user.getName());
	Assert.assertEquals(userResponse.getEmail(), user.getEmail());
	Assert.assertEquals(userResponse.getGender(), user.getGender());
	Assert.assertEquals(userResponse.getStatus(), user.getStatus());

	}
  
  public void validatePostResponse(Posts postResponse) {
		// TODO Auto-generated method stub
	Assert.assertEquals(postResponse.getName(), user.getName());
	Assert.assertEquals(postResponse.getBody(), post.getBody());
	}
	
}
