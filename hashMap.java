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

class Data<K, V>{
    K key;
    V Value;
    Data(K k, V v){
        this.key = k;
        this.Value = v;
    }
}

class Bucket<K ,V>{
    public List<Data> list = new LinkedList<Data>();
    
    void add(K key, V Value){
        boolean flag = false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).key.equals(key)){
                flag = true;
                break;
            }
        }
        if(!flag){
            Data d = new Data(key, Value);
            list.add(d);
        }
        else{
            System.out.println("Key" + key + " already exists");
        }
    }
    void delete(K key){
        boolean flag = false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).key.equals(key)){
                Data d = list.get(i);
                d = null;
                list.remove(i);
                System.out.println("key " + key + " is removed");
            }
            else{
                flag = true;
            }
        }
        if(flag){
            System.out.println("key " + key + " is not found");
        }
    }
    
    V get(K key){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).key.equals(key)){
                return (V)list.get(i).Value;
            }
        }
        return null;
    }
    
}

class hashMap<K, V>{
    int capacity;
    Bucket arr[];
    hashMap(int c){
        this.capacity = c;
        arr = new Bucket[this.capacity];
        for(int i = 0; i < this.capacity; i++){
            Bucket b = new Bucket();
            arr[i] = b;
        }
    }
    void add(K key, V Value){
        int hash = key.hashCode() % this.capacity;
        System.out.println(hash);
        arr[hash].add(key, Value);
    }
    void delete(K key){
        int hash = key.hashCode() % this.capacity;
        arr[hash].delete(key);
    }
    V get(K key){
        int hash = key.hashCode() % this.capacity;
        return (V)arr[hash].get(key);
    }
}

 class hacker {
        static int capacity = 10;
        
        public static void main(String args[]) {
            hashMap <String, String> hm = new hashMap(hacker.capacity);
            
            String key1 = new String("ABC");
            String value1 = new String("123");
            String key2 = new String("XYZ");
            String value2 = new String("456");
            
            hm.add(key1, value1);
            hm.add(key2, value2);
            
            System.out.println((String)hm.get(key2));
            
            hm.delete(key2);
            if(hm.get(key2) == null)
                System.out.println("not present");
            else
                System.out.println((String)hm.get(key2));
           
        }
}

