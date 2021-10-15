package website.dashboard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.dashboard.api.dto.request.ContactDTO;
import website.dashboard.api.model.Contact;
import website.dashboard.api.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@AllArgsConstructor
public class ContactController {
    private ContactService contactService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Creates contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The contact was created successfully"),

    })
    public ResponseEntity<Contact> createContact(@RequestBody @Valid ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "List all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),

    })
    public ResponseEntity<List<Contact>> listAll(){
        return new ResponseEntity<>(contactService.listAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    @Operation(summary = "Return a contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When contact with this id not exists"),

    })
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return new ResponseEntity<>(contactService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "When the contact is successfully deleted"),
            @ApiResponse(responseCode = "404", description = "When the contact with this id not exists"),

    })
    public ResponseEntity<Void> delete(@PathVariable long id){
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Updated a contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When the contact is successfully updated"),
            @ApiResponse(responseCode = "404", description = "When contact  not exists"),

    })
    public ResponseEntity<Contact> update(@Valid @RequestBody ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.updateById(contactDTO), HttpStatus.OK);
    }
}
