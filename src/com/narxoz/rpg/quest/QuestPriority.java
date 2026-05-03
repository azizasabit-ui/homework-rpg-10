package com.narxoz.rpg.quest;

public enum QuestPriority {
    LOW(1),
    NORMAL(2),
    HIGH(3),
    URGENT(4);

    private final int value;

    QuestPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAtLeast(QuestPriority other) {
        return this.value >= other.value;
    }
}