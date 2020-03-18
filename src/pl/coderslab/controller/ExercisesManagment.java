package pl.coderslab.controller;

import pl.coderslab.dao.ExercisesDao;
import pl.coderslab.model.Exercise;

import java.util.Scanner;

public class ExercisesManagment {
    private static String SEPARATOR = "----------------------------";
    private static ExercisesDao EDAO = new ExercisesDao();

    public static void main(String[] args) {
        exercises();
    }

    static void exercises() {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        do {
            Exercise[] all = EDAO.findAll();

            System.out.println("Exercises in system:");
            findAllExercises(all);


            System.out.println("Select one options:");
            System.out.println("1 - add Exercise");
            System.out.println("2 - edit Exercise");
            System.out.println("3 - delete Exercise");
            System.out.println("4 - Quit");


            quit = commandsDecision(scan, quit);

        } while (!quit);

    }

    private static void findAllExercises(Exercise[] all) {
        for (Exercise eExercise : all) {
            System.out.println(eExercise.getId() + ". " + eExercise.getTitle());
        }
    }

    private static boolean commandsDecision(Scanner scan, boolean quit) {
        String commands = scan.nextLine();
        System.out.println(SEPARATOR);

        switch (commands) {
            case "quit":
                quit = true;
                break;
            case "4":
                quit = true;
                break;
            case "add":
            case "1":
                addExercise(scan);
                break;

            case "edit":
            case "2":
                editExercise(scan);
                break;
            case "delete":
            case "3":
                deleteExercise(scan);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commands);
        }
        return quit;
    }

    private static void deleteExercise(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("Enter id to delete");
        int delete = scan.nextInt();

        EDAO.delete(delete);
        System.out.println("Exercise has been deleted");
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void editExercise(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("Enter exercise id to edit data : ");
        int exerciseId = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter exercise title");

        String title = scan.nextLine();

        System.out.println("Enter description");

        String description = scan.nextLine();

        Exercise user = new Exercise();
        user.setId(exerciseId);
        user.setTitle(title);
        user.setDescription(description);
        EDAO.update(user);
        System.out.println("Exercise has been updated");
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void addExercise(Scanner scan) {
        try {
            System.out.println("I need you to enter some data");

            System.out.println("Enter title");

            String title = scan.nextLine();

            System.out.println("Enter description");

            String description = scan.nextLine();

            System.out.println();
            Exercise excercise = new Exercise(title, description);
            Exercise tester = EDAO.create(excercise);
            if (tester != null) {
                System.out.println("Exercise added successfully.");
                scan.nextLine();

            } else {
                System.out.println("Error! Can't add exercise to database.");
                scan.nextLine();

            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("Something went Wrong");
            System.out.println(SEPARATOR);
            System.out.println();
            scan.nextLine();

        }
        System.out.println(SEPARATOR);
    }


}
