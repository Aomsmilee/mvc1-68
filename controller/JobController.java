package controller;

import db.CSVWriter;
import model.Candidate;
import model.Job;
import view.ApplyView;

import javax.swing.*;
import java.util.List;

public class JobController {
    private Candidate candidate;

    public JobController(Candidate candidate){
        this.candidate=candidate;
    }

    public void applyJob(Job job){
        if(!job.getStatus().equalsIgnoreCase("open")){
            JOptionPane.showMessageDialog(null,"Cannot apply, job is closed!");
            return;
        }
        new ApplyView(candidate,job,this);
    }

    public void submitApplication(Candidate candidate, Job job, String email){
        if(candidate.getStatus().equalsIgnoreCase("studying") && job.getJobType().equalsIgnoreCase("normal")){
            JOptionPane.showMessageDialog(null,"Cannot apply normal job while studying!");
            return;
        }
        if(candidate.getStatus().equalsIgnoreCase("graduated") && job.getJobType().equalsIgnoreCase("internship")){
            JOptionPane.showMessageDialog(null,"Cannot apply internship after graduation!");
            return;
        }

        CSVWriter.appendLine("db/candidates.csv", candidate.toCSV());
        JOptionPane.showMessageDialog(null,"Application submitted successfully!");
    }
}
