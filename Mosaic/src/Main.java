import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends Frame {
    private JPanel mainPanel;

    // The buffered image of source and little sample
    BufferedImage sourceImage;
    BufferedImage littleImageSample;
    BufferedImage convertImage;
    BufferedImage r0g0b0, r0g1b0, r0g0b1, r0g2b0, r0g0b2, r0g1b2, r0g2b1, r0g1b1, r0g2b2;
    BufferedImage r1g0b0, r1g1b0, r1g0b1, r1g2b0, r1g0b2, r1g1b2, r1g2b1, r1g1b1, r1g2b2;
    BufferedImage r2g0b0, r2g1b0, r2g0b1, r2g2b0, r2g0b2, r2g1b2, r2g2b1, r2g1b1, r2g2b2;
    String inputImageFromTitle;
    String labelFromSelect;

    // The size of the Buffered images
    int sourceWidth;
    int sourceHeight;
    int littleWidth;
    int littleHeight;
    int clipedWidth;
    int clipedHeight;

    int outputArr[];
    BufferedImage imgArr[] = new BufferedImage[2500];
    int arrCounter = 0;


    public Main() {
        try {
//            sourceImage = ImageIO.read(new File(TitleForm.fc.getSelectedFile().getAbsoluteFile());
            //sourceImage = ImageIO.read(new File("LittleSample.png"));
            littleImageSample = ImageIO.read(new File ("LittleSample.png"));
//            inputImageFromTitle = TitleForm.inputImage;
            inputImageFromTitle = TitleForm.imageName;
            //System.out.println(inputImageFromTitle);
            sourceImage = ImageIO.read(new File(inputImageFromTitle));

            labelFromSelect = SelectForm.label;
            //System.out.println(labelFromSelect);

            // Sample images
            r0g0b0 = ImageIO.read(new File (labelFromSelect + "000.png"));
            r0g1b0 = ImageIO.read(new File (labelFromSelect + "010.png"));
            r0g0b1 = ImageIO.read(new File (labelFromSelect + "001.png"));
            r0g2b0 = ImageIO.read(new File (labelFromSelect + "020.png"));
            r0g0b2 = ImageIO.read(new File (labelFromSelect + "002.png"));
            r0g1b2 = ImageIO.read(new File (labelFromSelect + "012.png"));
            r0g2b1 = ImageIO.read(new File (labelFromSelect + "021.png"));
            r0g1b1 = ImageIO.read(new File (labelFromSelect + "011.png"));
            r0g2b2 = ImageIO.read(new File (labelFromSelect + "022.png"));

            r1g0b0 = ImageIO.read(new File (labelFromSelect + "100.png"));
            r1g1b0 = ImageIO.read(new File (labelFromSelect + "110.png"));
            r1g0b1 = ImageIO.read(new File (labelFromSelect + "101.png"));
            r1g2b0 = ImageIO.read(new File (labelFromSelect + "120.png"));
            r1g0b2 = ImageIO.read(new File (labelFromSelect + "102.png"));
            r1g1b2 = ImageIO.read(new File (labelFromSelect + "112.png"));
            r1g2b1 = ImageIO.read(new File (labelFromSelect + "121.png"));
            r1g1b1 = ImageIO.read(new File (labelFromSelect + "111.png"));
            r1g2b2 = ImageIO.read(new File (labelFromSelect + "122.png"));

            r2g0b0 = ImageIO.read(new File (labelFromSelect + "200.png"));
            r2g1b0 = ImageIO.read(new File (labelFromSelect + "210.png"));
            r2g0b1 = ImageIO.read(new File (labelFromSelect + "201.png"));
            r2g2b0 = ImageIO.read(new File (labelFromSelect + "220.png"));
            r2g0b2 = ImageIO.read(new File (labelFromSelect + "202.png"));
            r2g1b2 = ImageIO.read(new File (labelFromSelect + "212.png"));
            r2g2b1 = ImageIO.read(new File (labelFromSelect + "221.png"));
            r2g1b1 = ImageIO.read(new File (labelFromSelect + "211.png"));
            r2g2b2 = ImageIO.read(new File (labelFromSelect + "222.png"));

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

        sourceImage = sourceImage.getSubimage(0, 0, sourceImage.getWidth()/50*50, sourceImage.getHeight()/50*50);
        //System.out.println(sourceImage.getHeight());

        // -------------------- Calculate the average ----------------------
        int imgave[] = getAverageRGB(r0g1b0);
        //System.out.println("The average array value is: " + imgave[0] + ", " + imgave[1] + ", " + imgave[2]);

        //System.out.println(sourceImage.getHeight());
        outputArr = convertImage(sourceImage, r1g1b1);
        for (int i=0; i<outputArr.length; i++) {
            //System.out.print(outputArr[i]);
            imgArr[i] = switchColorName(outputArr)[i];

        }

        //imgArr = switchColorName(outputArr);

        convertImage = integrateImage(getCliped(sourceImage, r1g1b1));
        //System.out.println(imgArr[3]);




        //Anonymous inner-class listener to terminate program
        this.addWindowListener(
                new WindowAdapter() {//anonymous class definition
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);//terminate the program
                    }//end windowClosing()
                }//end WindowAdapter
        );//end addWindowListener
    }

    // Get the cliped image, src1 is source image
    public BufferedImage getCliped(BufferedImage src1, BufferedImage src2) {
        int clipedWidth = src2.getWidth()/50*50;
        int clipedHeight = (src1.getHeight()/50*50) * (src2.getWidth()/50*50) / (src1.getWidth()/50*50);
        int clipedY = (src2.getHeight() - clipedHeight) / 2;
//        BufferedImage clipedImage = src2.getSubimage(0, 0, clipedWidth, clipedHeight);
        BufferedImage clipedImage = src2.getSubimage(0, 0, 200, 200);

        return clipedImage;
    }

    // Integrate all the little images to a whole image
    public int[] convertImage(BufferedImage src1, BufferedImage src2) {

        // Calculating the average value
        int averRGB[] = new int[3];
        int resultCounter = 0;
        int result[] = new int[2500];
        for(int r=0; r<src1.getHeight(); r+= src1.getHeight()/50) {
            for(int c=0; c<src1.getWidth(); c+=src1.getWidth()/50) {

                // Calculate the average color of the source image
                int size = src1.getHeight() * src1.getWidth() / 2500;
                int red = 0, green = 0, blue = 0;

                for (int y=0+r; y<src1.getHeight()/50+r; y++) {
                    for (int x=0+c; x<src1.getWidth()/50+c; x++) {
                        int rgb = src1.getRGB(x, y);
                        red += ((rgb & 0xff0000) >> 16);
                        green += ((rgb & 0x00ff00) >> 8);
                        blue += ((rgb & 0x0000ff));
                    }
                }
                averRGB[0] = red / size;
                averRGB[1] = green / size;
                averRGB[2] = blue / size;


                // According to the average value to get the array
                if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 0;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 1;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 2;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 3;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 4;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 5;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 6;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 7;
                }
                else if (averRGB[0] >= 0 && averRGB[0]<86 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 8;
                }

                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 9;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 10;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 11;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 12;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 13;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 14;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 15;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 16;
                }
                else if (averRGB[0] >= 86 && averRGB[0]<171 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 17;
                }

                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 18;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 19;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 20;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=0 && averRGB[2]<86) {
                    result[resultCounter] = 21;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=0 && averRGB[1]<86 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 22;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=171 && averRGB[2]<256) {
                    result[resultCounter] = 23;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=171 && averRGB[1]<256 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 24;
                }
                else if (averRGB[0] >= 171 && averRGB[0]<256 && averRGB[1]>=86 && averRGB[1]<171 && averRGB[2]>=86 && averRGB[2]<171) {
                    result[resultCounter] = 25;
                }
                else {
                    result[resultCounter] = 26;
                }
                //System.out.println(averRGB[0]+", "+averRGB[1]+", "+averRGB[2]);
                //System.out.println(resultCounter);
                resultCounter++;
            }
        }

        return result;
    }

    // Integrate to the final image
    public BufferedImage integrateImage(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth()*50, img.getHeight()*50, img.getType());

        for(int r=0; r<img.getHeight()*50; r+= img.getHeight()) {
            for(int c=0; c<img.getWidth()*50; c+=img.getWidth()) {
                for (int y = 0; y < img.getHeight(); y++) {
                    for (int x = 0; x < img.getWidth(); x++) {
                        int clipRGB = imgArr[arrCounter].getRGB(x, y);
                        int red = getRed(clipRGB);
                        int green = getGreen(clipRGB);
                        int blue = getBlue(clipRGB);
                        result.setRGB(c + x, r + y, new Color(red, green, blue).getRGB());
                    }
                }
                arrCounter++;

                //}
            }
        }

        return result;
    }


    // Calculate the average rgb of the image
    public static int[] getAverageRGB(BufferedImage img) {
        int red = 0, green = 0, blue = 0;
        int averRGB[] = new int[3];
        int size = img.getHeight() * img.getWidth();

        for (int r=0; r< img.getHeight(); r++) {
            for (int c=0; c<img.getWidth(); c++) {
                int rgb = img.getRGB(c, r);

                red += ((rgb & 0xff0000) >> 16);
                green += ((rgb & 0x00ff00) >> 8);
                blue += ((rgb & 0x0000ff));
            }
        }

        averRGB[0] = red / size;
        averRGB[1] = green / size;
        averRGB[2] = blue / size;

        return averRGB;
    }


    // Helpers
    public static int getRed (int color) {
        return (color & 0xff0000) >> 16;
    }

    public static int getGreen (int color) {
        return (color & 0x00ff00) >> 8;
    }

    public static int getBlue (int color) {
        return color & 0x0000ff;
    }

    // Get the expression of r, g, b
    public static int getColorAverage (int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }


    // Switch index to color name
    public BufferedImage[] switchColorName(int[] arr) {
        BufferedImage img[] = new BufferedImage[2500];
        for (int i = 0; i < 2500; i++) {
            switch (arr[i]) {
                case 0:
                    img[i] = r0g0b0;
                    break;
                case 1:
                    img[i] = r0g1b0;
                    break;
                case 2:
                    img[i] = r0g0b1;
                    break;
                case 3:
                    img[i] = r0g2b0;
                    break;
                case 4:
                    img[i] = r0g0b2;
                    break;
                case 5:
                    img[i] = r0g1b2;
                    break;
                case 6:
                    img[i] = r0g2b1;
                    break;
                case 7:
                    img[i] = r0g1b1;
                    break;
                case 8:
                    img[i] = r0g2b2;
                    break;
                case 9:
                    img[i] = r1g0b0;
                    break;
                case 10:
                    img[i] = r1g1b0;
                    break;
                case 11:
                    img[i] = r1g0b1;
                    break;
                case 12:
                    img[i] = r1g2b0;
                    break;
                case 13:
                    img[i] = r1g0b2;
                    break;
                case 14:
                    img[i] = r1g1b2;
                    break;
                case 15:
                    img[i] = r1g2b1;
                    break;
                case 16:
                    img[i] = r1g1b1;
                    break;
                case 17:
                    img[i] = r1g2b2;
                    break;
                case 18:
                    img[i] = r2g0b0;
                    break;
                case 19:
                    img[i] = r2g1b0;
                    break;
                case 20:
                    img[i] = r2g0b1;
                    break;
                case 21:
                    img[i] = r2g2b0;
                    break;
                case 22:
                    img[i] = r2g0b2;
                    break;
                case 23:
                    img[i] = r2g1b2;
                    break;
                case 24:
                    img[i] = r2g2b1;
                    break;
                case 25:
                    img[i] = r2g1b1;
                    break;
                default:
                    img[i] = r2g2b2;
            }
        }
        return img;
    }


    public void paint (Graphics g) {
        this.setSize(1400, 1000);

        g.drawImage(convertImage, (1400-sourceWidth)/2, 100, sourceImage.getWidth(), sourceImage.getHeight(), this);
    }

    public static void main(String[] args) {
        Main mf = new Main();
        mf.repaint();
    }
}
