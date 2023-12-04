package org.example;

import java.util.List;

public class Person {
    private String fullName;
    private static int prevId = 0;
    private int id;
    private List <Integer> votedDays;
    private boolean paid;

    public Person(String fullName, List<Integer> votedDays) {
        this.votedDays = votedDays;
        this.fullName = fullName;
        this.id = ++prevId;
    }
    public boolean hasVoted(){
        return votedDays != null && !votedDays.isEmpty();
    }
    public boolean hasVotedForDay(int day){
        return votedDays.contains(day);
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", id=" + id +
                ", votedDays=" + votedDays +
                ", paid=" + paid +
                '}';
    }

    public List<Integer> getVotedDays() {
        return votedDays;
    }

    public String getFullName() {
        return fullName;
    }
}
