package model;

import java.sql.*;

public class AdministratorDAO {

    private static final Connection connection;
    static {
        try {
            connection = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Administrator doRetrieveByEmailPassword(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE email = ? AND pwd = SHA1(?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setEmail(resultSet.getString("email"));
                administrator.setId(resultSet.getInt("id"));
                administrator.setPwd(resultSet.getString("pwd"));
                return administrator;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Administrator doRetrieveByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE email = ?");
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setEmail(resultSet.getString("email"));
                administrator.setId(resultSet.getInt("id"));
                administrator.setPwd(resultSet.getString("pwd"));
                return administrator;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean doSave(Administrator administrator) {
        int b;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administrator(email, pwd) VALUES(?, SHA1(?))", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, administrator.getEmail());
            preparedStatement.setString(2, administrator.getPwd());

            b = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            administrator.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b==1;
    }

    public void doModifyPassword(Administrator administrator, String newPassword) {
        try {
            if (newPassword == null || newPassword.isEmpty()) {
                newPassword = administrator.getPwd();
            }
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE administrator SET pwd = SHA1(?) WHERE id = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, administrator.getId());

            if (preparedStatement.executeUpdate() != 1) {
                throw new RuntimeException("Update failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
