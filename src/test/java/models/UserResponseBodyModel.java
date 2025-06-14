package models;

import lombok.Data;

@Data
public class UserResponseBodyModel {
    String id, name, job, createdAt, updatedAt;
}
