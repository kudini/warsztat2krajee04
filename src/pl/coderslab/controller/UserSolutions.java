package pl.coderslab.controller;

import pl.coderslab.dao.ExercisesDao;
import pl.coderslab.dao.SolutionsDao;
import pl.coderslab.dao.UsersDao;
import pl.coderslab.model.Solutions;

import java.util.Scanner;

public class UserSolutions {
    private static String SEPARATOR = "----------------------------";
    private static SolutionsDao SDAO = new SolutionsDao();
    private static UsersDao UDAO = new UsersDao();
    private static ExercisesDao EDAO = new ExercisesDao();

    public static void main(String[] args) {
        int id = Integer.parseInt(args[0]);
        assignA(id);
    }

    static void assignA(int id) {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        do {

            System.out.println("Select one options:");
            System.out.println("1 - add Solution");
            System.out.println("2 - view Solutions");
            System.out.println("3 - Quit");


            quit = commandsDecision(scan, quit, id);

        } while (!quit);

    }

    private static void findAllSolutions(Solutions[] all) {
        for (Solutions solutions : all) {
            System.out.println(solutions.getId() + "." + " for exercise:" + solutions.getExcerciseId() + " by user: " + solutions.getUsersId());
            System.out.println("Created:" + solutions.getCreated() + " Updated:" + solutions.getUpdated());
            System.out.println(solutions.getDescription());
        }
    }

    private static boolean commandsDecision(Scanner scan, boolean quit, int id) {
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
                addSolution(scan, id);
                break;

            case "view":
            case "2":
                view(scan, id);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commands);
        }
        return quit;
    }

    private static void view(Scanner scan, int id) {
        System.out.println(SEPARATOR);
        System.out.println("Solutions in database:");
        Solutions[] allU = SDAO.findAllByUserId(id);
        findAllSolutions(allU);
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void addSolution(Scanner scan, int id) {
        try {
            System.out.println(SEPARATOR);
            System.out.println("Solutions in database:");
            Solutions[] allU = SDAO.findAllByUserId(id);
            findAllSolutions(allU);
            System.out.println("Enter Solution Id");
            int solutionid = scan.nextInt();

            scan.nextLine();
            System.out.println("Enter Solution");
            String description = scan.nextLine();

            Solutions newSolution = new Solutions();

            long millis = System.currentTimeMillis();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = convert(utilDate);
            newSolution.setId(solutionid);
            newSolution.setUpdated(sqlDate);
            newSolution.setDescription(description);
            SDAO.update(newSolution);
            System.out.println("Solution has been updated");
            System.out.println(SEPARATOR);
            scan.nextLine();

        } catch (Exception e) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("Wrong id went");
            System.out.println(SEPARATOR);
            System.out.println();
            scan.nextLine();

        }
        System.out.println(SEPARATOR);

    }

    public static java.sql.Date convert(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
