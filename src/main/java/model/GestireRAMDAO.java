package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireRAMDAO {
    public GestireRAM doRetrieveByIdAdminIdRAM(int idAdmin, int idRAM) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestireram where idAdmin=? and idRAM=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idRAM);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireRAM gestireRAM = new GestireRAM();
                gestireRAM.setIdRAM(resultSet.getInt("idRAM"));
                gestireRAM.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireRAM;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireRAM> doRetrieveAllGestireRAM() {
        try {
            List<GestireRAM> gestireRAMList = new ArrayList<GestireRAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestireram");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireRAM gestireRAM = new GestireRAM();
                gestireRAM.setIdRAM(resultSet.getInt("idRAM"));
                gestireRAM.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireRAMList.add(gestireRAM);
            }
            return gestireRAMList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(GestireRAM gestireRAM) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gestireram (idAdmin, idRAM) values (?,?)");
            preparedStatement.setInt(1, gestireRAM.getIdAdmin());
            preparedStatement.setInt(2, gestireRAM.getIdRAM());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
