package org.example;

import java.util.*;

public class VoteService {
    private int numberOfPeople;
    private List<Person> people = new ArrayList<>();
    public VoteService(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    public VoteService() {}
    public VoteService(List<Person> people) {
        this.people = people;
    }

    public void collectVotes() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfPeople; i++) {
            List<Integer> onePersonChosenDaysIndexes = new ArrayList<>();
            printMassege("Podaj imie osoby glosujacej: ");
            String name = scanner.next();

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
            people.add(new Person(name, onePersonChosenDaysIndexes));
        }
    }
    private List<String> listOfPeopleNotVoted() {
        List<String> names = new ArrayList<>();

        for (Person person : people) {
            if (!person.hasVoted()) {
                names.add(person.getFullName());
            }
        }

        sortList(names);
        return names;
    }
    private List<String> whoVotedForWinningDay(){
        int [] votesPerDay = countVotesPerDay();
        int winningDay = findMaxVotesDay(votesPerDay);

        List<String> listOfPeopleVotingWinnerDay = new ArrayList<>();

        for(Person person : people){
            if(person.hasVotedForDay(winningDay)){
                listOfPeopleVotingWinnerDay.add(person.getFullName());
            }
        }
        sortList(listOfPeopleVotingWinnerDay);
        return listOfPeopleVotingWinnerDay;
    }
    private void sortList (List<String> listToSort){
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
        for(Person person : people){
            chosenDays.addAll(person.getVotedDays());
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
        List<String> notVoted = listOfPeopleNotVoted();
        for(String notVot : notVoted){
            printMassege(notVot);
        }
    }
    public void printMembersWhoVotedWinningDay(){
        List<String> winners = whoVotedForWinningDay();
        for(String win : winners){
            printMassege(win);
        }
    }
    public void printMassege(String message) {
        System.out.println(message);
    }

    public void printMembersVotes() {
        for (Person person : people) {
            List<Integer> dayIndexes = person.getVotedDays();
            StringBuilder sb = new StringBuilder(person.getFullName() + " zaglosowal/a na: ");
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
    public List<String> getMemberList(){
        List<String> memberList = new ArrayList<>();
        for (Person person : people){
            memberList.add(person.getFullName());
        }
        return memberList;
    }

    @Override
    public String toString() {
        return "VoteService{" +
                "people=" + people +
                '}';
    }
}
