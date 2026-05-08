package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    private int healingPrepared = 0;

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void prepareHealingPotions(int amount) {
        healingPrepared += amount;
        System.out.println("  🧪 " + getName() + " prepares " + amount + " healing potions!");
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "healing":
                System.out.println("  💚 " + getName() + " receives healing request from " + from.getName());
                prepareHealingPotions(30);
                break;
            case "aid":
                System.out.println("  🩹 " + getName() + " prepares medical aid: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " noted message on [" + topic + "]: " + payload);
        }
    }
}