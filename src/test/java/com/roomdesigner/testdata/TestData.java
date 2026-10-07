package com.roomdesigner.testdata;

import java.util.UUID;

public record TestData(String roomName, String roomWidth, String roomLength) {

    public static TestData newRoom() {
        String uniqueName = "AutoRoom-" + UUID.randomUUID().toString().substring(0, 8);
        return new TestData(uniqueName, "6", "5");
    }
}
