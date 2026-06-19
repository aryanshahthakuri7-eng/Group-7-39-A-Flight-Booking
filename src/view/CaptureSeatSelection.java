package view;

import model.SessionManager;
import model.User;
import model.Flight;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Utility class to capture a high-fidelity visual rendering of SeatSelection.
 */
public class CaptureSeatSelection {
    public static void runCapture(String[] args) {
        System.out.println("Starting CaptureSeatSelection...");
        
        // 1. Initialize session with mock user
        User user = new User();
        user.setUserId(1);
        user.setFullName("User Name");
        user.setEmail("user@yatraair.com");
        SessionManager.setCurrentUser(user);
        
        try {
            // 2. Create mock Flight
            Flight mockFlight = new Flight(
                101, 
                "YS101", 
                "Kathmandu (KTM)", 
                "Pokhara (PKR)", 
                "17 JUN 2026", 
                "10:00 AM", 
                15, 
                8400.0, 
                "ACTIVE"
            );
            
            // 3. Create the seat selection frame
            SeatSelection frame = new SeatSelection(mockFlight, "Aryan Shah");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Set size exactly to the mockup dimensions
            frame.setSize(1366, 768);
            frame.setLocationRelativeTo(null);
            
            // Ensure seat A2 is selected
            frame.updateSelectionSummary();
            frame.setVisible(true);
            
            // Allow Swing time to layout and render
            Thread.sleep(1500);
            
            // 4. Paint the frame to a BufferedImage
            BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g2 = image.createGraphics();
            frame.paint(g2);
            g2.dispose();
            
            // 5. Save to the artifacts directory
            File outputfile = new File("C:/Users/Lenovo LOQ/.gemini/antigravity/brain/e8ab81bc-26bc-4c8c-a12a-fdcc04490336/seat_selection_render.png");
            ImageIO.write(image, "png", outputfile);
            System.out.println("Seat Selection screen capture saved successfully to: " + outputfile.getAbsolutePath());
            
            // Close the frame and exit
            frame.dispose();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
