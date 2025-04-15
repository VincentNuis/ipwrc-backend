package spring.boot.mappers;

import org.springframework.stereotype.Component;
import spring.boot.data.UserEntity;
import spring.boot.model.UserModel;

@Component
public class UserEntityMapper implements EntityMapper<UserEntity, UserModel> {

    @Override
    public UserModel map(UserEntity entity) {
        return new UserModel(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getId()
        );
    }

    @Override
    public UserEntity reverseMap(UserModel userModel) {
        return new UserEntity(
                userModel.getFirstName(),
                userModel.getLastName()
        );
    }
}
