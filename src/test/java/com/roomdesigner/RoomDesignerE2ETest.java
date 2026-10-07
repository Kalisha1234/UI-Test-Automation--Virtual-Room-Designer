package com.roomdesigner;

import com.roomdesigner.config.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

@Feature("Room design")
class RoomDesignerE2ETest extends BaseTest {

    @Test
    @Story("Create a room and manage furniture")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creates a room, adds and rotates a sofa, removes the sofa, and deletes the room.")
    void shouldCreateRoomAddAndRemoveFurniture() {
        page.createRoom(testData.roomName(), testData.roomWidth(), testData.roomLength());
        $$("button.room-chip").findBy(text(testData.roomName())).shouldBe(visible);

        page.addSofa();
        $$(".room-furniture").shouldHave(sizeGreaterThanOrEqual(1));

        page.selectLastFurniture();
        page.rotateFurniture();
        page.removeFurniture();
        $$(".room-furniture").shouldHave(size(0));

        page.deleteRoom();
        $$("button.room-chip").filterBy(text(testData.roomName())).shouldHave(size(0));
    }
}
