package view;

import controller.AuthController;
import model.Candidate;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginView extends JFrame {

    public LoginView(List<Candidate> candidates, AuthController controller){
        setTitle("Login - Job Fair");
        setSize(400,250);
        setLayout(new GridLayout(5,2,5,5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Role:"));
        JComboBox<String> comboRole = new JComboBox<>(new String[]{"Candidate","Admin"});
        add(comboRole);

        add(new JLabel("Username:"));
        JTextField txtUsername = new JTextField(); add(txtUsername);

        add(new JLabel("Password:"));
        JPasswordField txtPassword = new JPasswordField(); add(txtPassword);

        JButton btnLogin = new JButton("Login"); add(btnLogin);
        JLabel statusLabel = new JLabel(""); add(statusLabel);

        btnLogin.addActionListener(e -> {
            String role = (String) comboRole.getSelectedItem();
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if(role.equals("Candidate")){
                // ไม่ตรวจสอบ username/password
                Candidate tempCandidate = new Candidate(generateId(), "Temp", "User",
                        "temp@email.com", "studying", username, password);
                controller.loginCandidate(tempCandidate);
                dispose();
            } else {
                // Admin ก็ล็อคอินได้เลย
                controller.loginAdmin(username);
                dispose();
            }
        });

        setVisible(true);
    }

    // สร้าง ID 8 หลักไม่ขึ้นต้น 0
    private String generateId(){
        return String.valueOf(10000000 + (int)(Math.random()*90000000));
    }
}
