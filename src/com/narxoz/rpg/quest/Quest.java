package com.narxoz.rpg.quest;

public class Quest {
    private final String title;
    private final QuestPriority priority;
    private final int reward;
    private final String description;

    public Quest(String title, QuestPriority priority, int reward, String description) {
        this.title = title;
        this.priority = priority;
        this.reward = reward;
        this.description = description;
    }

    public String getTitle() { return title; }
    public QuestPriority getPriority() { return priority; }
    public int getReward() { return reward; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("'%s' [%s, Reward: %d gold]", title, priority, reward);
    }
}