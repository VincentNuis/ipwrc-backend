package spring.boot.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.boot.data.UserEntity;
import spring.boot.data.UserRepository;
import spring.boot.mappers.EntityMapper;
import spring.boot.model.UserModel;

@Service
@Qualifier("primary")
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final EntityMapper<UserEntity, UserModel> entityMapper;

    public UserServiceImpl(UserRepository userRepository,
                           EntityMapper<UserEntity, UserModel> entityMapper){
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    public UserModel getUser(String userName){
        return entityMapper.map(userRepository.findByFirstName(userName));
    }

    public void addUser(UserModel user){
        userRepository.save(entityMapper.reverseMap(user));
    }

    @Transactional
    public void deleteUser(String userName){
        userRepository.deleteByFirstName(userName);
    }
}
