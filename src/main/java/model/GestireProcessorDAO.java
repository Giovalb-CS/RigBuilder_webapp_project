package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestireProcessorDAO {
    public GestireProcessor doRetrieveByIdAdminIdProcessor(int idAdmin, int idProcessor) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gestireprocessor where idAdmin=? and idProcessor=?");
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idProcessor);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GestireProcessor gestireProcessor = new GestireProcessor();
                gestireProcessor.setIdProcessor(resultSet.getInt("idProcessor"));
                gestireProcessor.setIdAdmin(resultSet.getInt("idAdmin"));
                return gestireProcessor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GestireProcessor> doRetrieveAllGestireProcessor() {
        try {
            List<GestireProcessor> gestireCaseboxList = new ArrayList<GestireProcessor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GestireProcessor gestireProcessor = new GestireProcessor();
                gestireProcessor.setIdProcessor(resultSet.getInt("idProcessor"));
                gestireProcessor.setIdAdmin(resultSet.getInt("idAdmin"));
                gestireCaseboxList.add(gestireProcessor);
            }
            return gestireCaseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(GestireProcessor gestireProcessor) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gestireprocessor (idAdmin, idProcessor) values (?,?)");
            preparedStatement.setInt(1, gestireProcessor.getIdAdmin());
            preparedStatement.setInt(2, gestireProcessor.getIdProcessor());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
