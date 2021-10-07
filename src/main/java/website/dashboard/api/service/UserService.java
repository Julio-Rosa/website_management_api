package website.dashboard.api.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import website.dashboard.api.dto.request.UserDTO;
import website.dashboard.api.dto.response.UserDTOResponse;
import website.dashboard.api.mapper.UserMapper;
import website.dashboard.api.model.User;
import website.dashboard.api.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    public UserDTOResponse createUser(UserDTO userDTO){
        User userToSave = userMapper.toModel(userDTO);
        User savedUser = userRepository.save(userToSave);
        return userMapper.toDTOResponse(savedUser);
    }
    public List<UserDTOResponse> listAll(){
        List<User> all = userRepository.findAll();
        List<UserDTOResponse> userDTOResponses = new ArrayList<>();
        for (User user: all){
            userDTOResponses.add(userMapper.toDTOResponse(user));
        }
        return userDTOResponses;

    }
    public UserDTOResponse findById(long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        return userMapper.toDTOResponse(user);
    }
    public void delete(long id){
        findById(id);
        userRepository.deleteById(id);
    }
    public UserDTOResponse update(UserDTO userDTO){
        if (!verifyIfExits(userDTO.getId())){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

        }
        User userToSave = userMapper.toModel(userDTO);
        User savedUser = userRepository.save(userToSave);
        return userMapper.toDTOResponse(savedUser);


    }
    public boolean verifyIfExits(long id){
        return userRepository.existsById(id);

    }
}
