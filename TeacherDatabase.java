package Repository;

import Domain.Teacher;
import java.sql.*;
import java.util.ArrayList;

public class TeacherDatabase
{
    public TeacherDatabase() {}

    private static final String JDBC_URL = "jdbc:sqlite:data/teacher.db";

    private Connection conn = null;

    /**
     * Gets a connection to the database.
     * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
     */
    public void openConnection()
    {
        try
        {
            // with DriverManager
            if (conn == null || conn.isClosed())
                conn = DriverManager.getConnection(JDBC_URL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the underlying connection to the in-memory SQLite instance.
     */
    public void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates the sample schema for the database.
     */
    public void createSchema()
    {
        try
        {
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS teacher(id int, name varchar(200), email varchar(200), dateOfBirth varchar(200), rank varchar(20));");
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    public void initTables(ArrayList<Teacher> list)
    {
        ArrayList<String> teacher = new ArrayList<>();
        for (Teacher t: list)
        {
            String line = String.valueOf(t.getId()) + "|" + t.getName() + "|" + t.getEmail() + "|" + t.getDateOfBirth() + "|" + t.getRank();
            teacher.add(line);
        }
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO teacher VALUES (?, ?, ?, ?, ?)");
            for (String s : teacher) {
                String[] tokens = s.split("[|]");
                statement.setInt(1, Integer.parseInt(tokens[0]));
                statement.setString(2, tokens[1]);
                statement.setString(3, tokens[2]);
                statement.setString(4, tokens[3]);
                statement.setString(5, tokens[4]);
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO teacher VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, teacher.getId());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getDateOfBirth());
            statement.setString(5, teacher.getRank());
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTeacherByName(String name)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM teacher WHERE name=?");
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Teacher> getAll()
    {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try
        {
            PreparedStatement statement = conn.prepareStatement("SELECT * from teacher");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                Teacher t = new Teacher(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("dateOFBirth"), rs.getString("rank"));
                teachers.add(t);
            }
            rs.close();
            statement.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return teachers;
    }

    public void clearTable()
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM teacher");
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
