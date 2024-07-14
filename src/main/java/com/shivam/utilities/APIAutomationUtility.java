package com.shivam.utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class APIAutomationUtility {
	
	public static JsonNode getJsonFromURL(URL url) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(url);
	}


	public static List<Integer> getUsersOfGeoLocation(JsonNode users, double lat1, double lat2, double lng1, double lng2){
		List<Integer> cityUsers = new ArrayList<>();
		for(int i=0; i<users.size() ; i++) {
			double lat = users.get(i).get("address").get("geo").get("lat").asDouble();
			double lng = users.get(i).get("address").get("geo").get("lng").asDouble();
			boolean belongsToFanCodeCity = (lat>lat1 && lat<lat2 && lng>lng1 && lng<lng2);

			if(belongsToFanCodeCity) {
				cityUsers.add(users.get(i).get("id").asInt());
			}

		}
		return cityUsers;
	}
}
