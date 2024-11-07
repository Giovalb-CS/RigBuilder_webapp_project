package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RAMDAO {
    public RAM doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                return ram;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<RAM> doRetrieveByName(String name) {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();

            String[] keywords = name.split("\\s+");

            StringBuilder sql = new StringBuilder("SELECT DISTINCT * FROM ram WHERE ");
            for (int i = 0; i < keywords.length; i++) {
                sql.append("name LIKE ?");
                if (i < keywords.length - 1) {
                    sql.append(" AND ");
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < keywords.length; i++) {
                preparedStatement.setString(i + 1, "%" + keywords[i] + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAll() {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByRatingDesc() {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByRatingAsc() {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveByRating(int rating) {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByPriceDesc() {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByPriceAsc() {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByType(String type) {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where type=?");
            preparedStatement.setString(1, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveAllByMinClock(int clock) {
        try {
            List<RAM> ramList = new ArrayList<RAM>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where ram.clock >= ? order by clock");
            preparedStatement.setInt(1, clock);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }
            return ramList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RAM> doRetrieveFiltered(Double minRating, Double maxRating, Double minPrice, Double maxPrice, String type, Integer clockMin, Integer clockMax) {
        try {
            List<RAM> ramList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            // Costruzione dinamica della query SQL
            StringBuilder sql = new StringBuilder("SELECT * FROM ram WHERE 1=1");

            if (minRating != null) {
                sql.append(" AND rating >= ?");
            }
            if (maxRating != null) {
                sql.append(" AND rating <= ?");
            }
            if (minPrice != null) {
                sql.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                sql.append(" AND price <= ?");
            }
            if (type != null && !type.isEmpty()) {
                sql.append(" AND type = ?");
            }
            if (clockMin != null) {
                sql.append(" AND clock >= ?");
            }
            if (clockMax != null) {
                sql.append(" AND clock <= ?");
            }

            // Creazione del PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (minRating != null) {
                preparedStatement.setDouble(paramIndex++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setDouble(paramIndex++, maxRating);
            }
            if (minPrice != null) {
                preparedStatement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(paramIndex++, maxPrice);
            }
            if (type != null && !type.isEmpty()) {
                preparedStatement.setString(paramIndex++, type);
            }
            if (clockMin != null) {
                preparedStatement.setInt(paramIndex++, clockMin);
            }
            if (clockMax != null) {
                preparedStatement.setInt(paramIndex++, clockMax);
            }

            // Esecuzione della query e popolamento della lista di RAM
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getDouble("rating"));
                ram.setPrice(resultSet.getDouble("price"));
                ram.setShop_URL(resultSet.getString("shop_URL"));
                ram.setImage_URL(resultSet.getString("image_URL"));
                ram.setTdp(resultSet.getInt("tdp"));
                ram.setType(resultSet.getString("type"));
                ram.setClock(resultSet.getInt("clock"));
                ramList.add(ram);
            }

            return ramList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(RAM ram) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ram (name, rating, price, shop_url, image_url, tdp, type, clock) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ram.getName());
            preparedStatement.setDouble(2, ram.getRating());
            preparedStatement.setDouble(3, ram.getPrice());
            preparedStatement.setString(4, ram.getShop_URL());
            preparedStatement.setString(5, ram.getImage_URL());
            preparedStatement.setInt(6, ram.getTdp());
            preparedStatement.setString(7, ram.getType());
            preparedStatement.setInt(8, ram.getClock());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                ram.setId(generatedId);
                return generatedId;
            } else throw new RuntimeException("Failed to obtain ID.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(RAM ram) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ram SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, tdp=?, type=?, clock=? WHERE id=?"
            );
            preparedStatement.setString(1, ram.getName());
            preparedStatement.setDouble(2, ram.getRating());
            preparedStatement.setDouble(3, ram.getPrice());
            preparedStatement.setString(4, ram.getShop_URL());
            preparedStatement.setString(5, ram.getImage_URL());
            preparedStatement.setInt(6, ram.getTdp());
            preparedStatement.setString(7, ram.getType());
            preparedStatement.setInt(8, ram.getClock());
            preparedStatement.setInt(9, ram.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ram WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveDistinctRamTypes() {
        List<String> ramTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT type FROM ram";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ramTypes.add(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramTypes;
    }
}
