package view;

import model.Candidate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminView extends JFrame {
    private Set<Integer> lockedRows = new HashSet<>();

    public AdminView(List<Candidate> candidates){
        setTitle("Admin - Candidate List / Add Grade");
        setSize(1000,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel แยก Normal / Internship
        JTabbedPane tabbedPane = new JTabbedPane();

        // Normal Candidates (ไม่ต้องใส่เกรด)
        JPanel normalPanel = new JPanel(new BorderLayout());
        String[] normalColumns = {"ID","First Name","Last Name","Email","Status","Username"};
        DefaultTableModel normalModel = new DefaultTableModel(normalColumns,0);
        JTable normalTable = new JTable(normalModel);

        // Internship Candidates (สามารถใส่เกรดได้)
        JPanel internshipPanel = new JPanel(new BorderLayout());
        String[] internColumns = {"ID","First Name","Last Name","Email","Status","Username","Grade"};
        DefaultTableModel internModel = new DefaultTableModel(internColumns,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // เฉพาะคอลัมน์ Grade และยังไม่ล็อค
                return column == 6 && !lockedRows.contains(row);
            }
        };
        JTable internTable = new JTable(internModel);

        // แยก Candidate
        for(int i=0; i<candidates.size(); i++){
            Candidate c = candidates.get(i);
            String status = c.getStatus().toLowerCase();
            if(status.equals("studying") || status.equals("internship") || status.equals("sangkit")){
                // Internship tab
                internModel.addRow(new Object[]{
                        c.getId(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getEmail(),
                        c.getStatus(),
                        c.getUsername(),
                        "" // grade ว่าง
                });
            } else {
                // Normal tab
                normalModel.addRow(new Object[]{
                        c.getId(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getEmail(),
                        c.getStatus(),
                        c.getUsername()
                });
            }
        }

        normalPanel.add(new JScrollPane(normalTable), BorderLayout.CENTER);
        internshipPanel.add(new JScrollPane(internTable), BorderLayout.CENTER);

        // ปุ่มบันทึกเกรดสำหรับ Internship
        JPanel btnPanel = new JPanel();
        JButton btnSave = new JButton("Save Grade");
        btnPanel.add(btnSave);
        internshipPanel.add(btnPanel, BorderLayout.SOUTH);

        // Action Save Grade
        btnSave.addActionListener(e -> {
            int selectedRow = internTable.getSelectedRow();
            if(selectedRow >= 0){
                String[] grades = {"A","B+","B","C+","C","D+","D","F"};
                String selectedGrade = (String) JOptionPane.showInputDialog(
                        this,
                        "Select Grade for " + internModel.getValueAt(selectedRow,1) + " " + internModel.getValueAt(selectedRow,2),
                        "Assign Grade",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        grades,
                        grades[0]
                );

                if(selectedGrade != null){
                    internModel.setValueAt(selectedGrade, selectedRow, 6);
                    lockedRows.add(selectedRow); // ล็อคไม่ให้แก้ไขอีก
                    JOptionPane.showMessageDialog(this,"Grade saved: " + selectedGrade);
                    // TODO: บันทึกลง CSV หรือฐานข้อมูล
                }
            } else {
                JOptionPane.showMessageDialog(this,"Select a candidate first!");
            }
        });

        tabbedPane.add("Normal Candidates", normalPanel);
        tabbedPane.add("Internship Candidates", internshipPanel);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
