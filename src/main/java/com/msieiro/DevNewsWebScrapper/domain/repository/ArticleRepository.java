package com.msieiro.DevNewsWebScrapper.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msieiro.DevNewsWebScrapper.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
