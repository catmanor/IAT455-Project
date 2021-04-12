import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectForm {
    private JPanel selectPanel;
    private JButton starBtn;
    private JButton landscapeBtn;
    private JButton sportBtn;

    public static String label;

    // Set the select form
    public SelectForm() {
        JFrame frame = new JFrame("SelctForm");
        frame.setContentPane(selectPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // For the sport button
        sportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label = "sport/";
                Main mForm = new Main();
                frame.dispose();
            }
        });

        // For the star button
        starBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label = "star/";
                Main mForm = new Main();
                frame.dispose();
            }
        });

        // For the landscape button
        landscapeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label = "landscape/";
                Main mForm = new Main();
                frame.dispose();
            }
        });
    }
}
