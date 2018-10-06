package co.edureka.edureka29sep2018;

import java.io.Serializable;

// Serializable is Java's Marker Interface for Serialization
// If we wish to pass UserDefined Object in another activity, it must be serializable
public class User implements Serializable{

    public String name;
    public int age;

    public User(){

    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }
}
