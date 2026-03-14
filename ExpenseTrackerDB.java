import java.sql.*;
import java.util.Scanner;

public class ExpenseTrackerDB {

    static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
    static final String USER = "root";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter MySQL password: ");
        String password = sc.nextLine();

        int choice;

        do {

            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1 Add Expense");
            System.out.println("2 View Expenses");
            System.out.println("3 Delete Expense");
            System.out.println("4 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice){

                case 1:
                    addExpense(sc, password);
                    break;

                case 2:
                    viewExpenses(password);
                    break;

                case 3:
                    deleteExpense(sc, password);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");

            }

        } while(choice != 4);

        sc.close();
    }

    public static void addExpense(Scanner sc, String password) {

        try {

            Connection conn = DriverManager.getConnection(URL, USER, password);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Date (yyyy-mm-dd): ");
            String date = sc.nextLine();

            String sql = "INSERT INTO expenses VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            ps.setString(2,category);
            ps.setDouble(3,amount);
            ps.setString(4,date);

            ps.executeUpdate();

            System.out.println("Expense added!");

            conn.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void viewExpenses(String password) {

        try {

            Connection conn = DriverManager.getConnection(URL, USER, password);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");

            System.out.println("\nID | Category | Amount | Date");

            while(rs.next()){

                int id = rs.getInt("id");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");

                System.out.println(id+" | "+category+" | "+amount+" | "+date);
            }

            conn.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteExpense(Scanner sc, String password){

        try{

            Connection conn = DriverManager.getConnection(URL, USER, password);

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM expenses WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);

            ps.executeUpdate();

            System.out.println("Expense deleted!");

            conn.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}