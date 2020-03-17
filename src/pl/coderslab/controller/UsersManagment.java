package pl.coderslab.controller;

import pl.coderslab.dao.UsersDao;
import pl.coderslab.model.Users;

import java.util.Scanner;

public class UsersManagment {
    private static String SEPARATOR = "}----------------------------{";
    private static UsersDao UDAO = new UsersDao();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String commands = "";
        System.out.println(SEPARATOR);
        boolean quit = false;
        while (!quit) {
            Users[] all = UDAO.findAll();

            System.out.println("Users in system:");
            for (int i = 0; i < all.length; i++) {
                System.out.println(all[i].getId() + ". " + all[i].getUsername());
            }

            System.out.println(SEPARATOR);

            System.out.println("Select - options:");
            System.out.println("1 - add User");
            System.out.println("2 - edit User");
            System.out.println("3 - delete User");

            System.out.println(SEPARATOR);

            quit = commandsDecision(scan, quit);

        }

        System.out.println(SEPARATOR);
    }

    private static boolean commandsDecision(Scanner scan, boolean quit) {

        String commands = scan.nextLine();
        System.out.println(SEPARATOR);
        if (commands.equals("quit")) {
            quit = true;
        }else if (commands.equals("add")) {
            addUser(scan);
        }else if (commands.equals("1")) {
            addUser(scan);

        }else if (commands.equals("edit")) {
            editUser(scan);
        }else if (commands.equals("2")) {
            editUser(scan);
        }else if (commands.equals("delete")) {
            deleteUser(scan);
        } else if (commands.equals("3")) {
            deleteUser(scan);
        }else if (commands.equals("")){
            System.out.println("Wrong Choice!");
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
            Users tester=UDAO.create(user);
            if(user!=null) {
                System.out.println("User added successfully.");
            }else{
                System.out.println("Error! Can't add user to database.");
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
