package com.narxoz.rpg.guild;

public class Scout extends GuildMember {
    private String lastReport = "";

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void sendIntel(String intel) {
        lastReport = intel;
        System.out.println("  🔭 " + getName() + " gathers intel: " + intel);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        switch (topic) {
            case "scouting":
                System.out.println("  🗺️ " + getName() + " receives scouting request from " + from.getName());
                sendIntel("Roads are dangerous, monsters spotted!");
                break;
            case "route":
                System.out.println("  🧭 " + getName() + " plans route based on: " + payload);
                break;
            default:
                System.out.println("  ℹ️ " + getName() + " noted message on [" + topic + "]: " + payload);
        }
    }
}