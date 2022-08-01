package com.msieiro.DevNewsWebScrapper.application;

import java.util.List;

import com.msieiro.DevNewsWebScrapper.domain.Person;

public interface PersonaService {

    Person getPersonByName(final String name);

    Person savePerson(final Person person);

    List<Person> savePersons(final List<Person> persons);
}
