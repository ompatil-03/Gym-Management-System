import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class GymManagementSystem {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static MembersDAO memberDAO = new MembersDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nGym Management System");
            System.out.println("1. Add Member");
            System.out.println("2. View All Members");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Search Member by Name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addMember();
                        break;
                    case 2:
                        viewAllMembers();
                        break;
                    case 3:
                        updateMember();
                        break;
                    case 4:
                        deleteMember();
                        break;
                    case 5:
                        searchMemberByName();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (SQLException | ParseException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addMember() throws SQLException, ParseException {
        Member member = new Member();
        System.out.print("Enter name: ");
        member.setName(scanner.nextLine());
        System.out.print("Enter age: ");
        member.setAge(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        System.out.print("Enter contact: ");
        member.setContact(scanner.nextLine());
        System.out.print("Enter membership type: ");
        member.setMembershipType(scanner.nextLine());
        System.out.print("Enter start date (yyyy-MM-dd): ");
        member.setStartDate(DATE_FORMAT.parse(scanner.nextLine()));
        System.out.print("Enter expiry date (yyyy-MM-dd): ");
        member.setExpiryDate(DATE_FORMAT.parse(scanner.nextLine()));

        memberDAO.addMember(member);
        System.out.println("Member added successfully!");
    }

    private static void viewAllMembers() throws SQLException {
        List<Member> members = memberDAO.getAllMembers();
        System.out.println("All Members:");
        for (Member member : members) {
            System.out.println(member.getId() + ": " + member.getName() + " - " + member.getMembershipType());
        }
    }

    private static void updateMember() throws SQLException, ParseException {
        System.out.print("Enter member ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Member member = memberDAO.searchMemberByName(Integer.toString(id));
        if (member != null) {
            System.out.print("Enter new name (current: " + member.getName() + "): ");
            member.setName(scanner.nextLine());
            System.out.print("Enter new age (current: " + member.getAge() + "): ");
            member.setAge(scanner.nextInt());
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new contact (current: " + member.getContact() + "): ");
            member.setContact(scanner.nextLine());
            System.out.print("Enter new membership type (current: " + member.getMembershipType() + "): ");
            member.setMembershipType(scanner.nextLine());
            System.out.print("Enter new start date (current: " + DATE_FORMAT.format(member.getStartDate()) + ", yyyy-MM-dd): ");
            member.setStartDate(DATE_FORMAT.parse(scanner.nextLine()));
            System.out.print("Enter new expiry date (current: " + DATE_FORMAT.format(member.getExpiryDate()) + ", yyyy-MM-dd): ");
            member.setExpiryDate(DATE_FORMAT.parse(scanner.nextLine()));

            memberDAO.updateMember(member);
            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member not found!");
        }
    }

    private static void deleteMember() throws SQLException {
        System.out.print("Enter member ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        memberDAO.deleteMember(id);
        System.out.println("Member deleted successfully!");
    }

    private static void searchMemberByName() throws SQLException {
        System.out.print("Enter member name to search: ");
        String name = scanner.nextLine();

        Member member = memberDAO.searchMemberByName(name);
        if (member != null) {
            System.out.println("Member found: " + member.getId() + " - " + member.getName());
        } else {
            System.out.println("Member not found!");
        }
    }
}
