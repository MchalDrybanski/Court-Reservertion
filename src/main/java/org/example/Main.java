package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, List<Integer>> votes = new HashMap<>(Map.of(
                "Jan",List.of(1,2),
                "Ania",List.of(1,5),
                "Andrzej",List.of(1),
                "Kamil",List.of(1),
                "Wojtek",List.of(2,1),
                "Paulina", List.of(5,1),
                "Marcin", List.of(4,1),
                "Karolina",List.of(3,1),
                "Dominika",List.of(0,1),
                "Szymon",List.of(2,1)
             //   "Ula", List.of(4,1)
        ));

        //Form form = new Form(votes);
        VoteService voteService = new VoteService(3);
        voteService.collectVotes();
        int[] days = voteService.countVotesPerDay();
        voteService.printMembersVotes();
        int maxDayIndex = voteService.findMaxVotesDay(days);
        voteService.printReport(maxDayIndex,days);
        Payments payments = new Payments(voteService);

//        Form form = new Form(15);//todo ile osób ma głosować?
//        form.collectVotes();
//        form.printMembersVotes();
    }
}