package org.example.db;

import org.example.db.utils.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("sql/init_db.sql"));
                Connection connection = Database.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            StringBuilder currentStatement = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();

                if (line.isBlank() || line.startsWith("--")) {
                    continue;
                }
                currentStatement.append(line);

                if (line.endsWith(";")) {
                    statement.execute(currentStatement.toString());
                    currentStatement.setLength(0);
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
