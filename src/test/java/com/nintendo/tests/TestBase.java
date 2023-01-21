package com.nintendo.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.nintendo.attachments.Attach;
import com.nintendo.config.Browser;
import com.nintendo.config.ConfigReader;
import com.nintendo.config.ProjectConfiguration;
import com.nintendo.config.WebConfig;
import com.nintendo.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;

public class TestBase {

    private static final WebConfig webConfig = ConfigReader.Instance.read();
    private static ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
    MainPage mainPage = new MainPage();


    @BeforeAll
    public static void setUp(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        projectConfiguration.webConfig();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (Configuration.browser.equals(Browser.CHROME.name())) {
            Attach.browserConsoleLogs();
        }

        if (projectConfiguration.isRemote()) {
            Attach.addVideo(projectConfiguration.getVideoStorageUrl());
        }
    }
}
