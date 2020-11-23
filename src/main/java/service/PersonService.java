package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.MessageDTO;
import dto.PersonDTO;
import entity.Person;
import exception.PersonNotFoundException;
import repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	// Lista de pessoas

	public List<PersonDTO> getPessoas() {
		return personRepository.findAll().stream().map(PersonDTO::create).collect(Collectors.toList());

	}

	// Encontrar pessoa pelo Id

	public Optional<PersonDTO> findById(Long id) {
		return personRepository.findById(id).map(PersonDTO::create);

	}

	// deletar pessoa

	public boolean delete(Long id) {

		if (findById(id).isPresent()) {
			personRepository.deleteById(id);
			return true;
		}
		return false;
			
	}
	
	
	//insert
	
	public PersonDTO insert(Person person) {
		Assert.isNull(person.getId());
		return PersonDTO.create(personRepository.save(person));
		
		
	}
	
	
	// update
	
	public PersonDTO update(Person person,Long id) {
		Assert.notNull(id);
		
		Optional<Person> optional = personRepository.findById(id);
		if(optional.isPresent()) {
			Person pd = optional.get();
			pd.setBirthDate(person.getBirthDate());
			pd.setCpf(person.getCpf());
			pd.setFirstName(person.getFirstName());
			pd.setId(person.getId());
			pd.setLastName(person.getLastName());
			pd.setPhones(person.getPhones());
			
			personRepository.save(pd);
			return PersonDTO.create(pd);
		} else {
			return null;
			
		}
		
			
	}
	
	
	//salva pessoas
	
	public Person save(Person person) {
		return personRepository.save(person);
	}

	public MessageDTO createPerson(PersonDTO personDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
