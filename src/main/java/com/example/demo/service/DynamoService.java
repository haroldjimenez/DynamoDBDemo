package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

@Service
public class DynamoService {
	
	private AmazonDynamoDB amazonDynamoDb;
	
	@Autowired
	public DynamoService(AmazonDynamoDB amazonDynamoDb) {
		this.amazonDynamoDb=amazonDynamoDb;
	}

	public String getTables() {
		ListTablesResult listTables = amazonDynamoDb.listTables();
		return listTables.getTableNames().toString();
	}

}
