/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs300_project3;

/**
 *
 * @author Cole
 */
public class Person {
    private int id;
    private String fName;
    private String lName;
    private int age;
    
    Person(int id, String fName, String lName, int age){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }
    public int getId(){
        return this.id;
    }
    public String getFirstName(){
        return fName;
    }
    public String getLastName(){
        return lName;
    }
    public int getAge(){
        return age;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setFirstName(String fName){
        this.fName = fName;
    }
    public void setLastName(String lName){
        this.lName = lName;
    }
    public void setAge(int age){
        this.age = age;
    }
    @Override
    public String toString(){
        return "ID: " +this.getId()+"\n"+
                "First Name: " +this.getFirstName()+"\n"+
                "Last Name: " +this.getLastName()+"\n"+
                "Age: " +this.getAge();
    }
}
