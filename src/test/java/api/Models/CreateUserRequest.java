package api.Models;

public class CreateUserRequest {
    private String name;
    private String email;
    private String gender;
    private String status;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }
}
