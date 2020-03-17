package pl.coderslab.controller;

import java.util.Scanner;

public class AdminTools {
    private static String SEPARATOR = "----------------------------";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        while (!quit) {

            System.out.println("Select - options:");
            System.out.println("1 - Users");
            System.out.println("2 - Exercises");
            System.out.println("3 - Groups");
            System.out.println("4 - Asign Exercises");
            System.out.println("5 - Quit");


            quit = commandsDecision(scan, quit);

        }

    }

    private static boolean commandsDecision(Scanner scan, boolean quit) {

        String commands = scan.nextLine().toLowerCase();
        System.out.println(SEPARATOR);
        switch (commands) {
            case "quit":
                quit = true;
                break;
            case "5":
                quit = true;
                break;
            case "users":
            case "1":
                UsersManagment.users();
                break;

            case "exercises":
            case "2":
                ExercisesManagment.exercises();
                break;
            case "groups":
            case "3":
                GroupsManagment.groups();
                break;
            case "asign":
            case "4":
                ExercisesAssignment.assignA();
                break;
            case "":
                System.out.println("Wrong Choice!");
                break;
        }
        return quit;
    }
}
