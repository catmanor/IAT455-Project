import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TitleForm extends Frame {
    public static String imageName;
    private JPanel titlePanel;
    private JButton chooseBtn;
    private JButton inputBtn;
    public JFileChooser fc;
    public BufferedImage inputImage;
    BufferedImage bgImage;

    public TitleForm() {

        try {
            bgImage = ImageIO.read(new File("bg.png"));
        } catch (Exception e) {
            System.out.println("Cannot load the provided image");
        }

        // Set the title frame component
        JFrame frame = new JFrame("TitleForm");
        frame.setContentPane(titlePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // For the choose button
        chooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectForm sForm = new SelectForm();
                frame.dispose();
            }
        });

        // For the input button
        inputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton select = new JButton();
                fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("Desktop"));
                fc.setDialogTitle("Please choose an input image");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(select) == JFileChooser.APPROVE_OPTION) {}
                String fileName = fc.getSelectedFile().getAbsoluteFile().toString();
                imageName = fileName;
//                System.out.println(imageName);
//                System.out.println(fc.getSelectedFile().getAbsoluteFile());
            }
        });

    }

    public String getImageName() {
        return imageName;
    }

    public void paint (Graphics g) {
        this.setSize(1000, 800);

        g.drawImage(bgImage, 0, 0, 500, 500, this);
    }

    public static void main(String[] args) {
        new TitleForm();
    }
}
