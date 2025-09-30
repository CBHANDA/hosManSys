package model;
public class Room {
    private int id;
    private String roomNo;
    private String type;
    private String status;

    public Room(){}
    public Room(int id, String roomNo, String type, String status){
        this.id=id; this.roomNo=roomNo; this.type=type; this.status=status;
    }
    public Room(String roomNo, String type, String status){ this(0,roomNo,type,status); }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getRoomNo(){return roomNo;}
    public void setRoomNo(String roomNo){this.roomNo=roomNo;}
    public String getType(){return type;}
    public void setType(String type){this.type=type;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
