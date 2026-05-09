package com.narxoz.rpg.guild;

public class Captain extends GuildMember {
    private String currentOrders = "";

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void giveOrders(String orders) {
        currentOrders = orders;
        System.out.println("  ⚔️ " + getName() + " issues orders: " + orders);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "orders":
                System.out.println("  📋 " + getName() + " receives order confirmation from " + from.getName());
                giveOrders("Execute " + payload);
                break;
            case "attack":
                System.out.println("  ⚔️ " + getName() + " coordinates attack plan: " + payload);
                break;
            case "defense":
                System.out.println("  🛡️ " + getName() + " sets defensive positions: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " noted message on [" + topic + "]: " + payload);
        }
    }
}