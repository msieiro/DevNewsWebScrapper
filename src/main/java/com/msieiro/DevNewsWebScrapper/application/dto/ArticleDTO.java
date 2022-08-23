package com.msieiro.DevNewsWebScrapper.application.dto;

import java.util.UUID;

import com.msieiro.DevNewsWebScrapper.domain.Article;

import lombok.Data;

@Data
public class ArticleDTO {

    private UUID id;
    private String title;
    private String date;
    private String url;
    private PersonDTO owner;

    public ArticleDTO(final Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.date = article.getDate().toString();
        this.url = article.getUrl();
        this.owner = new PersonDTO(article.getOwner());
    }
}
