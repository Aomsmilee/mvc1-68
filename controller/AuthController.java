package controller;

import db.CSVReader;
import model.Candidate;
import model.Job;
import view.AdminView;
import view.JobListView;

import java.util.List;
import javax.swing.JOptionPane;

public class AuthController {
    private List<Candidate> candidates;

    public AuthController(List<Candidate> candidates){
        this.candidates=candidates;
    }

    public void showLogin(){
        new view.LoginView(candidates,this);
    }

    public void loginCandidate(Candidate candidate){
        List<Job> jobs = CSVReader.loadJobs("db/jobs.csv");
        new JobListView(candidate,jobs,new JobController(candidate));
    }

    public void loginAdmin(String username){
        List<Candidate> candidates = CSVReader.loadCandidates("db/candidates.csv");
        new AdminView(candidates);
        JOptionPane.showMessageDialog(null,"Admin "+username+" logged in!");
    }
}
