package website.dashboard.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Contact> createContact(@RequestBody @Valid ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Contact>> listAll(){
        return new ResponseEntity<>(contactService.listAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return new ResponseEntity<>(contactService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Contact> updateById(@PathVariable long id, @RequestBody ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.updateById(id,contactDTO), HttpStatus.OK);
    }
}
