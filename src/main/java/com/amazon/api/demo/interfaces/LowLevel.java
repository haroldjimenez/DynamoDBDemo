package com.amazon.api.demo.interfaces;

import java.util.HashMap;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public class LowLevel {
	
	public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2)
				// .withCredentials(new ProfileCredentialsProvider("harold.jimenez"))
				.build();

        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Cedula", new AttributeValue().withS("786"));
        key.put("Nombre", new AttributeValue().withS("Angelica"));

        GetItemRequest request = new GetItemRequest()
            .withTableName("Persona")
            .withKey(key);

        try {
            GetItemResult result = client.getItem(request);
            if (result != null && result.getItem() != null) {
                AttributeValue age = result.getItem().get("Edad");
                System.out.println("The Person is " + age.getN());
            } else {
                System.out.println("No matching Person was found");
            }
        } catch (Exception e) {
            System.err.println("Unable to retrieve data: ");
            System.err.println(e.getMessage());
            
        } finally {
        	client.shutdown();
		}
	}
}
