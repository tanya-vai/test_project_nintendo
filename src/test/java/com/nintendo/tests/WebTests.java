package com.nintendo.tests;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static com.codeborne.selenide.Selenide.*;

public class WebTests extends TestBase{


    String searchedGame = "zelda";
    SelenideElement newsButton = $("a[aria-controls='NEWS_EVENTS']"),
            newsHeader = $(".PageHeaderstyles__Wrapper-sc-kz4e8d-0");
    ElementsCollection headerMenu = $$(".Topstyles__Flex-sc-16dp618-1.jouQ");


    @Test
    @DisplayName("Check that navigation menu has needed categories")
    void checkCategoriesOfNavigationMenu() {
        String expectedCategories = ("Support Wish List Cart Log in / Sign up");
        open("");
        headerMenu.shouldHave(CollectionCondition.texts(expectedCategories));
    }

    @Test
    void checkThatNeededFilterIsDisplayed() {
        open("");
        newsButton.click();
        newsHeader.shouldHave(Condition.text("News"));
    }

    @Test
    void findAC() {
        open("");
        $("[data-testid=\"form\"]").click();
        $("[name=\"q\"]").setValue(searchedGame).pressEnter();
        $(".hJQmxQ").shouldHave(Condition.text("Results for \"" + searchedGame + "\""));
    }


    @Test
    void newTest(){
        open("");
        $("[data-testid=\"form\"]").click();
        $(".FlyoverResultsstyles__StyledTrendingText-sc-wb2ypo-8.iGfOW").click();
        $("#Digital-radio-0").shouldBe(Condition.checked);
        $(".GridItemstyles-sc-8ag8jk-0").shouldHave(Condition.text("Direct download"));
    }

    @CsvSource(value ={
            "Brasil, Procurar jogos, hardware, novidades, etc",
            "USA, Search games, hardware, news, etc"
    })
    @ParameterizedTest
    void chang(String country, String searchPlaceholder){
        open("");
        $(".IconLinkstyles__Contents-sc-10fd8op-1").click();
        $$("ul li a").findBy(Condition.text("Brasil")).click();
        $("[data-testid=\"form\"]").shouldHave(Condition.text("Procurar jogos, hardware, novidades, etc"));
    }

}
