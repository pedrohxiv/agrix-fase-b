package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {
  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  public void getExistentPersonByIdTest() {
    Person person = new Person();

    person.setId(1L);
    person.setUsername("user");
    person.setPassword("password");
    person.setRole(Role.USER);

    Mockito.when(personRepository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(ArgumentMatchers.eq(1L));

    Assertions.assertEquals(returnedPerson.getId(), person.getId());
    Assertions.assertEquals(returnedPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(returnedPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  public void getNonExistentPersonByIdTest() {
    Mockito.when(personRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(999L));

    Mockito.verify(personRepository).findById(ArgumentMatchers.eq(999L));
  }

  @Test
  public void getExistentPersonByUsernameTest() {
    Person person = new Person();

    person.setId(1L);
    person.setUsername("user");
    person.setPassword("password");
    person.setRole(Role.USER);

    Mockito.when(personRepository.findByUsername(ArgumentMatchers.eq("user"))).thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonByUsername("user");

    Mockito.verify(personRepository).findByUsername(ArgumentMatchers.eq("user"));

    Assertions.assertEquals(returnedPerson.getId(), person.getId());
    Assertions.assertEquals(returnedPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(returnedPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  public void getNonExistentPersonByUsernameTest() {
    Mockito.when(personRepository.findByUsername(ArgumentMatchers.any())).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("admin"));

    Mockito.verify(personRepository).findByUsername(ArgumentMatchers.eq("admin"));
  }

  @Test
  public void createTest() {
    Person person = new Person();

    person.setUsername("user");
    person.setPassword("password");
    person.setRole(Role.USER);

    Person personToReturn = new Person();

    personToReturn.setId(1L);
    personToReturn.setUsername(person.getUsername());
    personToReturn.setPassword(person.getPassword());
    personToReturn.setRole(person.getRole());

    Mockito.when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(personToReturn);

    Person createdPerson = personService.create(person);

    Mockito.verify(personRepository).save(ArgumentMatchers.any(Person.class));

    Assertions.assertEquals(1L, createdPerson.getId());
    Assertions.assertEquals(person.getUsername(), createdPerson.getUsername());
    Assertions.assertEquals(person.getPassword(), createdPerson.getPassword());
    Assertions.assertEquals(person.getRole(), createdPerson.getRole());
  }

}
