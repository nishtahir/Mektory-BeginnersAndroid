package com.example;

/**
 * Created by Nish on 2/21/15.
 * Base class for game objects
 *
 * In this case it's called the
 * superclass of hero and badguy
 */
public abstract class Entity {
    int x;
    int y;

    //Annotation
    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }

    public void draw(){
        System.out.print("Entity " +
                "is drawn");
    }

    public void blowUp(){
        // set timer
        // create bomb

        System.out.println("Entity" +
        " has raised has the roof");
    }
}

