package com.nintendo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class GamePage {

    SelenideElement
            closeButtonForFeedback = $(".QSIWebResponsiveDialog-Layout1-SI_5774MF28S0urRk2_close-btn"),
            digitalCheckbox = $("#Digital-radio-0"),
            physicalCheckbox = $("#Physical-radio-1"),
            directDownloadButton = $(".GridItemstyles-sc-8ag8jk-0"),
            addToCartButton = $(".PurchaseOptionsstyles__WrappingButton-sc-wotdmb-7"),
            itemQuantity = $(".QuantitySelectorstyles__Quantity-sc-mrkana-2"),
            addItemButton = $("[aria-label=\"Add item\"]"),
            errorText = $(".bxkVZE");

    private final static String ERROR_TEXT = "Item limit reached";
    @Step("Click on digital option checkbox")
    public GamePage clickOnDigitalCheckbox() {
        digitalCheckbox.click();
        return this;
    }
    @Step("Close feedback popup")
    public GamePage closeFeedBackPopUp() {
        closeButtonForFeedback.click();
        return this;
    }
    @Step("Click on physical option checkbox")
    public GamePage clickOnPhysicalCheckbox() {
        physicalCheckbox.click();
        return this;
    }

    @Step("Check that checkbox for digital option is ticked")
    public GamePage checkThatDigitalCheckboxIsTicked() {
        digitalCheckbox.shouldBe(Condition.checked);
        return this;
    }

    @Step("Check that checkbox for physical option is ticked")
    public GamePage checkThatPhysicalCheckboxIsTicked() {
        physicalCheckbox.shouldBe(Condition.checked);
        return this;
    }

    @Step("Check that Direct Download button is displayed")
    public GamePage checkThatDirectDownloadButtonIsDisplayed() {
        directDownloadButton.shouldHave(Condition.text("Direct download"));
        return this;
    }

    @Step("Check that Add to cart button is displayed")
    public GamePage checkThatAddToCartButtonIsDisplayed() {
        addToCartButton.shouldHave(Condition.text("Add to cart"));
        return this;
    }

    @Step("Click add item buttom till the limit reached")
    public GamePage clickAddItemTillLimitReached() {
        for (int i = 0; i < 6; i++){
            addItemButton.click();
        }
        return this;
    }

    @Step("Check that Add item button is disabled")
    public GamePage checkAddItemButtonDisabled() {
        addItemButton.shouldBe(Condition.disabled);
        return this;
    }

    @Step("Check that error message for limit reached has appeared")
    public GamePage checkErrorMessageForLimitReachedHasAppeared() {
        errorText.shouldHave(Condition.exactText(ERROR_TEXT));
        return this;
    }




}
