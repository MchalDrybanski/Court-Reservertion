package org.example;

import java.util.*;

public class Payments {
   private Scanner scanner = new Scanner(System.in);
    private VoteService voteService;
    private List<String> listOfPeopleWhoPaid = new ArrayList<>();

    public Payments(VoteService voteService) {
        this.voteService = voteService;
    }

    public void printEveryone(){
        for(String member : voteService.getMemberList()){
            System.out.println(member);
        }
    }
    private String findPerson(String name){
        for(String member : voteService.getMemberList()){
            if(member.contains(name)){
                return name;
            }
        }
        return null;
    }
    public List<String> addPersonWhoPaid(int numberOfPeople){
        voteService.printMassege("Podaj imiona ktore chcesz dodac do listy osob oplaconych: ");
        for(int i = 0; i < numberOfPeople; i++){
            String name = scanner.next();
            if(findPerson(name) != null){
                listOfPeopleWhoPaid.add(name);
            }else
                voteService.printMassege("Taka osoba nie istnieje na liscie czlonkow.");
        }
        return listOfPeopleWhoPaid;
    }
    public void printListOfPeopleWhoPaid(){
        for(String list : listOfPeopleWhoPaid)
            voteService.printMassege(list);
    }
}
