package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireGPUDAO {
    public GestireGPU doRetrieveByIdAdminIdGPU(int idAdmin, int idGPU) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiregpu where idAdmin=? and idGPU=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idGPU);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireGPU gestireGPU = new GestireGPU();
                gestireGPU.setIdGPU(resultSet.getInt("idGPU"));
                gestireGPU.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireGPU;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireGPU> doRetrieveAllGestireGPU() {
        try {
            List<GestireGPU> gestireGPUList = new ArrayList<GestireGPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiregpu");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireGPU gestireGPU = new GestireGPU();
                gestireGPU.setIdGPU(resultSet.getInt("idGPU"));
                gestireGPU.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireGPUList.add(gestireGPU);
            }
            return gestireGPUList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(GestireGPU gestireGPU) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gestiregpu (idAdmin, idGPU) values (?,?)");
            preparedStatement.setInt(1, gestireGPU.getIdAdmin());
            preparedStatement.setInt(2, gestireGPU.getIdGPU());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
