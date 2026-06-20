package view;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CaptureLogin {
    public static void main(String[] args) {
        System.out.println("Starting CaptureLogin...");
        
        try {
            // Create the login frame
            login frame = new login();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Set size matching the preferred dimensions
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Allow Swing to render the frame components
            Thread.sleep(1500);
            
            // Paint the frame to a BufferedImage
            BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g2 = image.createGraphics();
            frame.paint(g2);
            g2.dispose();
            
            // Save to the artifacts directory
            File outputfile = new File("C:/Users/Lenovo LOQ/.gemini/antigravity/brain/e6fcc305-bd28-4466-9cfc-dc958fa34a80/login_render.png");
            ImageIO.write(image, "png", outputfile);
            
            System.out.println("Login screen capture saved successfully to: " + outputfile.getAbsolutePath());
            
            // Close the frame and exit
            frame.dispose();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
