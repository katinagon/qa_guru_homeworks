package model.lombok;

import lombok.Data;

@Data
public class UserResponseBodyModel {
    String id, name, job, createdAt, updatedAt;
}
