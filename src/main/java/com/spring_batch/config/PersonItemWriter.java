package com.spring_batch.config;

import com.spring_batch.entities.Person;
import com.spring_batch.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonItemWriter implements ItemWriter<Person> {
    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonItemWriter.class);

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        for (Person person : chunk.getItems()) {
            logger.info("Writing person: {}", person);
            personRepository.saveAndFlush(person); // Ensure you handle merge operations correctly
        }
        logger.info("count of items written: {}", chunk.getItems().size());
    }
}
