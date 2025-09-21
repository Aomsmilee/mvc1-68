package view;

import controller.JobController;
import model.Candidate;
import model.Job;
import model.Company;
import db.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ApplyView extends JFrame {
    public ApplyView(Candidate candidate, Job job, JobController controller){
        setTitle("Apply Job: " + job.getTitle());
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // First Name
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        JTextField txtFirstName = new JTextField(candidate.getFirstName(), 15);
        panel.add(txtFirstName, gbc);
        row++;

        // Last Name
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        JTextField txtLastName = new JTextField(candidate.getLastName(), 15);
        panel.add(txtLastName, gbc);
        row++;

        // Email
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField txtEmail = new JTextField(candidate.getEmail(), 15);
        panel.add(txtEmail, gbc);
        row++;

        // Status
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"studying","graduated"});
        comboStatus.setSelectedItem(candidate.getStatus());
        panel.add(comboStatus, gbc);
        row++;

        // Username
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField txtUsername = new JTextField(candidate.getUsername(), 15);
        panel.add(txtUsername, gbc);
        row++;

        // Password
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField txtPassword = new JPasswordField(candidate.getPassword(), 15);
        panel.add(txtPassword, gbc);
        row++;

        // เลือกบริษัท
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Company:"), gbc);
        gbc.gridx = 1;
        List<Company> companies = CSVReader.loadCompanies("db/companies.csv"); // โหลดบริษัทจาก CSV
        JComboBox<String> comboCompany = new JComboBox<>();
        for(Company c : companies){
            comboCompany.addItem(c.getName() + " (" + c.getCompanyId() + ")");
        }
        panel.add(comboCompany, gbc);
        row++;

        // Submit button
        gbc.gridx = 0; gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnSubmit = new JButton("Submit");
        panel.add(btnSubmit, gbc);

        btnSubmit.addActionListener(e -> {
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String email = txtEmail.getText().trim();
            String status = (String) comboStatus.getSelectedItem();
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            String companySelected = (String) comboCompany.getSelectedItem();

            if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
               username.isEmpty() || password.isEmpty() || companySelected == null){
                JOptionPane.showMessageDialog(this,"Please fill all fields!");
                return;
            }

            Candidate newCandidate = new Candidate(generateId(), firstName, lastName, email, status, username, password);
            controller.submitApplication(newCandidate, job, email); // บันทึก Candidate + Job
            JOptionPane.showMessageDialog(this,"Applied to: " + companySelected);
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    // สร้าง ID 8 หลักไม่ขึ้นต้น 0
    private String generateId(){
        return String.valueOf(10000000 + (int)(Math.random()*90000000));
    }
}
