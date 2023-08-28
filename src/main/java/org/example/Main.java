package org.example;

import java.sql.*;
import java.util.stream.IntStream;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/transactions";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static void main(String[] args) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int idOfTheUser = 15;
            statement.executeUpdate("delete from users;");
            statement.execute("insert into users (transaction, sender, receiver) values (1001, 'Ivan', 'Damir');");
            ResultSet resultSet = statement.executeQuery(String.format("select * from users where iduser = %d;", idOfTheUser));
            if(resultSet.next()) {
                System.out.println("Transaction: " + resultSet.getInt("transaction"));
                System.out.println("Sender username: " + resultSet.getString("sender"));
                System.out.println("Receiver username: " + resultSet.getString("receiver"));
            } else {
                System.out.printf("There isn't column with id %d", idOfTheUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}