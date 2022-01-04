package me.Sshawarma;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MandelbrotGenerator {

    public void generateFrame(int n) {
        Config config = Config.getConfig();
        int width = config.width;
        int height = config.height;
        int threadCount = config.threadCount;
        Path path = config.path;

        ArrayList<Thread> threadPool = new ArrayList<>();
        BufferedImage buffer = new BufferedImage(config.width,config.height,BufferedImage.TYPE_INT_RGB);

        //Partition frame into threads and run them
        for(int threadNumber=0; threadNumber<threadCount; threadNumber++) {

            Boundary frameBound = new Boundary(
                    0,
                    width,
                    threadNumber*height/threadCount,
                    (threadNumber+1)*height/threadCount,
                    n
            );

            MandelbrotThread thread = new MandelbrotThread(frameBound, buffer);
            thread.start();
            threadPool.add(thread);
        }

        //Wait for all threads to complete their work
        for(Thread thread : threadPool) {
            try{
                thread.join();
            } catch(InterruptedException e) {
                System.out.println("Error occured in a thread\n" + e);
                System.exit(0);
            }
        }

        //Write buffer to image on disc
        //Here we write to png
        try {
            File outputFile = new File(path.toString() + "/Mandelbrot-" + n + ".png");
            ImageIO.write(buffer, "png", outputFile);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
