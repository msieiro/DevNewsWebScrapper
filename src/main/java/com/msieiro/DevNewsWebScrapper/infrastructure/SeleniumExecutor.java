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

import com.msieiro.DevNewsWebScrapper.application.ArticleService;
import com.msieiro.DevNewsWebScrapper.application.PersonaService;
import com.msieiro.DevNewsWebScrapper.domain.Article;
import com.msieiro.DevNewsWebScrapper.domain.Person;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
class SeleniumExecutor {

    private final PersonaService personaService;
    private final ArticleService articleService;

    @EventListener(ApplicationReadyEvent.class)
    protected void loadDB() {
        WebDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
        final WebDriver driver = new ChromeDriver(options);

        loadPersonasInDB();
        loadMidudevArticles(driver);
        loadJamesSinclairArticles(driver);
        loadBaeldungArticles(driver);

        driver.quit();
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
                        .website("https://www.baeldung.com/full_archive")
                        .logo(
                                "https://media-exp1.licdn.com/dms/image/C561BAQE-eTcygnAyIA/company-background_10000/0/1555304127962?e=2147483647&v=beta&t=OrfN0EC9zz5hnJhyw9scYew49uVFqcAG1d7zC43tgXc")
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
                        .logo(
                                "https://web-dev.imgix.net/image/FNkVSAX8UDTTQWQkKftSgGe9clO2/uZ3hQS2EPrA9csOgkoXI.png?auto=format&fit=max&w=1200&fm=auto")
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

    private void loadMidudevArticles(final WebDriver driver) {
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
                        .url(article.getAttribute("href"))
                        .owner(midudev)
                        .build());
            }

            articleService.saveAllArticles(miduArticles);
            log.info("ADDED ALL ARTICLES TO DATABASE");
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadMidudevArticles: {}", e.getMessage());
        }
    }

    private void loadJamesSinclairArticles(final WebDriver driver) {
        final Person jSinclair = personaService.getPersonByName("James Sinclair");
        final List<Article> jSinclairArticles = jSinclair.getArticles();
        try {
            driver.get(jSinclair.getWebsite());

            final List<WebElement> jSinclairWebArticles = driver
                    .findElements(By.className("ArticleList-item"));

            for (int i = 0; i < 6; i++) {
                final WebElement article = jSinclairWebArticles.get(i);
                jSinclairArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("h2")).findElement((By.tagName("a")))
                                .getText())
                        .date(article.findElement(By.tagName("time"))
                                .getText())
                        .url(article.findElement(By.tagName("h2")).findElement((By.tagName("a"))).getAttribute("href"))
                        .owner(jSinclair)
                        .build());
            }

            articleService.saveAllArticles(jSinclairArticles);
            log.info("ADDED ALL ARTICLES TO DATABASE");
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadJamesSinclairArticles: {}",
                    e.getMessage());
        }
    }

    private void loadBaeldungArticles(final WebDriver driver) {
        final Person baeldung = personaService.getPersonByName("Baeldung");
        final List<Article> baeldungArticles = baeldung.getArticles();

        try {
            driver.get(baeldung.getWebsite());

            driver.findElement(By.xpath("//*[@id='qc-cmp2-ui']/div[2]/div/button[2]")).click();

            final List<WebElement> baeldungArchiveList = driver
                    .findElement(By.className("bca-archive__list")).findElements(By.tagName("li"));

            // final List<WebElement> baeldungArchiveList2 =
            // baeldungArchiveList.findElements(By.cssSelector(".bca-archive__monthlisting"));

            log.info("founded monthlisting, with size : {}", baeldungArchiveList.size());

            /*
             * final List<WebElement> baeldungArchiveListItems = baeldungArchiveList.get(0)
             * .findElements(By.tagName("li"));
             *
             * log.info("founded lis");
             *
             * for (int i = 0; i < baeldungArchiveListItems.size(); i++) {
             * final WebElement article = baeldungArchiveListItems.get(i);
             * baeldungArticles.add(Article.builder()
             * .title(article.findElement(By.tagName("a"))
             * .getText())
             * .date("unknown")
             * .url(article.findElement(By.tagName("a")).getAttribute("href"))
             * .owner(baeldung)
             * .build());
             * }
             *
             * articleService.saveAllArticles(baeldungArticles);
             * log.info("ADDED ALL ARTICLES TO DATABASE");
             */
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadBaeldungArticles: {}",
                    e.getMessage());
        }
    }
}
