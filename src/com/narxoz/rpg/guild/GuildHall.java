package com.narxoz.rpg.guild;

import java.util.*;

public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int totalMessagesRouted = 0;

    @Override
    public void register(GuildMember member) {
        // Register for default topics based on member type
        if (member instanceof Quartermaster) {
            addSubscriber("supplies", member);
            addSubscriber("reward", member);
        } else if (member instanceof Scout) {
            addSubscriber("scouting", member);
            addSubscriber("route", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
            addSubscriber("aid", member);
        } else if (member instanceof Captain) {
            addSubscriber("orders", member);
            addSubscriber("attack", member);
            addSubscriber("defense", member);
        } else if (member instanceof Loremaster) {
            addSubscriber("lore", member);
            addSubscriber("history", member);
            addSubscriber("curse", member);
        }
        System.out.println("  📝 Registered: " + member.getName());
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        System.out.println("\n  📢 " + from.getName() + " dispatches on [" + topic + "]: " + payload);
        List<GuildMember> subscribers = subscribersFor(topic);
        totalMessagesRouted += subscribers.size();
        
        for (GuildMember member : subscribers) {
            if (member != from) { // Don't send to self
                member.receive(topic, from, payload);
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
    
    public int getTotalMessagesRouted() {
        return totalMessagesRouted;
    }
}