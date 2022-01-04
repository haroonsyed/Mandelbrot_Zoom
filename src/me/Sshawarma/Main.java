package me.Sshawarma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws IOException {

        //Make dir for animation
        Path path = Paths.get("./output/" + UUID.randomUUID().toString());
        Files.createDirectories(path);

        final int width = 1920;
        final int height = 1080;
        int threadCount = 4;

        //Make frames and run them
        for(int threadNumber=0; threadNumber<threadCount; threadNumber++) {

            Boundary frameBound = new Boundary(
                threadNumber*width/threadCount,
                    (threadNumber+1)*(width)/threadCount,
                threadNumber*height/threadCount,
                    (threadNumber+1)*height/threadCount
            );

            frameBound.print();

//            MandelbrotGen thread = new MandelbrotGen(frameBound,path);
//            thread.start();
        }


    }





}
