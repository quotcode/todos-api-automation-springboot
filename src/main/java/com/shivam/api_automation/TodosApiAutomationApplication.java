package com.shivam.api_automation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.shivam.utilities.APIAutomationUtility;


@SpringBootApplication
public class TodosApiAutomationApplication {
	public static final Logger LOGGER = LogManager.getLogger(TodosApiAutomationApplication.class);
	
	public static final double LAT_1 = -40;
	public static final double LAT_2 = 5;
	public static final double LNG_1 = 5;
	public static final double LNG_2 = 100;
	
	public static final double COMPLETION_PERCENTAGE = 59.0;
	

	public static void main(String[] args) throws IOException {
		
		Map<Integer, Integer> todosAssignedToUsers = new HashMap<>();
		Map<Integer, Integer> todosCompletedByUsers = new HashMap<>();
		Map<Integer, Double> todosResult = new HashMap<>();
		
		
		SpringApplication.run(TodosApiAutomationApplication.class, args);
		
		// provided URLs of the data source
		URL todosUrl = new URL("https://jsonplaceholder.typicode.com/todos");
		URL usersURL = new URL("https://jsonplaceholder.typicode.com/users");
		
		// reading the JSON from given URL
		JsonNode todos = APIAutomationUtility.getJsonFromURL(todosUrl);
		JsonNode users = APIAutomationUtility.getJsonFromURL(usersURL);

		// finding users who live in FanCode city
		List<Integer> fancodeUsers = APIAutomationUtility.getUsersOfGeoLocation(users, LAT_1, LAT_2, LNG_1, LNG_2);
		
		// logic to fetch todos assigned to a given user living in a FanCode city
		for(int i=0; i<todos.size() ; i++) {
			int userId = todos.get(i).get("userId").asInt();
			if(fancodeUsers.contains(userId) ) {
				todosAssignedToUsers.put(userId, todosAssignedToUsers.getOrDefault(userId, 0)+1);
				if(todos.get(i).get("completed").asBoolean()) {
					todosCompletedByUsers.put(userId, todosCompletedByUsers.getOrDefault(userId, 0)+1);
				}
			}
		}
		// logic to calculate completion percentage
		for(Integer user: todosAssignedToUsers.keySet()) {
			double percentage = todosCompletedByUsers.get(user)/ (double) (todosAssignedToUsers.get(user)) * 100;
			if(percentage>COMPLETION_PERCENTAGE) {
				todosResult.put(user, percentage);
			}
		}
		LOGGER.info("------------------ Results! ------------------");
		LOGGER.info(todosResult.toString());
	}

}
