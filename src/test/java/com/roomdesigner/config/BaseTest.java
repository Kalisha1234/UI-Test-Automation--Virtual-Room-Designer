package com.roomdesigner.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.roomdesigner.pages.RoomDesignerPage;
import com.roomdesigner.testdata.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    protected RoomDesignerPage page;
    protected TestData testData;

    @BeforeEach
    protected void setUp() {
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "false"));
        Configuration.baseUrl = System.getProperty("selenide.baseUrl", "http://localhost:5173");
        Configuration.timeout = Long.parseLong(System.getProperty("selenide.timeout", "15000"));
        Configuration.browserSize = System.getProperty("selenide.browserSize", "1440x1000");
        Configuration.pageLoadTimeout = 30000;

        testData = TestData.newRoom();
        page = new RoomDesignerPage();
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true)
        );
        open(Configuration.baseUrl);
    }

    @AfterEach
    protected void tearDown() {
        try {
            if (testData != null && page != null && WebDriverRunner.hasWebDriverStarted()) {
                page.deleteRoomIfPresent(testData.roomName());
            }
        } finally {
            try {
                if (WebDriverRunner.hasWebDriverStarted()) {
                    Selenide.closeWebDriver();
                }
            } finally {
                SelenideLogger.removeListener("AllureSelenide");
            }
        }
    }
}
