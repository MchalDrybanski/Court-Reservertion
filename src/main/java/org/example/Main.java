package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
     //   List<Integer> votes = List.of(1, 1, 1, 2, 2, 3, 1, 3, 4, 4, 4, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2);
      //  Form form = new Form(votes);
      //  int[] days = form.countVotesPerDay();
      //  int maxDayIndex = form.findMaxVotesDay(days);
        Form form = new Form(15);
        form.start();
        form.printMembersVotes();
    }
}