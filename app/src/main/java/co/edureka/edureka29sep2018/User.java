package co.edureka.edureka29sep2018;

import java.io.Serializable;

// Serializable is Java's Marker Interface for Serialization
// If we wish to pass UserDefined Object in another activity, it must be serializable
public class User implements Serializable{

    public int image;
    public String name;
    public String email;

    public int age;

    public User(){

    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public User(int image, String name, String email){
        this.image = image;
        this.name = name;
        this.email = email;
    }
}
