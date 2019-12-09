package com.amazon.api.demo.interfaces;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.GetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class Document {

    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB docClient = new DynamoDB(client);

        Table table = docClient.getTable("Persona");
        GetItemOutcome outcome = table.getItemOutcome("Cedula", "123", "Nombre", "harold");

        int year = outcome.getItem().getInt("Edad");
        String apellido = outcome.getItem().getString("Apellido");
        System.out.println(apellido + " is " + year);
    }
}

