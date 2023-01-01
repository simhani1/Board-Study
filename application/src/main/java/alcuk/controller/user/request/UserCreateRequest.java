package alcuk.controller.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateRequest{
    private String name;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_password")
    private String userPassword;

    public UserCreateRequest(){}

    public UserCreateRequest(String name, String userId, String userPassword){
        this.name = name;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
