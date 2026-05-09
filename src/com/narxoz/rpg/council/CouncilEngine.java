package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.List;

public class CouncilEngine {
    private int questsTraversed = 0;
    private int messagesRouted = 0;
    private int membersNotified = 0;

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildHall hall) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           WAR COUNCIL PLANNING SESSION                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("👥 Council Attendees:");
        for (Hero hero : party) {
            System.out.println("  ⚔️ " + hero);
        }
        
        // Demonstrate different iterators
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📋 ITERATOR 1: Ordered Quest Traversal");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;
            System.out.println("\n  📜 REVIEWING: " + quest);
            System.out.println("     Description: " + quest.getDescription());
            
            // Dispatch messages through mediator for each quest
            hall.dispatch("orders", null, "Review quest: " + quest.getTitle());
            hall.dispatch("scouting", null, "Gather intel for " + quest.getTitle());
            messagesRouted += 2;
            membersNotified += hall.subscribersFor("orders").size() + hall.subscribersFor("scouting").size();
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📋 ITERATOR 2: Priority-Based Traversal (HIGH+)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest quest = priority.next();
            questsTraversed++;
            System.out.println("\n  🔥 HIGH PRIORITY: " + quest);
            System.out.println("     Reward: " + quest.getReward() + " gold");
            
            // High priority quests get extra coordination
            hall.dispatch("supplies", null, "Extra supplies for high priority quest");
            hall.dispatch("healing", null, "Standby medics for dangerous mission");
            hall.dispatch("reward", null, "Bonus reward for " + quest.getTitle());
            messagesRouted += 3;
            membersNotified += hall.subscribersFor("supplies").size() + 
                              hall.subscribersFor("healing").size() + 
                              hall.subscribersFor("reward").size();
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📋 ITERATOR 3: Reward-Sorted Traversal (Open/Closed Proof)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        QuestIterator rewardSorted = questLog.rewardSorted();
        while (rewardSorted.hasNext()) {
            Quest quest = rewardSorted.next();
            questsTraversed++;
            System.out.println("\n  💰 HIGHEST REWARD: " + quest);
            System.out.println("     Priority: " + quest.getPriority());
            
            hall.dispatch("defense", null, "Protect high-value mission: " + quest.getTitle());
            messagesRouted++;
            membersNotified += hall.subscribersFor("defense").size();
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                 COUNCIL PLANNING COMPLETE               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        // В CouncilEngine.runCouncil() добавить демонстрацию hero communication:

System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
System.out.println("📋 Heroes Participating in Council Communication");
System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

for (Hero hero : party) {
    // Heroes request quests
    hero.requestQuest("Dragon Hunt");
    hero.sendMessage("scouting", "I can scout ahead for the party");
    
    // Heroes report completion after quest
    hero.reportCompletion("Goblin Caves", 150);
    
    messagesRouted += 2;
    membersNotified += hall.subscribersFor("quest_request").size() + 
                       hall.subscribersFor("quest_complete").size();
}
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}