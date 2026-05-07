package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RewardSortedQuestIterator implements QuestIterator {
    private final List<Quest> sortedQuests;
    private int cursor = 0;

    public RewardSortedQuestIterator(QuestLog log) {
        this.sortedQuests = new ArrayList<>(log.snapshot());
        this.sortedQuests.sort(Comparator.comparingInt(Quest::getReward).reversed());
    }

    @Override
    public boolean hasNext() {
        return cursor < sortedQuests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more quests");
        }
        return sortedQuests.get(cursor++);
    }
}