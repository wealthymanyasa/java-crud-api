package com.lyvepulse.restaurant.Services;


//@Entity
//@Repository
//@Service
//@RestController


import com.lyvepulse.restaurant.dtos.UserDto;
import com.lyvepulse.restaurant.entity.User;
import com.lyvepulse.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {


        User user = new User();
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setRole(user.getRole());

        return userRepository.save(user);
    }

}
