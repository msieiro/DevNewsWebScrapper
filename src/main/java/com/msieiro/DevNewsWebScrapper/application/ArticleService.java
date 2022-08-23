package com.msieiro.DevNewsWebScrapper.application;

import java.util.List;

import com.msieiro.DevNewsWebScrapper.application.dto.ArticleDTO;
import com.msieiro.DevNewsWebScrapper.domain.Article;

public interface ArticleService {

    List<ArticleDTO> getAllArticles();

    void saveAllArticles(final List<Article> articles);

}
