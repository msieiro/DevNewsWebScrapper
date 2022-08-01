package com.msieiro.DevNewsWebScrapper.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.msieiro.DevNewsWebScrapper.application.ArticleService;
import com.msieiro.DevNewsWebScrapper.application.PersonaService;
import com.msieiro.DevNewsWebScrapper.domain.Article;
import com.msieiro.DevNewsWebScrapper.domain.Person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
class SeleniumExecutor {

    private final PersonaService personaService;
    private final ArticleService articleService;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(readOnly = false)
    protected void loadDB() {
        loadPersonasInDB();
        loadMidudevArticles();
    }

    private void loadPersonasInDB() {
        final List<Person> personas = new ArrayList<>() {
            {
                add(Person.builder()
                        .name("midudev")
                        .website("https://midu.dev/")
                        .logo("https://midu.dev/logo.png")
                        .build());
                add(Person.builder()
                        .name("James Sinclair")
                        .website("https://jrsinclair.com/")
                        .logo("https://jrsinclair.com/assets/owl.svg")
                        .build());
                /*
                 * add(Person.builder() Hacked Web??
                 * .name("Nicolas Schurmann")
                 * .website("https://www.nicolas-schurmann.com/blog")
                 * .build());
                 */
                add(Person.builder()
                        .name("Baeldung")
                        .website("https://www.baeldung.com/")
                        .logo("https://media-exp1.licdn.com/dms/image/C561BAQE-eTcygnAyIA/company-background_10000/0/1555304127962?e=2147483647&v=beta&t=OrfN0EC9zz5hnJhyw9scYew49uVFqcAG1d7zC43tgXc")
                        .build());
                add(Person.builder()
                        .name("Smashing Magazine")
                        .website("https://www.smashingmagazine.com/articles/")
                        .logo("https://www.smashingmagazine.com/images/logo--full.svg")
                        .build());
                add(Person.builder()
                        .name("dev.to")
                        .website("https://dev.to/t/")
                        .logo("https://dev-to-uploads.s3.amazonaws.com/uploads/logos/resized_logo_UQww2soKuUsjaOGNB38o.png")
                        .build());
                add(Person.builder()
                        .name("web.dev")
                        .website("https://web.dev/blog/")
                        .logo("https://web-dev.imgix.net/image/FNkVSAX8UDTTQWQkKftSgGe9clO2/uZ3hQS2EPrA9csOgkoXI.png?auto=format&fit=max&w=1200&fm=auto")
                        .build());
                add(Person.builder()
                        .name("freeCodeCamp")
                        .website("https://www.freecodecamp.org/news/")
                        .logo("https://cdn.freecodecamp.org/platform/universal/fcc_primary.svg")
                        .build());
            }
        };
        personaService.savePersons(personas);
        log.info("ADDED ALL PERSONS TO DATABASE");
    }

    private void loadMidudevArticles() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        final WebDriver driver = new ChromeDriver(options);
        final Person midudev = personaService.getPersonByName("midudev");
        final List<Article> miduArticles = midudev.getArticles();

        try {
            driver.get(midudev.getWebsite());

            final List<WebElement> miduWebArticles = driver.findElements(By.className("home-article"));

            for (int i = 0; i < miduWebArticles.size(); i++) {
                final WebElement article = miduWebArticles.get(i);
                miduArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("h2"))
                                .getText())
                        .date(article.findElement(By.tagName("time"))
                                .getText())
                        .url(miduWebArticles.get(i).getAttribute("href"))
                        .owner(midudev)
                        .build());
            }

            articleService.saveAllArticles(miduArticles);
            log.info("ADDED ALL ARTICLES TO DATABASE");
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadMidudevArticles: {}", e.getCause().getMessage());
        } finally {
            driver.quit();
            log.info("QUITTING SELENIUM DRIVER");
        }
    }
}
