package website.dashboard.api.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.dashboard.api.dto.request.UserDTO;
import website.dashboard.api.dto.response.UserDTOResponse;
import website.dashboard.api.model.User;
import website.dashboard.api.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDTOResponse> listAll() {
        return new ResponseEntity(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTOResponse> findById(@PathVariable long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<UserDTOResponse> update(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }
}
