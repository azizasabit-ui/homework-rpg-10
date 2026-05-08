package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {
    private int suppliesAllocated = 0;

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void allocateSupplies(int amount) {
        suppliesAllocated += amount;
        System.out.println("  🎒 " + getName() + " allocates " + amount + " supplies!");
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "supplies":
                System.out.println("  📦 " + getName() + " received supply request from " + from.getName());
                allocateSupplies(50);
                break;
            case "reward":
                System.out.println("  💰 " + getName() + " prepares reward chest: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " noted message on [" + topic + "]: " + payload);
        }
    }
}