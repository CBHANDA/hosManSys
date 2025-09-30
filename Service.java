package model;
public class Service {
    private int id;
    private int patientId;
    private String description;
    private double cost;

    public Service(){}
    public Service(int id, int patientId, String description, double cost){
        this.id=id; this.patientId=patientId; this.description=description; this.cost=cost;
    }
    public Service(int patientId, String description, double cost){ this(0,patientId,description,cost); }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getPatientId(){return patientId;}
    public void setPatientId(int patientId){this.patientId=patientId;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public double getCost(){return cost;}
    public void setCost(double cost){this.cost=cost;}
}
