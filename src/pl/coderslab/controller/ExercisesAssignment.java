package pl.coderslab.controller;

import pl.coderslab.dao.ExercisesDao;
import pl.coderslab.dao.SolutionsDao;
import pl.coderslab.dao.UsersDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solutions;
import pl.coderslab.model.Users;

import java.sql.Date;
import java.util.Scanner;

public class ExercisesAssignment {
    private static String SEPARATOR = "----------------------------";
    private static SolutionsDao SDAO = new SolutionsDao();
    private static UsersDao UDAO = new UsersDao();
    private static ExercisesDao EDAO = new ExercisesDao();

    public static void main(String[] args) {
        assignA();
    }

    static void assignA() {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        do {

            System.out.println("Select one options:");
            System.out.println("1 - add Assignment");
            System.out.println("2 - view Assignment");
            System.out.println("3 - Quit");


            quit = commandsDecision(scan, quit);

        } while (!quit);

    }

    private static void findAllUsers(Users[] all) {
        for (Users users : all) {
            System.out.println(users.getId() + ". " + users.getUsername());
        }
    }

    private static void findAllExercises(Exercise[] all) {
        for (Exercise exercisesA : all) {
            System.out.println(exercisesA.getId() + ". " + exercisesA.getTitle());
        }
    }

    private static void findAllSolutions(Solutions[] all) {
        for (Solutions solutions : all) {
            System.out.println(solutions.getExcerciseId() + ". " + solutions.getUsersId());
            System.out.println(solutions.getUpdated());
            System.out.println(solutions.getDescription());
        }
    }

    private static boolean commandsDecision(Scanner scan, boolean quit) {
        String commands = scan.nextLine();
        System.out.println(SEPARATOR);

        switch (commands) {
            case "quit":
                quit = true;
                break;
            case "3":
                quit = true;
                break;
            case "add":
            case "1":
                addAssigment(scan);
                break;

            case "view":
            case "2":
                editView(scan);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commands);
        }
        return quit;
    }

    private static void editView(Scanner scan) {
        System.out.println(SEPARATOR);

        System.out.println(SEPARATOR);
        System.out.println("Users in database:");
        Users[] allU = UDAO.findAll();
        findAllUsers(allU);
        System.out.println("Enter User Id");
        int userid = scan.nextInt();

        System.out.println("Solutions of user = " + userid + " in database:");
        Solutions[] allS = SDAO.findAllByUserId(userid);
        findAllSolutions(allS);
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void addAssigment(Scanner scan) {
        try {
            System.out.println(SEPARATOR);
            System.out.println("Users in database:");
            Users[] allU = UDAO.findAll();
            findAllUsers(allU);
            System.out.println("Enter User Id");
            int userid = scan.nextInt();

            System.out.println("Exercises in database:");
            Exercise[] allE = EDAO.findAll();
            findAllExercises(allE);
            System.out.println("Enter exercise id");
            int exerciseid = scan.nextInt();

            Solutions newSolution = new Solutions();
            long millis = System.currentTimeMillis();
            newSolution.setCreated(new Date(millis));
            newSolution.setUsersId(userid);
            newSolution.setExcerciseId(exerciseid);
            System.out.println();
            Solutions tester = SDAO.create(newSolution);
            if (tester != null) {
                System.out.println("Solution added successfully.");
                scan.nextLine();

            } else {
                System.out.println("Error! Can't add solution to database.");
                scan.nextLine();

            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("Something went Wrong");
            System.out.println(SEPARATOR);
            e.printStackTrace();
            System.out.println();
            scan.nextLine();

        }
        System.out.println(SEPARATOR);
    }


}
