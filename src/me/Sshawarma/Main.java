package me.Sshawarma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Config config = Config.getConfig();
        int width = config.width;
        int height = config.height;
        int threadCount = config.threadCount;

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
