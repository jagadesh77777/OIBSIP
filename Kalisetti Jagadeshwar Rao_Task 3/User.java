public class User {
    private String userId;
    private String name;
    private String pin;

    public User(String userId, String name, String pin){
        this.userId = userId;
        this.name = name;
        this.pin = pin;
    }
    public String getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getPin(){
        return pin;
    }
}
