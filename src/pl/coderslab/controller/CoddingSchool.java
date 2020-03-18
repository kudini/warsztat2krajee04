package pl.coderslab.controller;

import pl.coderslab.dao.UsersDao;
import pl.coderslab.model.Users;

import java.util.Scanner;

public class CoddingSchool {
    private static UsersDao UDAO = new UsersDao();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Write your userid:");
        int userid = scan.nextInt();
        Users user = UDAO.read(userid);
        scan.nextLine();
        System.out.println("Write your password:");
        String password = scan.nextLine();
        if (password.equals(user.getPassword())) {
            //u mnie 3 byla grupa admin to jest do dostosoawnia
            if (user.getUserGroupId() == 3) {
                AdminTools.admin();
            } else {
                UserSolutions.assignA(userid);
            }
        } else {
            System.out.println("Wrong password of userid");
        }

    }
}
