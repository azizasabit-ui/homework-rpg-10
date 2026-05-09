package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {
    private int loreCollected = 0;

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void recordHistory(String history) {
        loreCollected++;
        System.out.println("  📚 " + getName() + " records ancient history: " + history);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "lore":
                System.out.println("  📖 " + getName() + " shares ancient knowledge about: " + payload);
                recordHistory(payload);
                break;
            case "history":
                System.out.println("  🏛️ " + getName() + " consults archives for: " + payload);
                break;
            case "curse":
                System.out.println("  ⚠️ " + getName() + " warns about cursed history: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " noted message on [" + topic + "]: " + payload);
        }
    }
}