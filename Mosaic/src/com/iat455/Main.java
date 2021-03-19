package com.iat455;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends Frame {

    // The buffered image of source and little sample
    BufferedImage sourceImage;
    BufferedImage littleImageSample;
    BufferedImage clipedImage;

    // The size of the Buffered images
    int sourceWidth;
    int sourceHeight;
    int littleWidth;
    int littleHeight;
    int clipedWidth;
    int clipedHeight;

    // Constructor
    public Main() {
        try {
            sourceImage = ImageIO.read(new File("SourceSample.jpeg"));
            littleImageSample = ImageIO.read(new File ("LittleSample.jpg"));
        } catch (Exception e) {
            System.out.println("Cannot load the provided image");
        }

        // Display the title
        this.setTitle("Image Mosaic");
        this.setVisible(true);

        // Set the  size of Buffered Image
        sourceWidth = sourceImage.getWidth();
        sourceHeight = sourceImage.getHeight();
        littleWidth = littleImageSample.getWidth();
        littleHeight = littleImageSample.getHeight();

        System.out.println("Source Width: " + sourceWidth);
        System.out.println("Source Height: " + sourceHeight);
        System.out.println("Little Width: " + littleWidth);
        System.out.println("Little Width: " + littleWidth);

        // BufferedImage getSubimage (int x, int y, int w, int h);
        // Cut the little image
        // clipedImage = littleImageSample.getSubimage(0, 0, 100, 100);
        clipedWidth = littleWidth;
        clipedHeight = sourceHeight * littleWidth / sourceWidth;
        System.out.println(clipedWidth + ", " + clipedHeight);
        int clipedY = (littleHeight - clipedHeight) / 2;
        clipedImage = littleImageSample.getSubimage(0, clipedY, clipedWidth, clipedHeight);



        //Anonymous inner-class listener to terminate program
        this.addWindowListener(
                new WindowAdapter() {//anonymous class definition
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);//terminate the program
                    }//end windowClosing()
                }//end WindowAdapter
        );//end addWindowListener
    }

    public void paint (Graphics g) {
        this.setSize(1000, 800);

        // g.drawImage(littleImageSample, 50, 50, littleWidth, littleHeight, this);
        // g.drawImage(clipedImage, 50, 500, clipedWidth/10, clipedHeight/10, this);

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                 g.drawImage(clipedImage, 50+clipedWidth*j/5, 50+clipedHeight*i/5, clipedWidth/5, clipedHeight/5, this);
            }
        }
    }

    public static void main(String[] args) {
        Main panel = new Main();
        panel.repaint();
    }
}
