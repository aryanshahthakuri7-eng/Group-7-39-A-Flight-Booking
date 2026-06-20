package view;

import model.SessionManager;
import model.User;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CaptureDashboard {
    public static void main(String[] args) {
        System.out.println("Starting CaptureDashboard...");
        
        // 1. Initialize session with mock user "User Name"
        User user = new User();
        user.setUserId(1);
        user.setFullName("User Name");
        user.setEmail("user@yatraair.com");
        user.setPhone("+977 9812345678");
        SessionManager.setCurrentUser(user);
        
        try {
            // 2. Create the dashboard frame
            dashboard frame = new dashboard();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Set size exactly to the required dimensions
            frame.setSize(820, 590);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Allow Swing to render the frame components
            Thread.sleep(1500);
            
            // 3. Paint the frame to a BufferedImage
            BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g2 = image.createGraphics();
            frame.paint(g2);
            g2.dispose();
            
            // 4. Save to the artifacts directory
            File outputfile = new File("C:/Users/Lenovo LOQ/.gemini/antigravity/brain/e8ab81bc-26bc-4c8c-a12a-fdcc04490336/dashboard_render.png");
            ImageIO.write(image, "png", outputfile);
            
            System.out.println("Dashboard screen capture saved successfully to: " + outputfile.getAbsolutePath());
            
            // Close the frame and exit
            frame.dispose();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
