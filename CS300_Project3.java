/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs300_project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CS300_Project3 {
    public static void main(String[] args) {
        ArrayList<Person> people = readFile();
        HashArray hashArray = new HashArray(people);
        hashArray.display();
             
    }
    public static ArrayList<Person> readFile(){
        ArrayList<Person> result = new ArrayList<>();
        File f = new File("person.csv");
        try{
            Scanner read = new Scanner(f);
            while(read.hasNextLine()){
                String temp = read.nextLine();
                String[] tempArr = temp.split(",");
                int id = Integer.parseInt(tempArr[0]);
                String firstName = tempArr[1];
                String lastName = tempArr[2];
                int age = Integer.parseInt(tempArr[3]);
                Person p = new Person(id, firstName, lastName, age);
                result.add(p);
            }
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
