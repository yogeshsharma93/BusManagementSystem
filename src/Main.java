package busmanagementsystem;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        ConnectJDBC base = new ConnectJDBC();
        base.setconnection();

        System.out.println("-----Enter Whom Your-----");
        System.out.println("|    |1|CUSTOMER        |");
        System.out.println("|    |2|ADMIN           |");
        System.out.println("-------------------------");
        System.out.print("Enter your choice: ");
        int user_choice = sc.nextInt();

        if (user_choice == 1) {
            User user = new User();
            user.startUserTasks();
            user.userMenu();
        } else if (user_choice == 2) {
            Admin admin = new Admin();
            admin.startAdminTasks();
            admin.adminMenu();
        } else {
            System.out.println("Invalid Option");
        }

        sc.close();
    }
}