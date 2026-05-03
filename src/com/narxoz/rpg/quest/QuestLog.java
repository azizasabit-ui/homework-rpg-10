package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestLog {
    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public List<Quest> snapshot() {
        return new ArrayList<>(quests);
    }

    public QuestIterator ordered() {
        return new OrderedQuestIterator(this);
    }

    public QuestIterator reverse() {
        return new ReverseQuestIterator(this);
    }

    public QuestIterator priorityAtLeast(QuestPriority minPriority) {
        return new PriorityQuestIterator(this, minPriority);
    }

    public QuestIterator rewardSorted() {
        return new RewardSortedQuestIterator(this);
    }

    public int size() {
        return quests.size();
    }
}