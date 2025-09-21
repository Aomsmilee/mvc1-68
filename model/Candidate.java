package model;

public class Candidate {
    private String id, firstName, lastName, email, status, username, password;

    public Candidate(String id, String firstName, String lastName, String email,
                     String status, String username, String password){
        this.id=id; this.firstName=firstName; this.lastName=lastName;
        this.email=email; this.status=status; this.username=username; this.password=password;
    }

    public String getId(){ return id; }
    public String getName(){ return firstName + " " + lastName; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getEmail(){ return email; }
    public String getStatus(){ return status; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String toCSV(){ return id+","+firstName+","+lastName+","+email+","+status+","+username+","+password; }
}
