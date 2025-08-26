
import Project.DatabaseConnector;

import java.sql.*;
import java.util.Random;

public class Library {
    Random rand = new Random();
    private DatabaseConnector db;
    public Library() {
        db = new DatabaseConnector();
    }
    public void addBook(String title, String author, int year) {
        String sql = "INSERT INTO books (id,title, author,year,availability) VALUES (?, ?,?,?,?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))  {
            stmt.setInt(1, rand.nextInt(9999,100000));
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setInt(4, year);
            stmt.setBoolean(5, true);
            stmt.executeUpdate();
            System.out.println("Book added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - "
                        + rs.getString("title") + " by "
                        + rs.getString("author") + " in "
                        + rs.getInt("year") + " -----> "
                        + rs.getBoolean("availability"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void borrowBook(int bookId) {
        String sql = "UPDATE books SET availability=false WHERE id=?";
        if (isBookAvailable(bookId)) {
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
                System.out.println("book is borrowed");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("book is not available");
    }
    public void returnBook(int bookId) {
        String sql = "UPDATE books SET availability=true WHERE id=?";
        if (!isBookAvailable(bookId)) {
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
                System.out.println("book is returned");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("book is already available");
        }
    }


    public void updateBook(int id, String newTitle, String newauthor,int newyear) {
        String sql = "UPDATE books SET title=?, author =?, year =? , availability =? WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, newauthor);
            stmt.setInt(3, newyear);
            stmt.setBoolean(4, true);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Book updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isBookAvailable(int bookId){
        String sql = "SELECT availability FROM books WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("availability");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Book deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void main(String[] args) {
    }
}

