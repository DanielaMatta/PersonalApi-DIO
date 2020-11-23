package dto;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

import entity.Person;
import entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String cpf;

	private LocalDate birthDate;

	private List<Phone> phones;

	//public PersonDTO(Person person) {
		//super();
		//this.id = person.getId();
		//this.firstName = person.getFirstName();
		//this.lastName = person.getLastName();
		//this.cpf = person.getCpf();
		//this.birthDate = person.getBirthDate();
		//this.phones = person.getPhones();
	
	
	public static PersonDTO create(Person person) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(person, PersonDTO.class);
	
	
	
	}
	
	
	
	
	
	
}
