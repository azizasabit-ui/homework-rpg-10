package com.narxoz.rpg.council;

public class CouncilRunResult {
    private final int questsTraversed;
    private final int messagesRouted;
    private final int membersNotified;

    public CouncilRunResult(int questsTraversed, int messagesRouted, int membersNotified) {
        this.questsTraversed = questsTraversed;
        this.messagesRouted = messagesRouted;
        this.membersNotified = membersNotified;
    }

    public int getQuestsTraversed() { return questsTraversed; }
    public int getMessagesRouted() { return messagesRouted; }
    public int getMembersNotified() { return membersNotified; }

    @Override
    public String toString() {
        return "╔════════════════════════════════════════╗\n" +
               "║      COUNCIL RUN RESULTS               ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║ Quests Traversed:  " + String.format("%-14d", questsTraversed) + "║\n" +
               "║ Messages Routed:   " + String.format("%-14d", messagesRouted) + "║\n" +
               "║ Members Notified:  " + String.format("%-14d", membersNotified) + "║\n" +
               "╚════════════════════════════════════════╝";
    }
}