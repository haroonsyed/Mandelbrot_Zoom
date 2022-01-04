package me.Sshawarma;

public class Boundary {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public Boundary(int x1, int x2, int y1, int y2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    public void print() {
        System.out.println("X1 "+x1 + " Y1 " + y1);
        System.out.println("X2 "+x2 + " Y2 " + y2);
    }
}
