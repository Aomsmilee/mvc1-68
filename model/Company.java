package model;

public class Company {
    private String companyId,name,email,location;

    public Company(String companyId,String name,String email,String location){
        this.companyId=companyId; this.name=name; this.email=email; this.location=location;
    }

    public String getCompanyId(){ return companyId; }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getLocation(){ return location; }
}
