package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestirePSUDAO {
    public GestirePSU doRetrieveByIdAdminIdPSU(int idAdmin, int idPSU) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirepsu where idAdmin=? and idPSU=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idPSU);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestirePSU gestirePSU = new GestirePSU();
                gestirePSU.setIdPSU(resultSet.getInt("idPSU"));
                gestirePSU.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestirePSU;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestirePSU> doRetrieveAllGestirePSU() {
        try {
            List<GestirePSU> gestirePSUList = new ArrayList<GestirePSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirepsu");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestirePSU gestirePSU = new GestirePSU();
                gestirePSU.setIdPSU(resultSet.getInt("idPSU"));
                gestirePSU.setIdAdmin(resultSet.getInt("idAdmin"));
                gestirePSUList.add(gestirePSU);
            }
            return gestirePSUList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
