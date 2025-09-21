import controller.AuthController;
import db.CSVReader;
import model.Candidate;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Candidate> candidates = CSVReader.loadCandidates("db/candidates.csv");
        new AuthController(candidates).showLogin();
    }
}