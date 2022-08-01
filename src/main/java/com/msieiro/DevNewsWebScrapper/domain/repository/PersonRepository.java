package com.msieiro.DevNewsWebScrapper.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msieiro.DevNewsWebScrapper.domain.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Person findByName(final String name);
}
