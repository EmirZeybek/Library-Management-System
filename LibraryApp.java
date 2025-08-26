
import java.sql.*;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Library library = new Library();
        int choice = 0;
        while (choice != 7) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. Modify a book");
            System.out.println("3. Remove a book");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. List all books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = in.nextInt();
            in.nextLine();
            switch (choice){
                case 1:
                    System.out.print("Enter book title:");
                    String title = in.nextLine();
                    System.out.print("Enter book author:");
                    String author = in.nextLine();
                    System.out.print("Enter book year:");
                    int year = in.nextInt();
                    library.addBook(title,author,year);
                    break;
                case 2:
                    System.out.print("Enter book id u want to update:");
                    int id = in.nextInt();
                    System.out.print("Enter book title:");
                    String title1 = in.next();
                    System.out.print("Enter book author:");
                    String author1 = in.next();
                    System.out.print("Enter book year:");
                    int year1 = in.nextInt();
                    library.updateBook(id,title1,author1,year1);
                    break;
                case 3:
                    System.out.print("Enter book id u want to remove:");
                    int id1 = in.nextInt();
                    library.deleteBook(id1);
                    break;
                case 4:
                    System.out.print("Enter book ID to borrow the book:");
                    int bookId1 = in.nextInt();
                    library.borrowBook(bookId1);
                    break;
                case 5:
                    System.out.print("Enter book ID to return the book:");
                    int bookId2 = in.nextInt();
                    library.returnBook(bookId2);
                    break;
                case 6:
                    library.listBooks();
                    break;
            }
        }
    }
}
