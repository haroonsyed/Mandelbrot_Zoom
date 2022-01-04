package me.Sshawarma;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MandelbrotThread extends Thread{

    Config config = Config.getConfig();
    final int height = config.height;
    final int width = config.width;
    final double centerX = config.centerX;
    final double centerY = config.centerY;
    final Color targets[] = config.targets;
    private BufferedImage img;
    private Boundary bound;

    public MandelbrotThread(Boundary bound, BufferedImage buffer) {
        this.bound = bound;
        this.img = buffer;
    }

    //returns pixel based on gradient depending on how close to target color it is
    public Color getPixelColor(Color targetColor,int n, int iterLimit) {
        return new Color(
                n*targetColor.getRed()/iterLimit,
                n* targetColor.getGreen()/iterLimit,
                n* targetColor.getBlue()/iterLimit
        );
    }

    @Override
    public void run() {
        double range = 4*Math.exp(-bound.frameNumber*0.1);

        //DO NOT CHANGE, scales x to stop stretching
        double rangeX = range*height/width;

        for (int xCoord=bound.x1; xCoord<bound.x2; xCoord++) {
            for(int yCoord=bound.y1; yCoord<bound.y2; yCoord++){

                // Remember that complex numbers are of the form a+bi
                // a is x component
                // b is y component
                // Original denotes this is "C"
                double aOriginal = (((double)xCoord)/width) * range + (centerX-range/2);
                double bOriginal = (((double)yCoord)/height) * rangeX + (centerY-rangeX/2);

                // These are the values that will be iterated on
                double a = 0;
                double b = 0;

                // iteration loop of determining if the number converges
                // Test each point in space and determine convergence
                int n = 0;
                int iterLimit = 100+(bound.frameNumber);
                int escapeValue = 2;
                for(n=0;n<iterLimit; n++) {
                    // Determine iterated x and y axis value.
                    // Remember f(0) = C always

                    // Z^2 + C = a^2 + 2abi - b^2 + aOriginal + bOriginali
                    // = (a^2 - b^2 + a) + (2abi + bi)
                    double aa = a*a;
                    double ab = a*b;
                    double bb = b*b;
                    a = (aa - bb) + aOriginal;
                    b = (2*ab) + bOriginal;

                    //Does it converge?
                    if(Math.abs(a) > escapeValue && Math.abs(b) > escapeValue) {
                        break;
                    }

                }

                //Set color based on convergence
                Color c = null;

                if(n<iterLimit*0.2) {
                    c = getPixelColor(targets[0],n,iterLimit);
                }
                else if(n<iterLimit*0.4) {
                    c = getPixelColor(targets[1],n,iterLimit);
                }
                else if(n<iterLimit*0.6) {
                    c = getPixelColor(targets[2],n,iterLimit);
                }
                else if(n<iterLimit*0.8) {
                    c = getPixelColor(targets[3],n,iterLimit);
                } else {
                    c = getPixelColor(targets[4],n,iterLimit);
                }

                //Write to image
                img.setRGB(xCoord,yCoord,c.getRGB());

                //DEBUG INFO
//                System.out.println("-----------");
//                System.out.println(red);
//                System.out.println(green);
//                System.out.println(blue);

            }
        }
    }
}
