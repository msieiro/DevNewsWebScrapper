package com.msieiro.DevNewsWebScrapper.application.dto;

import java.util.UUID;

import com.msieiro.DevNewsWebScrapper.domain.Person;

import lombok.Data;

@Data
public class PersonDTO {

    private UUID id;
    private String name;
    private String website;
    private String logo;

    public PersonDTO(final Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.website = person.getWebsite();
        this.logo = person.getLogo();
    }
}
