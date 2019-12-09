package com.amazon.api.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.api.demo.service.DynamoService;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

@RestController
@RequestMapping(path = "/dynamodb")
public class DynamoController {
	
	private DynamoService dynamoService;
	
	@Autowired
	public DynamoController(DynamoService dynamoService) {
		this.dynamoService = dynamoService;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/getTables")
	public String getTables() {

		return dynamoService.getTables();
	}
	
	public static void main(String[] args) {
		new DynamoController(new DynamoService(AmazonDynamoDBClientBuilder.standard()
				.withRegion(Regions.US_EAST_2)
				.build())).getTables();
		
	}
	


}
