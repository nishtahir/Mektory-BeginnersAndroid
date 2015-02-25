package com.example;

public class MyGameApplication {

    public MyGameApplication(){

        // create a new Hero object
        Hero hiro = new Hero();

        // Since there is no toString method in
        // the Hero class
        // The toString method in Entity is called
        System.out.println(hiro);

        // draw hero
        hiro.draw();


        BadGuy drEvil = new BadGuy();
        drEvil.blowUp();

        // Polymorphism allows us instantiate our hero and bad guy
        // with thier super class type
        // It's like magic
        Entity entity = new Hero();
        Entity entity2 = new BadGuy();
        Entity[] everythingInMyGame = {entity, entity2};

        for(Entity e : everythingInMyGame){

            // Here we check if they implement Moveable
            if(e instanceof Movable){
                ((Movable) e).moveLeft();
            }
        }
    }

    public static void main(String[] args){
        new MyGameApplication();
    }
}
