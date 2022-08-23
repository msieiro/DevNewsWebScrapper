package com.msieiro.DevNewsWebScrapper.infrastructure;

import java.time.LocalDate;
import java.time.Month;
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
        options.addArguments(
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        options.addArguments("--start-maximized");
        final WebDriver driver = new ChromeDriver(options);

        loadPersonasInDB();

        loadMidudevArticles(driver);
        loadJamesSinclairArticles(driver);
        loadBaeldungArticles(driver);
        loadNicolasSchurmannArticles(driver);
        loadSmashingMagazineArticles(driver);
        loadWebDevArticles(driver);

        driver.quit();
    }

    /**
     *
     * @param stringDate with format like: <b>August 01, 2020</b>
     * @return the parsed date from String to LocalDate
     */
    private LocalDate parseStringDateToLocalDate3(final String stringDate) {
        final int day = Integer.parseInt(stringDate.split(" ")[1].split(",")[0]);
        final Month month = parseStringMonthToMonthClazz(stringDate.split(" ")[0]);
        final int year = Integer.parseInt(stringDate.split(" ")[2]);
        return LocalDate.of(year, month, day);
    }

    /**
     *
     * @param stringDate with format like: <b>10th April 2022</b>
     * @return the parsed date from String to LocalDate
     */
    private LocalDate parseStringDateToLocalDate2(final String stringDate) {
        final int day = Integer.parseInt(stringDate.split(" ")[0].replaceAll("[a-z]", ""));
        final Month month = parseStringMonthToMonthClazz(stringDate.split(" ")[1]);
        final int year = Integer.parseInt(stringDate.split(" ")[2]);
        return LocalDate.of(year, month, day);
    }

    /**
     *
     * @param stringDate with format like: <b>26 julio 2022</b>
     * @return the parsed date from String to LocalDate
     */
    private LocalDate parseStringDateToLocalDate(final String stringDate) {
        final int day = Integer.parseInt(stringDate.split(" ")[0]);
        final Month month = parseStringMonthToMonthClazz(stringDate.split(" ")[1]);
        final int year = Integer.parseInt(stringDate.split(" ")[2]);
        return LocalDate.of(year, month, day);
    }

    private Month parseStringMonthToMonthClazz(final String month) {
        final String lowerMonth = month.toLowerCase();

        switch (lowerMonth) {
            case "enero":
            case "january":
            case "jan":
                return Month.JANUARY;
            case "febrero":
            case "february":
            case "feb":
                return Month.FEBRUARY;
            case "marzo":
            case "march":
            case "mar":
                return Month.MARCH;
            case "abril":
            case "april":
            case "apr":
            return Month.APRIL;
            case "mayo":
            case "may":
                return Month.MAY;
            case "junio":
            case "june":
            case "jun":
                return Month.JUNE;
            case "julio":
            case "july":
            case "jul":
                return Month.JULY;
            case "agosto":
            case "august":
            case "aug":
                return Month.AUGUST;
            case "septiembre":
            case "september":
            case "sept":
            case "sep":
                return Month.SEPTEMBER;
            case "octubre":
            case "october":
            case "oct":
                return Month.OCTOBER;
            case "noviembre":
            case "november":
            case "nov":
                return Month.NOVEMBER;
            case "diciembre":
            case "december":
            case "dec":
                return Month.DECEMBER;
        }

        return null;
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
                add(Person.builder()
                        .name("Baeldung")
                        .website("https://www.baeldung.com/full_archive")
                        .logo("https://media-exp1.licdn.com/dms/image/C561BAQE-eTcygnAyIA/company-background_10000/0/1555304127962?e=2147483647&v=beta&t=OrfN0EC9zz5hnJhyw9scYew49uVFqcAG1d7zC43tgXc")
                        .build());
                add(Person.builder()
                        .name("Nicolas Schurmann")
                        .website("https://www.nicolas-schurmann.com/")
                        .logo("https://i.ibb.co/D79cDNy/Z69fh-RL9-Oa-Xs-Dz-Xs-CUe2s-GIq-U7-G1-F5-Mwl0-Pwl-Bsx-ll13-K0n-Lb47q7-RMen7-NHvz-MVDgd2-s88-c-k-c0x0.jpg")
                        .build());
                add(Person.builder()
                        .name("Smashing Magazine")
                        .website("https://www.smashingmagazine.com/articles/")
                        .logo("https://inpsyde.com/wp-content/uploads/sites/2/2020/04/smashing-magazine_logo-cover_big@2x.jpg")
                        .build());
                add(Person.builder()
                        .name("web.dev")
                        .website("https://web.dev/blog/")
                        .logo(
                                "https://web-dev.imgix.net/image/FNkVSAX8UDTTQWQkKftSgGe9clO2/uZ3hQS2EPrA9csOgkoXI.png?auto=format&fit=max&w=1200&fm=auto")
                        .build());
                add(Person.builder()
                        .name("spring.io")
                        .website("https://spring.io/blog")
                        .logo("https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/2560px-Spring_Framework_Logo_2018.svg.png")
                        .build());
                add(Person.builder()
                        .name("dev.to")
                        .website("https://dev.to/t/")
                        .logo("https://dev-to-uploads.s3.amazonaws.com/uploads/logos/resized_logo_UQww2soKuUsjaOGNB38o.png")
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
        final List<Article> miduArticles = new ArrayList<>();

        try {
            driver.get(midudev.getWebsite());

            final List<WebElement> miduWebArticles = driver.findElements(By.className("home-article"));

            for (int i = 0; i < miduWebArticles.size(); i++) {
                final WebElement article = miduWebArticles.get(i);
                miduArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("h2"))
                                .getText())
                        .date(parseStringDateToLocalDate(article.findElement(By.tagName("time"))
                                .getText()))
                        .url(article.getAttribute("href"))
                        .owner(midudev)
                        .build());
            }

            articleService.saveAllArticles(miduArticles);
            log.info("ADDED ALL midudev ARTICLES TO DATABASE");
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadMidudevArticles: {}", e.getMessage());
        }
    }

    private void loadJamesSinclairArticles(final WebDriver driver) {
        final Person jSinclair = personaService.getPersonByName("James Sinclair");
        final List<Article> jSinclairArticles = new ArrayList<>();
        try {
            driver.get(jSinclair.getWebsite());

            final List<WebElement> jSinclairWebArticles = driver
                    .findElements(By.className("ArticleList-item"));

            for (int i = 0; i < 6; i++) {
                final WebElement article = jSinclairWebArticles.get(i);
                jSinclairArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("h2")).findElement((By.tagName("a")))
                                .getText())
                        .date(parseStringDateToLocalDate2(article.findElement(By.tagName("time"))
                                .getText()))
                        .url(article.findElement(By.tagName("h2")).findElement((By.tagName("a"))).getAttribute("href"))
                        .owner(jSinclair)
                        .build());
            }

            articleService.saveAllArticles(jSinclairArticles);
            log.info("ADDED ALL James Sinclair ARTICLES TO DATABASE");
        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadJamesSinclairArticles: {}",
                    e.getMessage());
        }
    }

    private void loadBaeldungArticles(final WebDriver driver) {
        final Person baeldung = personaService.getPersonByName("Baeldung");
        final List<Article> baeldungArticles = new ArrayList<>();

        try {
            driver.get(baeldung.getWebsite());

            final List<WebElement> baeldungArchiveList = driver
                    .findElement(By.className("bca-archive__monthlisting")).findElements(By.tagName("li"));

            for (int i = 0; i < 10; i++) {
                final WebElement article = baeldungArchiveList.get(i);

                baeldungArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("a"))
                                .getText())
                        .date(LocalDate.now())
                        .url(article.findElement(By.tagName("a")).getAttribute("href"))
                        .owner(baeldung)
                        .build());
            }

            baeldungArticles.forEach(article -> {
                driver.get(article.getUrl());
                article.setDate(parseStringDateToLocalDate3(driver.findElement(By.className("updated")).getText()));
            });

            articleService.saveAllArticles(baeldungArticles);

            log.info("ADDED ALL Baeldung ARTICLES TO DATABASE");

        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadBaeldungArticles: {}",
                    e.getMessage());
        }
    }

    private void loadNicolasSchurmannArticles(final WebDriver driver) {
        final Person nicolas = personaService.getPersonByName("Nicolas Schurmann");
        final List<Article> nicolasArticles = new ArrayList<>();

        try {
            driver.get(nicolas.getWebsite());

            final List<WebElement> nicolasColumnsList = driver
                    .findElements(By.cssSelector("a[class*='postItem__Article']"));

            for (int i = 0; i < 11; i++) {
                final WebElement article = nicolasColumnsList.get(i);

                nicolasArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("p"))
                                .getText())
                        .date(LocalDate.now())
                        .url(article.getAttribute("href"))
                        .owner(nicolas)
                        .build());
            }

            nicolasArticles.forEach(article -> {
                driver.get(article.getUrl());
                article.setDate(
                        parseStringDateToLocalDate3(
                                driver.findElement(By.className("content")).findElement(By.tagName("div"))
                                        .findElement(By.tagName("div")).findElement(By.tagName("p")).getText()));
            });

            articleService.saveAllArticles(nicolasArticles);

            log.info("ADDED ALL Nicolas Schurmann ARTICLES TO DATABASE");

        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadNicolasSchurmannArticles: {}",
                    e.getMessage());
        }
    }

    private void loadSmashingMagazineArticles(final WebDriver driver) {
        final Person smashingMagazine = personaService.getPersonByName("Smashing Magazine");
        final List<Article> smashingMagazineArticles = new ArrayList<>();

        try {
            driver.get(smashingMagazine.getWebsite());

            final List<WebElement> smashingMagazineArticleList = driver
                    .findElements(By.className("article--post"));

            for (int i = 0; i < 9; i++) {
                final WebElement article = smashingMagazineArticleList.get(i);

                smashingMagazineArticles.add(Article.builder()
                        .title(article.findElement(By.tagName("h2"))
                                .findElement(By.tagName("a"))
                                .getText())
                        .date(parseStringDateToLocalDate3(article.findElement(By.className("article--post__content"))
                                .findElement(By.className("article--post__teaser"))
                                .findElement(By.tagName("time"))
                                .getText()))
                        .url((article.findElement(By.tagName("h2"))
                                .findElement(By.tagName("a"))
                                .getAttribute("href")))
                        .owner(smashingMagazine)
                        .build());
            }

            articleService.saveAllArticles(smashingMagazineArticles);

            log.info("ADDED ALL Smashing Magazine ARTICLES TO DATABASE");

        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadSmashingMagazineArticles: {}",
                    e.getMessage());
        }
    }

    private void loadWebDevArticles(final WebDriver driver) {
        final Person webDev = personaService.getPersonByName("web.dev");
        final List<Article> webDevArticles = new ArrayList<>();

        try {
            driver.get(webDev.getWebsite());

            final List<WebElement> smashingMagazineArticleList = driver
                    .findElements(By.className("card"));

            for (int i = 0; i < 7; i++) {
                final WebElement article = smashingMagazineArticleList.get(i);

                webDevArticles.add(Article.builder()
                        .title(article.findElement(By.className("card__content"))
                                .findElement(By.tagName("h2")).findElement(By.tagName("a"))
                                .getText())
                        .date(parseStringDateToLocalDate3(article.findElement(By.className("gap-top-size-1"))
                                .findElement(By.className("card__avatars"))
                                .findElement(By.className("flow"))
                                .findElement(By.tagName("time"))
                                .getText()))
                        .url((article
                                .findElement(By.tagName("a"))
                                .getAttribute("href")))
                        .owner(webDev)
                        .build());
            }

            articleService.saveAllArticles(webDevArticles);

            log.info("ADDED ALL web.dev ARTICLES TO DATABASE");

        } catch (final Exception e) {
            log.error("Error with SeleniumExecutor.loadWebDevArticles: {}",
                    e.getMessage());
        }
    }
}
