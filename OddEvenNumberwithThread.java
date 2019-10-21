/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;
import java.lang.*;

/**
 *
 * @author shsaini
 */
class ThreadDemo implements Runnable {
    static Object lock = new Object();
    static int i = 1;
    public void run(){  
        while(i <= 10){
            if(i % 2 == 0){
                synchronized(lock){
                    System.out.println(i);
                    i = i + 1;
                    try {
                        lock.wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            
            }
            else if(i % 2 == 1){
                synchronized(lock){
                    System.out.println(i);
                    i = i + 1;
                    lock.notify();
                }
            }
            
        }
    }
}

public class EvenOdd {
    public static void main(String args[]){
        Thread t1 = new Thread(new ThreadDemo());
        Thread t2 = new Thread(new ThreadDemo());
        t1.start();
        //System.out.println("wait");
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            e.getMessage();
        }
    }
}
