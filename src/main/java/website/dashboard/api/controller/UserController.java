package website.dashboard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Creates user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user was created successfully"),

    })
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),

    })
    public ResponseEntity<UserDTOResponse> listAll() {
        return new ResponseEntity(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Return a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When user with this id not exists"),

    })
    public ResponseEntity<UserDTOResponse> findById(@PathVariable long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "When the user is successfully deleted"),
            @ApiResponse(responseCode = "404", description = "When the user with this id not exists"),

    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    @Operation(summary = "Updated a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When the user is successfully updated"),
            @ApiResponse(responseCode = "404", description = "When user  not exists"),

    })
    public ResponseEntity<UserDTOResponse> update(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }
}
