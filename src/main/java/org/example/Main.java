package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.*;


public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost/PersonDB";
        String username = "postgres";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (connection != null) {
                System.out.println("Connected to the database.");

                List<Person> personsList = HumanUtil.loadPeople();

               // insertPeople(connection, personsList); insert
               // readPersons(connection);// Read persons
              //  deleteRows(connection, 45, 50);
              //  updateNamesForAge(connection, 50);


            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPeople(Connection connection, List<Person> personList) throws SQLException {
        String insertQuery = "INSERT INTO persons (name, surname, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (Person person : personList) {
                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(2, person.getSurname());
                preparedStatement.setInt(3, person.getAge());

                preparedStatement.executeUpdate();
            }
        }
    }
    public static void readPersons(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM persons ORDER BY age ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Show persons table:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname + ", Age: " + age);
            }
        }
    }

    public static void deleteRows(Connection connection, int minAge, int maxAge) throws SQLException {
        String deleteQuery = "DELETE FROM persons WHERE age > ? AND age < ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, minAge);
            preparedStatement.setInt(2, maxAge);
            preparedStatement.executeUpdate();

            System.out.println("Deleted rows with ages between " + minAge + " and " + maxAge);
        }
    }

    public static void updateNamesForAge(Connection connection, int ageThreshold) throws SQLException {
        String updateQuery = "UPDATE persons SET name = CONCAT(name, ' " + ageThreshold + "') WHERE age >= ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, ageThreshold);
            preparedStatement.executeUpdate();

            System.out.println("Updated names for individuals aged " + ageThreshold + " and above");
        }
    }

}
