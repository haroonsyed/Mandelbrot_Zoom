package me.Sshawarma;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

        for(int i=0; i<10; i++) {
            try {
                Date d = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH_mm_ss");
                String dateText = df.format(d);
                File outputFile = new File(path.toString() + "/Mandelbrot_" + i + ".png");
                ImageIO.write(getMandelbrotFrame(i), "png", outputFile);
            } catch(Exception e) {
                System.out.println(e);
            }
        }

    }

    public static Color getPixelColor(Color targetColor,int n, int iterLimit) {
        return new Color(
                n*targetColor.getRed()/iterLimit,
                n* targetColor.getGreen()/iterLimit,
                n* targetColor.getBlue()/iterLimit
        );
    }

    public static BufferedImage getMandelbrotFrame(int frame) {

        //CONFIG
        final int width = 1920;
        final int height = 1080;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        double range = 0.0001;
        //END CONFIG

        final double centerX =  0.360240443437614363236125244449545308482607807958585750488375814740195346059218100311752936722773426396233731729724987737320035372683285317664532401218521579554288661726564324134702299962817029213329980895208036363104546639698106204384566555001322985619004717862781192694046362748742863016467354574422779443226982622356594130430232458472420816652623492974891730419252651127672782407292315574480207005828774566475024380960675386215814315654794021855269375824443853463117354448779647099224311848192893972572398662626725254769950976527431277402440752868498588785436705371093442460696090720654908973712759963732914849861213100695402602927267843779747314419332179148608587129105289166676461292845685734536033692577618496925170576714796693411776794742904333484665301628662532967079174729170714156810530598764525260869731233845987202037712637770582084286587072766838497865108477149114659838883818795374195150936369987302574377608649625020864292915913378927790344097552591919409137354459097560040374880346637533711271919419723135538377394364882968994646845930838049998854075817859391340445151448381853615103761584177161812057928;
        final double centerY = -0.6413130610648031748603750151793020665794949522823052595561775430644485741727536902556370230689681162370740565537072149790106973211105273740851993394803287437606238596262287731075999483940467161288840614581091294325709988992269165007394305732683208318834672366947550710920088501655704252385244481168836426277052232593412981472237968353661477793530336607247738951625817755401065045362273039788332245567345061665756708689359294516668271440525273653083717877701237756144214394870245598590883973716531691124286669552803640414068523325276808909040317617092683826521501539932397262012011082098721944643118695001226048977430038509470101715555439047884752058334804891389685530946112621573416582482926221804767466258346014417934356149837352092608891639072745930639364693513216719114523328990690069588676087923656657656023794484324797546024248328156586471662631008741349069961493817600100133439721557969263221185095951241491408756751582471307537382827924073746760884081704887902040036056611401378785952452105099242499241003208013460878442953408648178692353788153787229940221611731034405203519945313911627314900851851072122990492499999999999999999991;

        //DO NOT CHANGE, scales x to stop stretching
        double rangeX = range*height/width;

        Color targets[] = {
                new Color(0x484349),
                new Color(0x109648),
                new Color(0x18a999),
                new Color(0x8af3ff),
                new Color(0xf7f0f0),
        };


        for (int xCoord=0; xCoord<width; xCoord++) {
            for(int yCoord=0; yCoord<height; yCoord++){

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
                int iterLimit = 150;
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

        return img;
    }


}
