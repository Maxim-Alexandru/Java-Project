package Repository;

import Domain.Discipline;
import Domain.Teacher;
import java.sql.*;
import java.util.ArrayList;

public class DisciplineDatabase
{
    public DisciplineDatabase() {}

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
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS discipline(id int, name varchar(200), numberOfCredits int);");
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    public void initTables(ArrayList<Discipline> list)
    {
        ArrayList<String> discipline = new ArrayList<>();
        for (Discipline d: list)
        {
            String line = String.valueOf(d.getId()) + "|" + d.getName() + "|" + d.getNumberOfCredits();
            discipline.add(line);
        }
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO discipline VALUES (?, ?, ?)");
            for (String s : discipline) {
                String[] tokens = s.split("[|]");
                statement.setInt(1, Integer.parseInt(tokens[0]));
                statement.setString(2, tokens[1]);
                statement.setInt(3, Integer.parseInt(tokens[2]));
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDiscipline(Discipline discipline)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO discipline VALUES (?, ?, ?)");
            statement.setInt(1, discipline.getId());
            statement.setString(2, discipline.getName());
            statement.setInt(3, discipline.getNumberOfCredits());
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDisciplineByName(String name)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM discipline WHERE name=?");
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Discipline> getAll()
    {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try
        {
            PreparedStatement statement = conn.prepareStatement("SELECT * from discipline");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                Discipline d = new Discipline(rs.getInt("id"), rs.getString("name"), rs.getInt("numberOfCredits"));
                disciplines.add(d);
            }
            rs.close();
            statement.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return disciplines;
    }

    public void clearTable()
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM discipline");
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
