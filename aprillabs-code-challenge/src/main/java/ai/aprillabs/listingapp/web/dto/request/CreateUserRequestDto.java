package ai.aprillabs.listingapp.web.dto.request;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.domain.UserType;


public class CreateUserRequestDto {
    private Integer id;
    private String name;
    private String type;
    private Integer age;
    private Integer numOfReferral;

    public CreateUserRequestDto(Integer id, String name, String type, Integer age, Integer numOfReferral) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.numOfReferral = numOfReferral;
    }

    public User toUser() {
        UserType userType;
        if (type.equalsIgnoreCase("partner"))
            userType = UserType.PARTNER;
        else if (type.equalsIgnoreCase("landlord"))
            userType = UserType.LANDLORD;
        else if (type.equalsIgnoreCase("agent"))
            userType = UserType.AGENT;
        else if (type.equalsIgnoreCase("broker"))
            userType = UserType.BROKER;
        else
            userType = UserType.CONTRACTOR;
        return new User(id, name, userType, age, numOfReferral);
    }

}
