package pl.coderslab.controller;

import pl.coderslab.dao.UsersDao;
import pl.coderslab.model.Users;

import java.util.Scanner;

public class UsersManagment {
    private static String SEPARATOR = "}----------------------------{";
    private static UsersDao UDAO = new UsersDao();

    public static void main(String[] args) {
        users();
    }

    static void users() {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        boolean quit = false;
        do {
            Users[] all = UDAO.findAll();
            commands = "";

            System.out.println("Users in system:");
            findAllUsers(all);


            System.out.println("Select - options:");
            System.out.println("1 - add User");
            System.out.println("2 - edit User");
            System.out.println("3 - delete User");
            System.out.println("4 - Quit");
            System.out.println(commands);
            quit = commandsDecision(scan, quit);
        }
        while (!quit);

    }

    private static void findAllUsers(Users[] all) {
        for (Users users : all) {
            System.out.println(users.getId() + ". " + users.getUsername());
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
                addUser(scan);
                break;

            case "edit":
            case "2":
                editUser(scan);
                break;
            case "delete":
            case "3":
                deleteUser(scan);
                break;
            default:
                System.out.println("Unexpected value:" + commands);
        }
        return quit;
    }

    private static void deleteUser(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("We need user id to delete");
        int delete = scan.nextInt();
        scan.nextLine();
        UDAO.delete(delete);
        System.out.println("User has been deleted");
        System.out.println(SEPARATOR);
    }

    private static void editUser(Scanner scan) {
        System.out.println(SEPARATOR);
        System.out.println("We need user id to edit data : ");
        int userId = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter user name");

        String username = scan.nextLine();

        System.out.println("Enter email");

        String email = scan.nextLine();

        System.out.println("Enter password ");

        String password = scan.nextLine();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        UDAO.update(user);
        System.out.println("User has been updated");
        System.out.println(SEPARATOR);
        scan.nextLine();

    }

    private static void addUser(Scanner scan) {
        try {

            System.out.println("We need some data to create user");

            System.out.println("Enter username");

            String username = scan.nextLine();

            System.out.println("Enter email");

            String email = scan.nextLine();

            System.out.println("Enter password ");

            String password = scan.nextLine();
            System.out.println("Enter User Group id");
            int userGroupId = scan.nextInt();

            System.out.println();
            Users user = new Users(username, email, password, userGroupId);
            Users tester = UDAO.create(user);
            if (user != null) {
                System.out.println("User added successfully.");
                scan.nextLine();
            } else {
                System.out.println("Error! Can't add user to database.");
                scan.nextLine();
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("Something went Wrong");
            System.out.println(SEPARATOR);
            System.out.println();
        }
        System.out.println(SEPARATOR);
    }

}
