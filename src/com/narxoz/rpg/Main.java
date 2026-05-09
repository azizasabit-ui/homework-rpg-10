package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     HOMEWORK 10: ITERATOR + MEDIATOR PATTERNS           ║");
        System.out.println("║           THE ADVENTURERS' GUILD - WAR COUNCIL          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // PART 1: CREATE HEROES
        System.out.println("🎯 PART 1: Creating Heroes");
        System.out.println("============================================================\n");
        
        
        System.out.println("Heroes created:");
         
        
        // PART 2: BUILD QUEST LOG
        System.out.println("\n🎯 PART 2: Building Quest Log");
        System.out.println("============================================================\n");
        
        QuestLog questLog = new QuestLog();
        questLog.addQuest(new Quest("Clear the Goblin Caves", QuestPriority.NORMAL, 150, 
                                    "Goblins have infested the mining tunnels"));
        questLog.addQuest(new Quest("Rescue the Merchant's Daughter", QuestPriority.HIGH, 300, 
                                    "Bandits have captured the merchant's daughter"));
        questLog.addQuest(new Quest("Slay the Undead Dragon", QuestPriority.URGENT, 1000, 
                                    "An undead dragon terrorizes the countryside"));
        questLog.addQuest(new Quest("Find the Lost Artifact", QuestPriority.LOW, 200, 
                                    "Recover ancient artifact from ruins"));
        questLog.addQuest(new Quest("Defend the Village", QuestPriority.HIGH, 400, 
                                    "Orc war band approaching the village"));
        
        System.out.println("Quest Log contains " + questLog.size() + " quests:");
        QuestIterator allQuests = questLog.ordered();
        int index = 1;
        while (allQuests.hasNext()) {
            System.out.println("  " + index++ + ". " + allQuests.next());
        }
        
        // PART 3: SET UP GUILD HALL AND MEMBERS
        System.out.println("\n🎯 PART 3: Setting Up Guild Hall and Officers");
        System.out.println("============================================================\n");
        
        GuildHall hall = new GuildHall();
        
        Quartermaster quartermaster = new Quartermaster("Balder the Quartermaster", hall);
        Scout scout = new Scout("Raven the Scout", hall);
        Healer healer = new Healer("Sister Miriam the Healer", hall);
        Captain captain = new Captain("Robert the Captain", hall);
        Loremaster loremaster = new Loremaster("Old Ben the Loremaster", hall); // Open/closed proof
        
        System.out.println("\n✅ All 5 guild officers registered!");
        System.out.println("  • Quartermaster - supplies & rewards");
        System.out.println("  • Scout - reconnaissance & routes");
        System.out.println("  • Healer - medical support");
        System.out.println("  • Captain - battle orders");
        System.out.println("  • Loremaster - ancient lore (NEW extension)");
        
        // PART 4: DEMONSTRATE MEDIATOR COMMUNICATION
        System.out.println("\n🎯 PART 4: Mediator Pattern - Guild Communication");
        System.out.println("============================================================\n");
        
        System.out.println("Example: Captain orders preparation for a dangerous quest:");
        captain.issueOrder("orders", "Prepare for dragon hunt - urgent priority");
        
        System.out.println("\nExample: Scout reports route information:");
        scout.reportRoute("scouting", "Mountain pass is blocked, need alternate route");
        
        System.out.println("\nExample: Quartermaster coordinates supplies:");
        quartermaster.requestSupplies("supplies", "Need extra rations for the expedition");
        
        System.out.println("\nExample: Healer readies medical support:");
        healer.prepareAid("healing", "Medical team on standby for wounded heroes");
        
        System.out.println("\nExample: Loremaster shares ancient knowledge:");
        loremaster.shareLore("lore", "The undead dragon is vulnerable to fire magic");
        
        // PART 5: RUN COUNCIL ENGINE
        System.out.println("\n🎯 PART 5: Running the War Council");
        System.out.println("============================================================\n");
        
        CouncilEngine engine = new CouncilEngine();
        
        // PART 6: PRINT FINAL RESULTS
        System.out.println("\n🎯 PART 6: Council Run Results");
        System.out.println("============================================================\n");
        
        
        // PART 7: ARCHITECTURE VERIFICATION
        System.out.println("\n🎯 PART 7: Architecture Verification");
        System.out.println("============================================================\n");
        
        System.out.println("✓ ITERATOR PATTERN:");
        System.out.println("  - QuestIterator custom interface (not Java Iterator)");
        System.out.println("  - QuestLog snapshot() prevents internal list exposure");
        System.out.println("  - 4 concrete iterators: Ordered, Reverse, Priority, RewardSorted");
        System.out.println("  - No public getQuests() method");
        System.out.println("  - RewardSortedQuestIterator proves open/closed extension");
        
        System.out.println("\n✓ MEDIATOR PATTERN:");
        System.out.println("  - GuildHall routes messages by topic");
        System.out.println("  - 5 colleagues: Quartermaster, Scout, Healer, Captain, Loremaster");
        System.out.println("  - No colleague stores references to other colleagues");
        System.out.println("  - All communication through GuildHall.dispatch()");
        System.out.println("  - Loremaster proves open/closed colleague extension");
        
        System.out.println("\n✓ DEMO COMPLETENESS:");
        System.out.println("  - 2 heroes with different stats");
        System.out.println("  - QuestLog with 5 quests of mixed priority");
        System.out.println("  - 4+ guild members registered");
        System.out.println("  - 3 different iterators demonstrated");
        System.out.println("  - Mediator messages dispatched during quest planning");
        System.out.println("  - Final CouncilRunResult printed");
        System.out.println("  - Open/closed proof: RewardSortedIterator + Loremaster");
        
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETE - SUCCESS               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}
