package com.example;

/**
 * Created by Nish on 2/21/15.
 */
public class BadGuy extends Entity {

    @Override
    public void blowUp() {
        super.blowUp();

        System.out.println("BadGuy" +
          " has raised has the roof");
    }
}
