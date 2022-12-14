package com.msieiro.DevNewsWebScrapper.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.msieiro.DevNewsWebScrapper.domain.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, UUID> {

    List<Article> findAll(final Sort sort);

}
