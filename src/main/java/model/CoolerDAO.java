package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoolerDAO {
    public Cooler doRetrieveByID(int id) {
        Cooler cooler = null;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cooler;
    }

    public Cooler doRetrieveByName(String name) {
        Cooler cooler = null;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cooler;
    }

    public List<Cooler> doRetrieveAll() {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByRatingDesc() {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler ORDER BY rating DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByRatingAsc() {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler ORDER BY rating ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveByRating(int rating) {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByPriceDesc() {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler ORDER BY price Desc");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByPriceAsc() {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler ORDER BY price ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByPriceBetween(double start, double end) {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE price BETWEEN ? AND ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllBySocket(String socketType) {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE socket LIKE ?");
            preparedStatement.setString(1, "%" + socketType + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByRadiatorSize(int minRadiatorSize) {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE radiator_size >= ?");
            preparedStatement.setInt(1, minRadiatorSize);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveAllByCoolerHeight(int minCoolerHeight) {
        List<Cooler> coolers = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cooler WHERE cooler_height >= ?");
            preparedStatement.setInt(1, minCoolerHeight);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setTdp(resultSet.getInt("tdp"));
                cooler.setSocket(resultSet.getString("socket"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                coolers.add(cooler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolers;
    }

    public List<Cooler> doRetrieveFiltered(Integer minRating, Integer maxRating, Double minPrice, Double maxPrice, Integer minCoolerHeight, Integer minRadiatorSize, String socket) {
        List<Cooler> coolerList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            StringBuilder query = new StringBuilder("SELECT * FROM Cooler WHERE 1=1");

            // filtri dinamici
            if (minRating != null) {
                query.append(" AND rating >= ?");
            }
            if (maxRating != null) {
                query.append(" AND rating <= ?");
            }
            if (minPrice != null) {
                query.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                query.append(" AND price <= ?");
            }
            if (minCoolerHeight != null) {
                query.append(" AND cooler_height >= ?");
            }
            if (minRadiatorSize != null) {
                query.append(" AND radiator_size >= ?");
            }
            if (socket != null && !socket.isEmpty()) {
                query.append(" AND socket LIKE ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            // impostazione parametri query
            int paramIndex = 1;
            if (minRating != null) {
                preparedStatement.setInt(paramIndex++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setInt(paramIndex++, maxRating);
            }
            if (minPrice != null) {
                preparedStatement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(paramIndex++, maxPrice);
            }
            if (minCoolerHeight != null) {
                preparedStatement.setInt(paramIndex++, minCoolerHeight);
            }
            if (minRadiatorSize != null) {
                preparedStatement.setInt(paramIndex++, minRadiatorSize);
            }
            if (socket != null && !socket.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + socket + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cooler cooler = new Cooler();
                cooler.setId(resultSet.getInt("id"));
                cooler.setName(resultSet.getString("name"));
                cooler.setRating(resultSet.getDouble("rating"));
                cooler.setPrice(resultSet.getDouble("price"));
                cooler.setShop_URL(resultSet.getString("shop_url"));
                cooler.setImage_URL(resultSet.getString("image_url"));
                cooler.setRpm(resultSet.getInt("rpm"));
                cooler.setNoise_level(resultSet.getInt("noise_level"));
                cooler.setRadiator_size(resultSet.getInt("radiator_size"));
                cooler.setCooler_height(resultSet.getInt("cooler_height"));
                cooler.setSocket(resultSet.getString("socket"));
                coolerList.add(cooler);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coolerList;
    }

    public void doSave(Cooler cooler) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Cooler (name, rating, price, shop_url, image_url, tdp, socket, rpm, noise_level, radiator_size, cooler_height) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cooler.getName());
            preparedStatement.setDouble(2, cooler.getRating());
            preparedStatement.setDouble(3, cooler.getPrice());
            preparedStatement.setString(4, cooler.getShop_URL());
            preparedStatement.setString(5, cooler.getImage_URL());
            preparedStatement.setInt(6, cooler.getTdp());
            preparedStatement.setString(7, cooler.getSocket());
            preparedStatement.setInt(8, cooler.getRpm());
            preparedStatement.setInt(9, cooler.getNoise_level());
            preparedStatement.setInt(10, cooler.getRadiator_size());
            preparedStatement.setInt(11, cooler.getCooler_height());
            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("INSERT error.");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            cooler.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(Cooler cooler) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cooler SET name=?, rating=?, price=?, shop_url=?, image_url=?, tdp=?, socket=?, rpm=?, noise_level=?, radiator_size=?, cooler_height=? WHERE id=?");
            preparedStatement.setString(1, cooler.getName());
            preparedStatement.setDouble(2, cooler.getRating());
            preparedStatement.setDouble(3, cooler.getPrice());
            preparedStatement.setString(4, cooler.getShop_URL());
            preparedStatement.setString(5, cooler.getImage_URL());
            preparedStatement.setInt(6, cooler.getTdp());
            preparedStatement.setString(7, cooler.getSocket());
            preparedStatement.setInt(8, cooler.getRpm());
            preparedStatement.setInt(9, cooler.getNoise_level());
            preparedStatement.setInt(10, cooler.getRadiator_size());
            preparedStatement.setInt(11, cooler.getCooler_height());
            preparedStatement.setInt(12, cooler.getId());
            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Cooler WHERE id=?");
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
