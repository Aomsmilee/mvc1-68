package view;

import controller.JobController;
import model.Candidate;
import model.Job;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobListView extends JFrame {
    public JobListView(Candidate candidate,List<Job> jobs,JobController controller){
        setTitle("Job List - Candidate: "+candidate.getName());
        setSize(900,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Job ID","Title","Description","Company ID","Deadline","Status","Job Type"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        JTable table = new JTable(model);

        for(Job j:jobs){
            model.addRow(new Object[]{j.getJobId(),j.getTitle(),j.getDescription(),j.getCompanyId(),
                    j.getDeadline(),j.getStatus(),j.getJobType()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnApply = new JButton("Apply Selected Job");
        add(btnApply, BorderLayout.SOUTH);

        btnApply.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row>=0){
                Job selectedJob = jobs.get(row);
                controller.applyJob(selectedJob);
            } else {
                JOptionPane.showMessageDialog(this,"Select a job first!");
            }
        });

        setVisible(true);
    }
}
