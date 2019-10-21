/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;
import java.util.*;
/**
 *
 * @author shsaini
 */

class Template<T>{
    T obj;
    Template(T obj){
        this.obj = obj;
    }
    T get_object(){
        return this.obj;
    }

}

class CompareFunc implements Comparator<Template> {
    int h1 = 0, h2 = 0;
    public int compare(Template t1, Template t2){
        if (t1.get_object() instanceof person){
            person p1 = (person)t1.get_object();
            h1 = p1.get_height();
        }
        else if(t1.get_object() instanceof Robot){
           Robot r1 = (Robot)t1.get_object();
           h1 = r1.get_height();
        }
        if(t2.get_object() instanceof person){
            person p2 = (person)t2.get_object();
            h2 = p2.get_height();
        }
        else if(t2.get_object() instanceof Robot){
            Robot r2 = (Robot)t2.get_object();
            h2 = r2.get_height();
        }
        return h2 - h1;
    }
}

class person{
    String name;
    int height;
    int age;
    String food_name;
    int total_eat_food;
    int walk_dist;
    int sleep_hrs;
    void set_walk(){
        this.walk_dist += 1;
    }
    int get_height(){
        return this.height;
    }
    void set_eat(String str){
        this.food_name = str;
        this.total_eat_food += 1;
    }
    void set_sleep(){
        this.sleep_hrs += 1;   
    }
    int get_walk(){
        System.out.println("walk distance" + this.walk_dist);
        return this.walk_dist;
    }
    int get_eat(){
        System.out.println("Food name" + this.food_name + "Total" + this.total_eat_food);
        return this.total_eat_food;
    }
    int get_sleep(){
        System.out.println("sleep hrs" + this.sleep_hrs);
        return this.sleep_hrs;
    }
}

class Robot{
    String name;
    int height;
    int walk_dist;
    
    public int get_height(){
        return this.height;
    }    
    
    void set_walk(){
        this.walk_dist += 1;
    }
    
    int get_walk(){
        System.out.println("walk distance" + this.walk_dist);
        return this.walk_dist;
    }
}

class Room{
    int capacity;
    public PriorityQueue <Template> pq = new PriorityQueue<Template>(new CompareFunc());
    
    void add_pq(Template a){
        if(this.pq.size() < this.capacity){
            this.pq.add(a);
        }
        else{
            System.out.println("Queue is full " + pq.size());
        }
    }
    
    void remove_pq(){
        if(!this.pq.isEmpty()){
            this.pq.remove();
        }
        else{
            System.out.println("Queue is empty");
        }
    }
    
     public PriorityQueue <Template> get_pq(){
        //System.out.println(pq);
        return this.pq;
    }
 
}


public class test {
   
    public static void main(String[] args){
        
        person p1 = new person();
        Template <person> t1 = new Template<person>(p1);
        person person_obj = t1.get_object();
        
        Room r1 = new Room();
        r1.capacity = 10;
        person_obj.name = "A";
        person_obj.height = 1;
        
        r1.add_pq(t1);
        person_obj.name = "B";
        person_obj.height = 6;       
        r1.add_pq(t1);
        person_obj.name = "C";
        person_obj.height = 4;
        r1.add_pq(t1);
        person_obj.name = "D";
        person_obj.height = 10;
        r1.add_pq(t1);
        
        
        Robot rt = new Robot();
        Template <Robot> t2 = new Template<Robot>(rt);
        Robot robot_obj = t2.get_object();
        robot_obj.name = "R_A";
        robot_obj.height = 10;
        r1.add_pq(t2);
        
        robot_obj.name = "R_B";
        robot_obj.height = 2;
        r1.add_pq(t2);   
        
        robot_obj.name = "R_C";
        robot_obj.height = 1;
        r1.add_pq(t2);
        
        if(r1.pq.peek().get_object() instanceof person){
            person p2 = (person)r1.pq.peek().get_object();
            System.out.println("Type: Person\nName: " + p2.name + "\nHeight: " + p2.get_height());
        }
        if(r1.pq.peek().get_object() instanceof Robot){
           Robot r2 = (Robot)r1.pq.peek().get_object();
           System.out.println("Type: Robot\nName: " + r2.name + "\nHeight: " + r2.get_height());
        }
    }
    
}
