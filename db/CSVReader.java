package db;

import model.Candidate;
import model.Job;
import model.Company;
import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<Candidate> loadCandidates(String filePath){
        List<Candidate> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine(); // skip header
            while((line=br.readLine())!=null){
                String[] data = line.split(",");
                if(data.length>=7){
                    list.add(new Candidate(data[0],data[1],data[2],data[3],data[4],data[5],data[6]));
                }
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    public static List<Job> loadJobs(String filePath){
        List<Job> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine(); // skip header
            while((line=br.readLine())!=null){
                String[] data = line.split(",");
                if(data.length>=7){
                    list.add(new Job(data[0],data[1],data[2],data[3],data[4],data[5],data[6]));
                }
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    public static List<Company> loadCompanies(String filePath){
        List<Company> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine();
            while((line=br.readLine())!=null){
                String[] data = line.split(",");
                if(data.length>=4){
                    list.add(new Company(data[0],data[1],data[2],data[3]));
                }
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;
    }
}
