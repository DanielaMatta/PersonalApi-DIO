import static org.hamcrest.CoreMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dto.MessageDTO;
import dto.PersonDTO;
import entity.Person;
import repository.PersonRepository;
import service.PersonService;

@ExtendWith(MockitoExtension.class)
	public class PersonServiceTest {

	    @Mock
	    private PersonRepository personRepository;

	    @InjectMocks
	    private PersonService personService;

	    @Test
	    <S> void testGivenPersonDTOThenReturnSavedMessage() {
	        PersonDTO personDTO = createFakeDTO();
	        Person expectedSavedPerson = createFakeEntity();

	        when(personRepository.saveAll((S) any(Person.class))).thenReturn(expectedSavedPerson);

	        MessageDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
	        MessageDTO succesMessage = personService.createPerson(personDTO);

	        assertEquals(expectedSuccessMessage, succesMessage);
	    }

	    private Person createFakeEntity() {
			// TODO Auto-generated method stub
			return null;
		}

		private MessageDTO createExpectedMessageResponse(Long id) {
	        return MessageDTO
	                .builder()
	                .message("Created person with ID " + id)
	                .build();
	    }
	}
	
	
	
	
	
	
	
	
	
	

