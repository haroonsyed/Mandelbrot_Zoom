package me.Sshawarma;

public class Main {

    public static void main(String[] args) {

        MandelbrotGenerator gen = new MandelbrotGenerator();

        for(int i=0; i<500; i++) {
            gen.generateFrame(i);
        }

    }

}
