package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
