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
class node<PageNo, Data>{
    PageNo key;
    Data value;
    node(PageNo p, Data d){
        this.key = p;
        this.value = d;
    }
}

class Bucket<PageNo, Data>{
    List<node> list = new LinkedList<node>();
    
    void add(PageNo p, Data Value){
        boolean flag = false;
        node newNode = new node(p, Value);
        
        for(int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).key == p){
                this.list.set(i, newNode);
                System.out.println("Page No." + p +" is updated in Memory");
                flag = true;
                break;
            }
        }
        if(!flag){
            this.list.add(newNode);
            System.out.println("Page No." + p +" is added in Memory");
        }
    }
    
    void delete(PageNo p){
        for(int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).key == p){
                node temp = this.list.get(i);
                temp = null;
                this.list.remove(i);
                break;
            }
        }
    }
    
    Data get(PageNo p){
        for(int i = 0; i <this.list.size();i++){
            if(this.list.get(i).key == p){
                return (Data)this.list.get(i).value;
            }
        }
        return null;
    }
    
}

class Cache<PageNo, Data>{
    int pageFrame;
    int hit;
    int miss;
    Vector<PageNo> deque;
    Bucket [] bucketList;
    
    Cache(int f){
        this.pageFrame = f;
        this.hit = 0;
        this.miss = 0;
        this.deque = new Vector<PageNo>(this.pageFrame);
        this.bucketList = new Bucket[this.pageFrame];
        for(int i = 0; i < this.pageFrame; i++){
            Bucket b = new Bucket();
            this.bucketList[i] = b;
        }
    }
    
    void addPage(PageNo p, Data value){
        int index = -1;
        for(int i = 0; i <this.deque.size(); i++){
            if(this.deque.get(i) == p){
                Data temp = getPage(p);
                if(temp.equals(value)){
                    index = i;
                }
                break;
            }
        }
        if(index != -1){
            this.deque.remove(index);
            this.deque.add(p);
            this.hit++;
            System.out.println("No page fault");
        }
        else{
            System.out.println("Page Fault Occured");
            if(this.deque.size() == this.pageFrame){
                PageNo temp = this.deque.get(0);
                deletePage(temp);
            }
            this.deque.add(p);
            this.miss++;
            int hash = p.hashCode() % this.pageFrame;
            this.bucketList[hash].add(p, value);
        }
    }
   
    void deletePage(PageNo p){
        int index = -1;
        if(this.deque.size() == 0){
            System.out.println("Page Table is empty");
            return;
        }
        
        for(int i = 0; i < this.deque.size(); i++){
            if(this.deque.get(i) == p){
                index = i;
                break;
            }
        }
        if(index != -1){
            this.deque.remove(index);
            int hash = p.hashCode() % this.pageFrame;
            this.bucketList[hash].delete(p);
        }
        else{
            System.out.println("Page" + p+ " not found in Cache");
        }
    }
    
    
    Data getPage(PageNo p){
        for(int i = 0; i < this.deque.size(); i++){
            if(this.deque.get(i) == p){
                int hash = p.hashCode() % this.pageFrame;
                return (Data)this.bucketList[hash].get(p);
            }
        }
        return null;
    }
    
    int getMiss(){
        return this.miss;
    }
    
    int getHit(){
        return this.hit;
    }
}


class hacker {       
    static int pageSize = 10;
    public static void main(String args[]) {
        Cache<String, String> lru = new Cache<String, String>(hacker.pageSize);
        
        lru.addPage("1", "abc");
        System.out.println("Miss: " + lru.getMiss());
        System.out.println("Hit: " + lru.getHit());
        lru.addPage("2", "chd");
        System.out.println("Miss: " + lru.getMiss());
        System.out.println("Hit: " + lru.getHit());
        lru.addPage("2", "chd");
        System.out.println("Miss: " + lru.getMiss());
        System.out.println("Hit: " + lru.getHit());
        lru.addPage("2", "azs");
        System.out.println("Miss: " + lru.getMiss());
        System.out.println("Hit: " + lru.getHit());
        System.out.println(lru.getPage("2"));
        lru.deletePage("2");
        System.out.println(lru.getPage("2"));
           
    }
}

