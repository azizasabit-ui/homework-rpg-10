package com.narxoz.rpg.combatant;

import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.guild.GuildMember;
import com.narxoz.rpg.guild.Loremaster;

/**
 * Represents a player-controlled hero participating in the war council.
 * Heroes can also act as guild members to receive quest-related messages.
 */
public class Hero extends GuildMember {
    private int hp;
    private final int maxHp;
    private int mana;
    private int gold;
    private final int attackPower;
    private final int defense;

    public Hero(String name, int hp, int attackPower, int defense, GuildMediator mediator) {
        this(name, hp, 0, attackPower, defense, 0, mediator);
    }

    public Hero(String name, int hp, int mana, int attackPower, int defense, int gold, GuildMediator mediator) {
        super(name, mediator);
        this.hp = hp;
        this.maxHp = hp;
        this.mana = mana;
        this.gold = gold;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    // Getters
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMana() { return mana; }
    public int getGold() { return gold; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - Math.max(0, amount));
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + Math.max(0, amount));
    }

    public void addGold(int amount) {
        gold += Math.max(0, amount);
    }

    public boolean spendGold(int amount) {
        if (amount < 0 || amount > gold) {
            return false;
        }
        gold -= amount;
        return true;
    }
    
    public void restoreMana(int amount) {
        mana += Math.max(0, amount);
    }
    
    public boolean spendMana(int amount) {
        if (amount < 0 || amount > mana) {
            return false;
        }
        mana -= amount;
        return true;
    }

    /**
     * Hero can send messages to other guild members through the mediator
     */
    public void sendMessage(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    /**
     * Hero requests a quest from the guild
     */
    public void requestQuest(String questTitle) {
        sendMessage("quest_request", "Hero " + getName() + " requests: " + questTitle);
    }
    
    /**
     * Hero reports quest completion
     */
    public void reportCompletion(String questTitle, int reward) {
        sendMessage("quest_complete", getName() + " completed: " + questTitle + ", earned " + reward + " gold");
        addGold(reward);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "orders":
                System.out.println("  ⚔️ " + getName() + " receives orders from " + from.getName() + ": " + payload);
                break;
            case "healing":
                System.out.println("  💚 " + getName() + " receives medical aid from " + from.getName());
                heal(30);
                break;
            case "reward":
                System.out.println("  💰 " + getName() + " receives reward: " + payload);
                break;
            case "lore":
                if (from instanceof Loremaster) {
                    System.out.println("  📖 " + getName() + " learns ancient wisdom: " + payload);
                }
                break;
            case "supplies":
                System.out.println("  🎒 " + getName() + " receives supplies: " + payload);
                break;
            case "quest_assigned":
                System.out.println("  📜 " + getName() + " is assigned quest: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " hears on [" + topic + "]: " + payload);
        }
    }

    @Override
    public String toString() {
        return "Hero{name='" + getName() + "', hp=" + hp + "/" + maxHp + 
               ", mana=" + mana + ", gold=" + gold + 
               ", attack=" + attackPower + ", defense=" + defense + "}";
    }
}