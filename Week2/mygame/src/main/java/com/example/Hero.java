package com.example;

/**
 * Created by Nish on 2/21/15.
 */
public class Hero extends Entity implements Movable{
    public Hero(){
        x = 10;
        y = 20;
    }

    /**
     * Overriding a method
     */
    @Override
    public void draw(){
        System.out.println("Hero"
        + " is drawn");
    }

    @Override
    public void moveLeft() {
        x--;
        System.out.println("X is now: " + x);
    }

    @Override
    public void moveRight() {
        x++;
        System.out.println("X is now: " + x);
    }
}
