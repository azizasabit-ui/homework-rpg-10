package com.narxoz.rpg.quest;

import java.util.List;

public class OrderedQuestIterator implements QuestIterator {
    private final List<Quest> snapshot;
    private int cursor = 0;

    public OrderedQuestIterator(QuestLog log) {
        this.snapshot = log.snapshot();
    }

    @Override
    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more quests");
        }
        return snapshot.get(cursor++);
    }
}