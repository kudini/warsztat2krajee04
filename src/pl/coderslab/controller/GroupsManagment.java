package pl.coderslab.controller;

import pl.coderslab.dao.UsersGroupsDao;
import pl.coderslab.model.UsersGroups;

import java.util.Scanner;

public class GroupsManagment {
    private static String SEPARATOR = "----------------------------";
    private static UsersGroupsDao UGDAO = new UsersGroupsDao();

    public static void main(String[] args) {
        groups();
    }

    static void groups() {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        do {
            UsersGroups[] all = UGDAO.findAll();

            System.out.println("Groups in system:");
            findAllGroups(all);


            System.out.println("Select options:");
            System.out.println("1 - add Group");
            System.out.println("2 - edit Group");
            System.out.println("3 - delete Group");
            System.out.println("4 - Quit");


            quit = commandsDecision(scan, quit);

        } while (!quit);

    }

    private static void findAllGroups(UsersGroups[] all) {
        for (UsersGroups usersGroups : all) {
            System.out.println(usersGroups.getId() + ". " + usersGroups.getName());
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
                addGroups(scan);
                break;

            case "edit":
            case "2":
                editGroups(scan);
                break;
            case "delete":
            case "3":
                deleteGroups(scan);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commands);
        }
        return quit;
    }

    private static void deleteGroups(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("Enter id to delete");
        int delete = scan.nextInt();

        UGDAO.delete(delete);
        System.out.println("Group has been deleted");
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void editGroups(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("Enter group id to edit data : ");
        int groupId = scan.nextInt();
        scan.nextLine();
        System.out.println("name");
        String name = scan.nextLine();

        UsersGroups uGroup = new UsersGroups();
        uGroup.setName(name);
        uGroup.setId(groupId);
        UGDAO.update(uGroup);

        System.out.println("Group has been updated");
        scan.nextLine();

    }

    private static void addGroups(Scanner scan) {
        try {
            System.out.println("I need you to enter some data");
            System.out.println("Enter group name");

            String user_group_name = scan.nextLine();


            System.out.println();

            UsersGroups u = new UsersGroups(user_group_name);
            if (UGDAO.create(u) != null) {
                System.out.println("Group added successfully.");
            } else {
                System.out.println("Error! Can't add group to database.");
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
