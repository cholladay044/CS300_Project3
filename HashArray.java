/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs300_project3;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Cole
 */
public class HashArray {
    final private int DEFAULT_LENGTH = 200;
    final private int MAXIMUM_LENGTH = 10000;
    final private double LOAD_FACTOR_THRESHOLD = 0.75;
    private Person[] list;
    private int length;
    private int size;
    private boolean valueFound;
    
    
    public HashArray(){
       this.length = this.DEFAULT_LENGTH;
       this.list = new Person[this.length];
       for(int i=0; i<this.length; i++){
           this.list[i] = null;
       }
    }
    public HashArray(int length){
       if(length > this.MAXIMUM_LENGTH){
           this.length = this.MAXIMUM_LENGTH;
       }
       else if (length < this.DEFAULT_LENGTH){
           this.length = this.DEFAULT_LENGTH;
       }
       else{
           this.length = length;
       }
       this.list = new Person[this.length];
       for(int i=0; i<length; i++){
           this.list[i] = null;
       }
    }
    public HashArray(ArrayList<Person> list){
        if(list.size() < this.DEFAULT_LENGTH){
            this.length = this.DEFAULT_LENGTH;
        }
        else if(list.size() >= this.MAXIMUM_LENGTH){
            this.length = this.MAXIMUM_LENGTH+this.MAXIMUM_LENGTH;
        }
        else{
            this.length = list.size()*2;
        }
        this.list = new Person[this.length];
        for(int i=0; i<list.size(); i++){
            insert(list.get(i));
        }
    }
    public boolean insert(Person p){
        if(checkLoad()){
            expand();
        }
        boolean flag = false;
        int index = hash(p);
        if(list.length == 0){
            list[index] = p;
            flag = true;
        }
        else{
            while(list[index] != null){
                if(index > list.length){
                    if(checkLoad()){
                        expand();
                    }
                    else{
                        index = 0;
                    }
                }
                System.out.println("Collision occured with value: " +index);
                index = index +1;
            }
            list[index] = p;
            flag = true;
        }
        size++;
        return flag;
    }
    public boolean remove(Person p){
        int index = search(p);
        if(valueFound){
            list[index] = null;
            return true;
        }
        return false;
    }
    public int search(Person p){
        int index = hash(p);
        for(int i=index; i<list.length-1; i++){
            if(list[i] !=null && list[i].equals(p)){
                valueFound = true;
                break;
            }
            else{
                valueFound = false;
                System.out.println(p.getFirstName() + " does not exist in list");
            }
        }
        return index;
    }
    public boolean replace(Person personToAdd, Person personToReplace){
        int index = search(personToReplace);
        if(valueFound){
            list[index] = personToAdd;
            return true;
        }
        return false;
    }
    public void display(){
        System.out.println("----------HASHARRAY----------");
        System.out.println("Index:       Person:     ");
        for(int i=0; i<list.length; i++){
            if(list[i] != null){
                System.out.println(i +"     "+list[i].getFirstName());
            }
        }
        System.out.println("-----------------------------");
        System.out.println("Table Size: " +list.length);
        System.out.println("Number of Keys: " +size);
        System.out.println("Current Load Factor: " + (double)(size/list.length));
    }
    private String reverse(String str){
        String result="";
        Stack s = new Stack();
        char[] charArray = str.toCharArray();
        for(int i=0; i<charArray.length; i++){
            s.push(charArray[i]);
        }
        while(!s.empty()){
            result += s.pop();
        }
        return result;
    }
    private int findPrime(){
        int result=0;
        int[] primeNumbers = new int[10];
        int decrement = primeNumbers.length -1;
        int num = length+1;
        int max = length+50;
        int count=0;
        for(int i=num; i<max; i++){
            if(checkPrime(i) && count < primeNumbers.length){
                primeNumbers[count] = i;
                count++; 
            }      
        }
        while(primeNumbers[decrement] == 0){
            decrement--;
        }
        result = primeNumbers[decrement];
        return result;
    }
    /*
    Code reference:
    https://www.guru99.com/prime-number-program-java.html
    */
    private boolean checkPrime(int num){
        int max = num/2;
        boolean flag = true;
        for(int i=2; i<=max; i++){
            if(num%i == 0){
               flag = false;
               break; 
            } 
        }
        return flag;
    }
    private boolean checkLoad(){
        double currentLoad = size/length;
        if(currentLoad > LOAD_FACTOR_THRESHOLD)
            return true;
        return false;
    }
    private void expand(){
        Person[] temp = new Person[length*2];
        for(int i=0; i<list.length; i++){
            temp[i] = list[i];
        }
        this.list = temp;
        length = list.length;
    }
    private int hashKey(Person p){
        String firstName = p.getFirstName().toLowerCase().replace(" ", "");
        String lastName = p.getLastName().toLowerCase().replace(" ", "");
        String temp = firstName + reverse(lastName);
        int hashCode = 0;
        for (int i=0; i<temp.length();i++){
            hashCode = temp.charAt(i) + 41*hashCode;
        }
        return Math.abs(hashCode);
    }
    private int hash(Person p){
        int hashKey = hashKey(p);
        int index = Math.abs(((3*hashKey+1)%findPrime())%length);
        return index;
    }
}
