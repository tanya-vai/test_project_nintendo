package com.nintendo.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement
            closeButtonForFeedback = $(".QSIWebResponsiveDialog-Layout1-SI_5774MF28S0urRk2_close-btn"),
            logoIcon = $(".Topstyles__Logo-sc-16dp618-3"),
            searchFilterDropdown = $(".SearchInputstyles__Select-sc-n42o5d-6"),
            changeRegionButton = $(".IconLinkstyles__Contents-sc-10fd8op-1"),
            searchInput = $("[data-testid=\"form\"]"),
            searchInput1 = $("[name=\"q\"]"),
            firstGameInTrendingTopics = $(".FlyoverResultsstyles__StyledTrendingText-sc-wb2ypo-8.iGfOW"),
            titleAfterSearchingForGame = $(".hJQmxQ"),
            searchInputPlaceholder = $("[data-testid=\"form\"]");
    ElementsCollection
            headerMenu = $$(".Topstyles__Flex-sc-16dp618-1.jouQ"),
            topStoreProducts = $$(".bWauIu");

    @Step("Open the main page")
    public MainPage openPage() {
        open("");
        return this;
    }

    @Step("Close feedback popup")
    public MainPage closeFeedBackPopUp() {
        closeButtonForFeedback.click();
        return this;
    }

    @Step("Check that logo icon is visible")
    public MainPage checkLogoIcon() {
        logoIcon.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check that search filter dropdown is visible")
    public MainPage checkSearchFilterDropdown() {
        searchFilterDropdown.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check that search input is visible")
    public MainPage checkSearchInput() {
        searchInput.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check categories in the navigation menu")
    public MainPage checkCategories(String expectedCategories) {
        headerMenu.shouldHave(CollectionCondition.texts(expectedCategories));
        return this;
    }

    @Step("Click on search input")
    public MainPage clickOnSearchInput() {
        searchInput.click();
        return this;
    }

    @Step("Set game name in the search input")
    public MainPage setGameNameInSearchInput(String searchedGame) {
        searchInput1.setValue(searchedGame).pressEnter();
        return this;
    }

    @Step("Check title after serching for a game")
    public MainPage checkTitleAfterSearchingForGame(String searchedGame) {
        titleAfterSearchingForGame.shouldHave(Condition.text("Results for \"" + searchedGame + "\""));
        return this;
    }

    @Step("Click on first game in trending topics")
    public MainPage clickOnFirstGameInTrendingTopics() {
        firstGameInTrendingTopics.click();
        return this;
    }

    @Step("Check Search input placeholder text")
    public MainPage checkSearchInputPlaceholderText(String searchPlaceholderText) {
        searchInputPlaceholder.shouldHave(Condition.text(searchPlaceholderText));
        return this;
    }

    @Step("Click on change region button")
    public MainPage clickOnChangeRegionButton() {
        changeRegionButton.click();
        return this;
    }

    @Step("Searching for a game")
    public MainPage searchForGame(String searchedGame) {
        clickOnSearchInput();
        searchInput1.setValue(searchedGame);
        topStoreProducts.first().click();
        return this;
    }


}
