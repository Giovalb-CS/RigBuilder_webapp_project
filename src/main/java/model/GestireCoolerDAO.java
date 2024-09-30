package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireCoolerDAO {
    public GestireCooler doRetrieveByIdAdminIdCooler(int idAdmin, int idCooler) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirecooler where idAdmin=? and idCooler=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idCooler);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireCooler gestireCooler = new GestireCooler();
                gestireCooler.setIdCooler(resultSet.getInt("idCooler"));
                gestireCooler.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireCooler;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireCooler> doRetrieveAllGestireCooler() {
        try {
            List<GestireCooler> gestireCoolerList = new ArrayList<GestireCooler>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirecooler");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireCooler gestireCooler = new GestireCooler();
                gestireCooler.setIdCooler(resultSet.getInt("idCooler"));
                gestireCooler.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireCoolerList.add(gestireCooler);
            }
            return gestireCoolerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
