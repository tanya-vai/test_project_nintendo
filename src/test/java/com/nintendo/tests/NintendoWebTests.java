package com.nintendo.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.nintendo.pages.GamePage;
import com.nintendo.pages.MainPage;
import com.nintendo.pages.RegionsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.sleep;

public class NintendoWebTests extends TestBase {

    MainPage mainPage = new MainPage();
    RegionsPage regionsPage = new RegionsPage();
    GamePage gamePage = new GamePage();

    @DisplayName("Check that all elements of the header are displayed")
    @ValueSource(strings = {"Support", "Wish", "List", "Cart", "Log in / Sign up"})
    @ParameterizedTest
    void checkCategoriesOfNavigationMenuTest(String expectedCategories) {
        mainPage.openPage()
                .checkLogoIcon()
                .checkSearchInput()
                .checkSearchFilterDropdown()
                .checkCategories(expectedCategories);
    }

    @Test
    @DisplayName("Checking that searched game's search results are shown")
    void checkTitleAfterSearchingForGameTest() {
        mainPage.openPage()
                .clickOnSearchInput()
                .setGameNameInSearchInput("zelda")
                .checkTitleAfterSearchingForGame("zelda");
    }

    @Test
    @DisplayName("Check that for corresponding buttons are displayed for both game types")
    void checkButtonsFor2GamesOptionsTest() {
        mainPage.openPage();
        Selenide.refresh();
        mainPage.searchForGame("mario odyssey");
        sleep(5000);
        gamePage.clickOnPhysicalCheckbox()
                .checkThatPhysicalCheckboxIsTicked()
                .checkThatAddToCartButtonIsDisplayed()
                .clickOnDigitalCheckbox()
                .checkThatDigitalCheckboxIsTicked()
                .checkThatDirectDownloadButtonIsDisplayed();

    }

    @Test
    @DisplayName("Check the error message when you reach item limit")
    void checkErrorMessageForLimitGame() {
        mainPage.openPage()
                .searchForGame("mario odyssey");
        sleep(5000);
        gamePage.clickOnPhysicalCheckbox()
                .checkThatPhysicalCheckboxIsTicked()
                .clickAddItemTillLimitReached()
                .checkAddItemButtonDisabled()
                .checkErrorMessageForLimitReachedHasAppeared();

    }

    @CsvSource(value = {
            "Brasil, Procurar jogos, hardware, novidades, etc",
            "USA, Search games, hardware, news, etc"
    })

    @ParameterizedTest(name = "Choosing country {0} and checking the display of text {1} in the search input placeholder")
    @DisplayName("Checking lang in search input placeholder by changing regions")
    void checkLanguageAfterChangingRegionTest(String country, String searchPlaceholder) {
        mainPage.openPage()
                .clickOnChangeRegionButton();
        regionsPage.selectOneRegion(country);
        mainPage.checkSearchInputPlaceholderText(searchPlaceholder);
    }
}