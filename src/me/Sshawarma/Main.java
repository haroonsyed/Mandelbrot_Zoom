package me.Sshawarma;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws IOException {

        //Make dir for animation
        Path path = Paths.get("./output/" + UUID.randomUUID().toString());
        Files.createDirectories(path);



        //Make frames and run them
        MandelbrotGen thread = new MandelbrotGen(0,path);
        MandelbrotGen thread1 = new MandelbrotGen(1,path);
        MandelbrotGen thread2 = new MandelbrotGen(2,path);
        MandelbrotGen thread3 = new MandelbrotGen(3,path);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }





}
