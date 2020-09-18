package medical;

public class MemberLogin {

    String firstName;
    String lastName;
    String password;
    int assistantID;



    public MemberLogin(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getAssistantID() {
        return assistantID;
    }

    public void setAssistantID(int assistantID) {
        this.assistantID = assistantID;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
