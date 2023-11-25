package org.example;

import java.util.*;

public class VoteService {
    private int numberOfPeople;
    private Map<String, List<Integer>> votingMember = new HashMap<>();
    public VoteService(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    public VoteService() {}
    public VoteService(Map<String, List<Integer>> votingMember) {this.votingMember = votingMember;}


    public void collectVotes() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfPeople; i++) {
            List<Integer> onePersonChosenDaysIndexes = new ArrayList<>();
            printMassege("Podaj imie osoby glosujacej: ");
            String name = scanner.next();
            votingMember.put(name, onePersonChosenDaysIndexes);
            while (true) {
                printMassege("Wybierz odpowiedni dla Ciebie dzien w tygodniu od poniedzialku do piatku.\n" +
                        "1 - Poniedzialek\n" +
                        "2 - Wtorek\n" +
                        "3 - Sroda\n" +
                        "4 - Czwartek\n" +
                        "5 - Piatek\n" +
                        "6 - Zakoncz wybieranie");
                int choice = scanner.nextInt();
                if (onePersonChosenDaysIndexes.contains(choice)) {
                    printMassege("Ten dzien zostal juz wybrany.");
                    continue;
                }
                if (choice >= 1 && choice <= 5) {
                    onePersonChosenDaysIndexes.add(choice-1);
                } else if (choice == 6) {
                    break;
                }
            }
        }
    }
    private ArrayList<String> listOfPeopleNotVoted() {
        ArrayList<String> names = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : votingMember.entrySet()) {
            List<Integer> onePersonChosenDaysIndexes = entry.getValue();

            if (onePersonChosenDaysIndexes == null || onePersonChosenDaysIndexes.isEmpty()) {
                names.add(entry.getKey());
            }
        }

        sortList(names);
        return names;
    }
    private ArrayList<String> whoVotedForWinningDay(){
        int [] votesPerDay = countVotesPerDay();
        int winningDay = findMaxVotesDay(votesPerDay);

        ArrayList<String> listOfPeopleVotingWinnerDay = new ArrayList<>();

        for(Map.Entry<String, List<Integer>> votes : votingMember.entrySet()){
            List<Integer> dayIndexes = votes.getValue();
            if(dayIndexes.contains(winningDay)){
                listOfPeopleVotingWinnerDay.add(votes.getKey());
            }
        }
        sortList(listOfPeopleVotingWinnerDay);
        return listOfPeopleVotingWinnerDay;
    }
    private void sortList (ArrayList<String> listToSort){
        Collections.sort(listToSort);
    }
    public int findMaxVotesDay(int[] days) {
        int mostVotes = days[0];
        int day = 0;
        for (int i = 0; i < days.length; i++) {
            if (mostVotes < days[i]) {
                mostVotes = days[i];
                day = i;
            }
        }
        return day;
    }
    public int[] countVotesPerDay() {
        int votesPerDay[] = new int[5];
        for (int day = 0; day < votesPerDay.length; day++) {
            int counterOfVotes = 0;
            for (Integer compareNumber : countChosenDays()) {
                if (compareNumber.equals(day)) {
                    counterOfVotes++;
                }
            }
            votesPerDay[day] = counterOfVotes;
        }
        return votesPerDay;
    }
    private List<Integer> countChosenDays(){
        List<Integer> chosenDays = new ArrayList<>();
        for(List<Integer> votes : votingMember.values()){
            chosenDays.addAll(votes);
        }
        return chosenDays;
    }

    public void printReport(int day, int[] tab) {
        String dayName = convertToDayName(day);
        System.out.println("Najwiecej glosow ma dzien: " + dayName);
        System.out.println("Głosów: " + tab[day]);
        if (tab[day] < 8) {
            System.out.println("Nie ma wystarczajoco osob na ten dzien.");
        } else System.out.println("Wybrany dzien to " + dayName);
    }
    public void printMembersWhoNotVoted(){
        ArrayList<String> notVoted = listOfPeopleNotVoted();
        for(String notVot : notVoted){
            printMassege(notVot);
        }
    }
    public void printMembersWhoVotedWinningDay(){
        ArrayList<String> winners = whoVotedForWinningDay();
        for(String win : winners){
            printMassege(win);
        }
    }
    public void printMassege(String message) {
        System.out.println(message);
    }

    public void printMembersVotes() {
        for (Map.Entry<String, List<Integer>> votes : votingMember.entrySet()) {
            List<Integer> dayIndexes = votes.getValue();
            StringBuilder sb = new StringBuilder(votes.getKey() + " zaglosowal/a na: ");
            int i = 0;
            for(Integer day : dayIndexes){
                sb.append(convertToDayName(day));//todo: DONE zmienic na wyswietlanie w jednej lini
                if(++i < dayIndexes.size()){
                    sb.append(", ");
                }
            }
            printMassege(sb.toString());
        }
    }
    private String convertToDayName(int day) {
        switch (day) {
            case 0:
                return "Poniedziałek";
            case 1:
                return "Wtorek";
            case 2:
                return "Środa";
            case 3:
                return "Czwartek";
            case 4:
                return "Piątek";
            default:
                return "Nieprawidłowy dzień";
        }
    }
    public ArrayList<String> getMemberList(){
        ArrayList<String> memberList = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> votes : votingMember.entrySet()){
            memberList.add(votes.getKey());
        }
        return memberList;
    }
}
