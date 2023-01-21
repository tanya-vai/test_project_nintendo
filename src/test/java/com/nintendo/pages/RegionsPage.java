package com.nintendo.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class RegionsPage {

    ElementsCollection regionsList = $$("ul li a");

    @Step("Select a region")
    public RegionsPage selectOneRegion(String region) {
       regionsList.findBy(Condition.text(region)).click();
        return this;
    }
}

