package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestireCaseboxDAO {
    public GestireCasebox doRetrieveByIdAdminIdCasebox(int idAdmin, int idCasebox) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirecasebox where idAdmin=? and idCasebox=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idCasebox);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireCasebox gestireCasebox = new GestireCasebox();
                gestireCasebox.setIdCasebox(resultSet.getInt("idCasebox"));
                gestireCasebox.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireCasebox;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireCasebox> doRetrieveAllGestireCasebox() {
        try {
            List<GestireCasebox> gestireCaseboxList = new ArrayList<GestireCasebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestirecasebox");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireCasebox gestireCasebox = new GestireCasebox();
                gestireCasebox.setIdCasebox(resultSet.getInt("idCasebox"));
                gestireCasebox.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireCaseboxList.add(gestireCasebox);
            }
            return gestireCaseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(GestireCasebox gestireCasebox) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gestirecasebox (idAdmin, idCaseBox) values (?,?)");
            preparedStatement.setInt(1, gestireCasebox.getIdAdmin());
            preparedStatement.setInt(2, gestireCasebox.getIdCasebox());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
