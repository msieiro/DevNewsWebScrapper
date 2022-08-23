package com.msieiro.DevNewsWebScrapper.application.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msieiro.DevNewsWebScrapper.application.ArticleService;
import com.msieiro.DevNewsWebScrapper.application.dto.ArticleDTO;
import com.msieiro.DevNewsWebScrapper.domain.Article;
import com.msieiro.DevNewsWebScrapper.domain.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = false)
    public void saveAllArticles(final List<Article> articles) {
        articleRepository.saveAll(articles);
    }

    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll(Sort.by("date").descending()).stream().map(ArticleDTO::new).toList();
    }

}
