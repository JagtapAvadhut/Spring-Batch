package com.spring_batch.config;

import com.spring_batch.entities.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person person) throws Exception {

		 person.setFirstName(person.getFirstName().toUpperCase());
		 person.setLastName(person.getLastName().toUpperCase());
		 person.setUpdatedBy("UPDATED_BY_SYSTEM");
		return person;
	}
}