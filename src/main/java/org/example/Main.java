package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, List<Integer>> votes = new HashMap<>(Map.of(
                "Jan",List.of(0,1,2,3,4),
                "Ania",List.of(1,5),
                "Andrzej",List.of(1),
                "Kamil",List.of(1),
                "Wojtek",List.of(2,1),
                "Paulina", List.of(5,1),
                "Marcin", List.of(4,1),
                "Karolina",List.of(3,1),
                "Dominika",List.of(0,1),
                "Szymon",List.of()
             //   "Ula", List.of(4,1)
        ));

        VoteService voteService = new VoteService(votes);
        //VoteService voteService = new VoteService(3);
        voteService.collectVotes();
        int[] days = voteService.countVotesPerDay();
        voteService.printMembersVotes();
        int maxDayIndex = voteService.findMaxVotesDay(days);
        voteService.printReport(maxDayIndex,days);
        System.out.println("-------------");
        voteService.printMembersWhoVotedWinningDay();
        System.out.println("-------------");
        voteService.printMembersWhoNotVoted();
        Payments payments = new Payments(voteService);
       // payments.printList(voteService);
        //payments.addPersonWhoPaid(4);
       // payments.printListOfPeopleWhoPaid();




        //VoteService form = new VoteService(10);//todo ile osób ma głosować?
        //voteService.collectVotes();
        //voteService.printMembersVotes();
    }
}