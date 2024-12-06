package com.abc.Spring3;

public class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("car is running in the road");
    }
}
