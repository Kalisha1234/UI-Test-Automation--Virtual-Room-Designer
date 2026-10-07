package com.roomdesigner.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RoomDesignerPage {

    private final SelenideElement roomNameInput = $("input[name='name']");
    private final SelenideElement widthInput = $("input[name='width']");
    private final SelenideElement lengthInput = $("input[name='length']");
    private final SelenideElement primaryButton = $("button.primary");
    private final ElementsCollection roomChips = $$("button.room-chip");

    @Step("Create room {name} with width {width} and length {length}")
    public RoomDesignerPage createRoom(String name, String width, String length) {
        roomNameInput.shouldBe(visible).clear();
        roomNameInput.setValue(name);
        widthInput.clear();
        widthInput.setValue(width);
        lengthInput.clear();
        lengthInput.setValue(length);
        primaryButton.click();
        return this;
    }

    @Step("Add a sofa to the room")
    public RoomDesignerPage addSofa() {
        $$(".catalog-item").findBy(text("Sofa")).click();
        return this;
    }

    @Step("Select the last furniture item")
    public RoomDesignerPage selectLastFurniture() {
        $$(".room-furniture").last().click();
        return this;
    }

    @Step("Rotate the selected furniture")
    public RoomDesignerPage rotateFurniture() {
        $$("button").findBy(text("Rotate +15°")).click();
        return this;
    }

    @Step("Remove the selected furniture")
    public RoomDesignerPage removeFurniture() {
        $$("button").findBy(text("Remove furniture")).click();
        return this;
    }

    @Step("Delete the selected room")
    public RoomDesignerPage deleteRoom() {
        $$("button").findBy(text("Delete selected room")).click();
        return this;
    }

    @Step("Remove room {name} if it remains after the test")
    public RoomDesignerPage deleteRoomIfPresent(String name) {
        ElementsCollection matchingRooms = roomChips.filterBy(text(name));
        if (matchingRooms.size() > 0) {
            matchingRooms.first().click();
            deleteRoom();
            matchingRooms.shouldHave(size(0));
        }
        return this;
    }
}
