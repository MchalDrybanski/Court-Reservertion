package org.example;

import java.util.*;

public class Form {
    private int numberOfPeople;
    private List<Integer> choosenDays = new ArrayList<>();
    private Map<String, Integer> votingMember = new HashMap<>();

    public Map<String, Integer> getVotingMember() {
        return votingMember;
    }

    public void setVotingMember(Map<String, Integer> votingMember) {
        this.votingMember = votingMember;
    }

    public Form(int numberOfPeople){
        this.numberOfPeople = numberOfPeople;
    }
    public Form(){

    }

    public Form(List<Integer> choosenDays) {
        this.choosenDays = choosenDays;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < numberOfPeople; i++){
            List<Integer> onePersonChosenDays = new ArrayList<>();
            printMassege("Podaj imie osoby glosujacej: ");
            String name = scanner.next();

            while(true){
                printMassege("Wybierz odpowiedni dla Ciebie dzien w tygodniu od poniedzialku do piatku.\n" +
                        "1 - Poniedzialek\n" +
                        "2 - Wtorek\n" +
                        "3 - Sroda\n" +
                        "4 - Czwartek\n" +
                        "5 - Piatek\n" +
                        "6 - Zakoncz wybieranie");
                int choice = scanner.nextInt();
                if(onePersonChosenDays.contains(choice)){
                    printMassege("Ten dzien zostal juz wybrany.");
                    continue;
                }
                if(choice >= 1 && choice <= 5){
                    onePersonChosenDays.add(choice);
                    choosenDays.add(choice);
                    votingMember.put(name, choice);
                }else if(choice == 6){
                    break;
                }
            }
        }
    }
    public void printMassege(String message){
        System.out.println(message);
    }

    public void printMembersVotes(){
        for(Map.Entry<String, Integer> votes : votingMember.entrySet()){
            int day = votes.getValue();
            String converToDay = convertToDayName(day);
            System.out.println(votes.getKey() + " zaglosowal/a na " + converToDay);
        }
    }
    public String convertToDayName(int day){
        switch (day){
            case 1:
                return "Poniedziałek";
            case 2:
                return "Wtorek";
            case 3:
                return "Środa";
            case 4:
                return "Czwartek";
            case 5:
                return "Piątek";
            default:
                return "Nieprawidłowy dzień";
        }
    }
    public int[] countVotesPerDay() {
        int votesPerDay [] = new int[5];
        for(int day = 1; day<=5; day++){
            int counterOfVotes = 0;
            for (Integer compareNumber : choosenDays) {
                if (compareNumber.equals(day)) {
                    counterOfVotes++;
                }
            }
           votesPerDay[day-1] = counterOfVotes;
        }
        return votesPerDay;
    }
    public int findMaxVotesDay(int [] days){
        int mostVotes = days[0];
        int day = 0;
        for(int i = 0; i < days.length; i++){
            if(mostVotes < days[i]){
                mostVotes = days[i];
                day = i;
            }
    }
        return day;
    }

    public void print(int day, int [] tab){
        String dayName;
        switch (day){
            case 0:
                dayName = "Poniedziałek";
                break;
            case 1:
                dayName = "Wtorek";
                break;
            case 2:
                dayName = "Środa";
                break;
            case 3:
                dayName = "Czwartek";
                break;
            case 4:
                dayName = "Piątek";
                break;
            default:
                dayName = "Brak wyboru";
        }
        System.out.println("Najwiecej glosow ma dzien: " + dayName);
        System.out.println("Głosów: " + tab[day]);
        if(tab[day] < 8){
            System.out.println("Nie ma wystarczajoco osob na ten dzien.");
        }else System.out.println("Wybrany dzien to " + dayName);
    }
}
