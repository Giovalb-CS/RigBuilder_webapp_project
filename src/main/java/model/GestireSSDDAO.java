package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireSSDDAO {
    public GestireSSD doRetrieveByIdAdminIdSSD(int idAdmin, int idSSD) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiressd where idAdmin=? and idSSD=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idSSD);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireSSD gestireSSD = new GestireSSD();
                gestireSSD.setIdSSD(resultSet.getInt("idSSD"));
                gestireSSD.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireSSD;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireSSD> doRetrieveAllGestireSSD() {
        try {
            List<GestireSSD> gestireSSDList = new ArrayList<GestireSSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestiressd");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireSSD gestireSSD = new GestireSSD();
                gestireSSD.setIdSSD(resultSet.getInt("idSSD"));
                gestireSSD.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireSSDList.add(gestireSSD);
            }
            return gestireSSDList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
