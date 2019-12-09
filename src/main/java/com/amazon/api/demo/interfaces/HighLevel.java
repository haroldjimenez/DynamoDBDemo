package com.amazon.api.demo.interfaces;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import java.util.HashMap;

public class HighLevel {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";




    private static AmazonDynamoDB getClientAmazon(Regions usEast2) {
        return AmazonDynamoDBClientBuilder.standard().withRegion(usEast2)
                                          // .withCredentials(new ProfileCredentialsProvider("harold.jimenez"))
                                          .build();
    }

    public static void main(String[] args) {

       // objectPersistenceExample();
        streamPersistenceExample();
    }

    private static void objectPersistenceExample() {

        AmazonDynamoDB clientOhio = getClientAmazon(Regions.US_EAST_2);

        DynamoDBMapper mapperOhio = new DynamoDBMapper(clientOhio, DynamoDBMapperConfig.builder().build());

        Persona newPersona = new Persona();
        newPersona.setCedula("132");
        newPersona.setNombre("Carlos");
        newPersona.setApellido("Obama");
        newPersona.setEdad(59);

        try {
            mapperOhio.save(newPersona);
            Persona result = mapperOhio.load(newPersona);
            if (result != null) {
                imprimir(result.getNombre() + " " + result.getApellido() + " is " +
                                   result.getEdad());
            }
            else {
                imprimir("No matching song was found");
            }
        }
        catch (Exception e) {
            imprimir("Unable to retrieve data: ");
            System.err.println(e.getMessage());
        }
    }

    private static void streamPersistenceExample() {
        AmazonDynamoDB clientOhio = getClientAmazon(Regions.US_EAST_2);
        AmazonDynamoDB clientCalifornia = getClientAmazon(Regions.US_WEST_1);


        DynamoDBMapper mapperOhio = new DynamoDBMapper(clientOhio);
        DynamoDBMapper mapperCalifornia= new DynamoDBMapper(clientCalifornia);



        Persona newPersona = new Persona();
        newPersona.setCedula("1234455");
        newPersona.setNombre("Cristian");
        newPersona.setApellido("Nodal");
        newPersona.setEdad(32);

        try {
            mapperOhio.save(newPersona);

            imprimir(Regions.US_EAST_2 + " Ingresó persona:" + newPersona.toString());

            Persona result = mapperCalifornia.load(newPersona, DynamoDBMapperConfig.builder().withConsistentReads(
                    ConsistentReads.CONSISTENT).build());
            if (result != null ) {
                imprimir(Regions.US_WEST_1+" "+result.getNombre() + " " + result.getApellido() + " is " +
                                   result.getEdad());
            }
            else {
                imprimir("No matching person was found");
            }
        }
        catch (Exception e) {
            imprimir("Unable to retrieve data: ");
            System.err.println(e.getMessage());
        }
    }

    private static void imprimir(String mensaje) {
        System.out.println(ANSI_RED + mensaje+ ANSI_RESET);
    }
}
