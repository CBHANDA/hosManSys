package model;
public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;

    public Patient(){}
    public Patient(int id, String name, int age, String gender, String address, String phone){
        this.id = id; this.name = name; this.age = age; this.gender = gender; this.address = address; this.phone = phone;
    }
    public Patient(String name, int age, String gender, String address, String phone){
        this(0,name,age,gender,address,phone);
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}
    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}
}
