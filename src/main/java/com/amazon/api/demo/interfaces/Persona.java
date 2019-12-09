package com.amazon.api.demo.interfaces;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Persona")
public class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    @DynamoDBHashKey(attributeName="Cedula")
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    @DynamoDBRangeKey(attributeName="Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @DynamoDBAttribute(attributeName = "Apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @DynamoDBAttribute(attributeName = "Edad")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula='" + cedula + '\'' + ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' + ", edad=" + edad + '}';
    }
}
