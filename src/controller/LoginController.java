package controller;

import dao.UserDAO;
import model.User;
import model.SessionManager;
import view.Login;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controller to manage the Login view and establish a user session.
 */
public class LoginController {

    private final Login view;
    private final UserDAO userDAO;

    public LoginController(Login view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initListeners();
    }

    private void initListeners() {
        // Submit Button
        view.getBtnLogin().addActionListener(e -> performLogin());

        // Enter Key Submissions
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        };
        view.getTxtEmail().addKeyListener(enterKeyAdapter);
        view.getTxtPassword().addKeyListener(enterKeyAdapter);

        // Header - Back to Home
        view.getLblBackHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                JOptionPane.showMessageDialog(view, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Forgot Password
        view.getLblForgotPassword().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                view.dispose();
                new controller.PasswordRecoveryController(new view.PasswordRecovery()).activate();
            }
        });

        // Sign Up
        view.getLblSignUp().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                view.dispose();
                NavigationController.goToSignUp(view);
            }
        });
    }

    private void performLogin() {
        String email = view.getTxtEmail().getText().trim();
        String password = new String(view.getTxtPassword().getPassword()).trim();
        
        view.getLblError().setText("");
        if (email.equals("Email") || password.equals("Password") || email.isEmpty() || password.isEmpty()) {
            view.getLblError().setText("Please enter both email and password.");
            return;
        }
        
        if (authenticate(email, password)) {
            NavigationController.goToDashboard(view);
        } else {
            view.getLblError().setText("Invalid email address or password.");
        }
    }

    private boolean authenticate(String email, String password) {
        // Check if user exists with matching email and password in database
        User user = userDAO.getByEmailAndPassword(email.trim(), password);
        if (user != null) {
            // Establish global user session for application views
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }
}
