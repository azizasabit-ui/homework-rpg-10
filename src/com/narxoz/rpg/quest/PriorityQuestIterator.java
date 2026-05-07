package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;

public class PriorityQuestIterator implements QuestIterator {
    private final List<Quest> filteredQuests;
    private int cursor = 0;

    public PriorityQuestIterator(QuestLog log, QuestPriority minPriority) {
        this.filteredQuests = new ArrayList<>();
        List<Quest> snapshot = log.snapshot();
        for (Quest quest : snapshot) {
            if (quest.getPriority().isAtLeast(minPriority)) {
                filteredQuests.add(quest);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return cursor < filteredQuests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more quests");
        }
        return filteredQuests.get(cursor++);
    }
}