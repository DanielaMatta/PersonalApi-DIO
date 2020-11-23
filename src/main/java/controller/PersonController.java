package controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dto.PersonDTO;
import entity.Person;
import exception.PersonNotFoundException;
import service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	
	
	@Autowired
	private PersonService service;
	
	
	
	 // Lista de pessoas
	
	@GetMapping()
	public ResponseEntity get() {
		List<PersonDTO> pessoas = service.getPessoas();
		return ResponseEntity.ok(pessoas);
		
	}
	
	
	//find by id
	
    @GetMapping("/{id}")
	    public ResponseEntity get(@PathVariable("id") Long id) {
    	Optional<PersonDTO> person = service.findById(id);
    
	        return person
	        		.map(pessoas -> ResponseEntity.ok(person))
	        		.orElse(ResponseEntity.notFound().build());
	    }

    
    //update
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Person person) {
    	person.setId(id);
    	PersonDTO pessoa = service.update(person, id);
    	return pessoa != null ?
    			ResponseEntity.ok(pessoa) :
    			ResponseEntity.notFound().build(); 
    }
    
   //delete
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delete(id);
    	
    	
    	return ok ?
    			ResponseEntity.ok().build() :
    			ResponseEntity.notFound().build();	
    	
    }
    
    
    @PostMapping
    public ResponseEntity post(@RequestBody Person person) {
    	
    	try {
    		PersonDTO  pessoas = service.insert(person);
    		URI location = getUri(pessoas.getId());
			return ResponseEntity.created(location).build();
    	} catch (Exception ex) {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    private URI getUri(Long id) {
    	return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(id).toUri();
    }
    	
    }
    

