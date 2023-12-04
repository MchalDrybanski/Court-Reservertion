package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(List.of(
                new Person("Michal", List.of(1,2,3,4)),
                new Person("Adam", List.of(0,1)),
                new Person("Michal D", List.of(0,1)),
                new Person("Andrzej", List.of(0,1)),
                new Person("Dominika", List.of(0,1,3)),
                new Person("Karolina", List.of(0,1,4)),
                new Person("Pawel", List.of(0,1,3)),
                new Person("Pawel D", List.of(0,1,3)),
                new Person("Kamil K", List.of(0,1)),
                new Person("Kamil P", List.of())
        ));

        VoteService voteService = new VoteService(list);
        System.out.println(voteService);
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
        payments.printEveryone();
        payments.addPersonWhoPaid(4);
        payments.printListOfPeopleWhoPaid();




        //VoteService form = new VoteService(10);//todo ile osób ma głosować?
        //voteService.collectVotes();
        //voteService.printMembersVotes();
    }
}