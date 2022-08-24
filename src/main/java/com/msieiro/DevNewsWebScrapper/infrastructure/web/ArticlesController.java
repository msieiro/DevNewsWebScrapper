package com.msieiro.DevNewsWebScrapper.infrastructure.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msieiro.DevNewsWebScrapper.application.ArticleService;
import com.msieiro.DevNewsWebScrapper.application.dto.ArticleDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@CrossOrigin(value = "https://dev-news-web-scrapper.herokuapp.com/")
class ArticlesController {

    private final ArticleService articleService;

    @GetMapping
    ResponseEntity<List<ArticleDTO>> getArticles() {
        return new ResponseEntity<List<ArticleDTO>>(articleService.getAllArticles(), HttpStatus.OK);
    }
}
