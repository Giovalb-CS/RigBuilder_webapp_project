package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireMotherboardDAO {
    public GestireMotherboard doRetrieveByIdAdminIdMotherboard(int idAdmin, int idMotherboard) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiremobo where idAdmin=? and idMOBO=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idMotherboard);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireMotherboard gestireMotherboard = new GestireMotherboard();
                gestireMotherboard.setIdMotherboard(resultSet.getInt("idMOBO"));
                gestireMotherboard.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireMotherboard;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireMotherboard> doRetrieveAllGestireMotherboard() {
        try {
            List<GestireMotherboard> gestireMotherboardList = new ArrayList<GestireMotherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiremobo");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireMotherboard gestireMotherboard = new GestireMotherboard();
                gestireMotherboard.setIdMotherboard(resultSet.getInt("idMOBO"));
                gestireMotherboard.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireMotherboardList.add(gestireMotherboard);
            }
            return gestireMotherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(GestireMotherboard gestireMotherboard) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gestiremobo (idAdmin, idMOBO) values (?,?)");
            preparedStatement.setInt(1, gestireMotherboard.getIdAdmin());
            preparedStatement.setInt(2, gestireMotherboard.getIdMotherboard());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
