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
                ram.setRating(resultSet.getInt("rating"));
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ram where name like ?");
            preparedStatement.setString(1, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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
                ram.setRating(resultSet.getInt("rating"));
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

    public List<RAM> doRetrieveFiltered(Integer ratingMin, Integer ratingMax, Double priceMin, Double priceMax, String type, Integer clockMin, Integer clockMax) {
        try {
            List<RAM> ramList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            // Costruzione dinamica della query SQL
            StringBuilder sql = new StringBuilder("SELECT * FROM ram WHERE 1=1");

            // Lista per memorizzare i valori dei parametri della query
            List<Object> parameters = new ArrayList<>();

            // Aggiunta del filtro per il rating
            if (ratingMin != null) {
                sql.append(" AND rating >= ?");
                parameters.add(ratingMin);
            }
            if (ratingMax != null) {
                sql.append(" AND rating <= ?");
                parameters.add(ratingMax);
            }

            // Aggiunta del filtro per il prezzo
            if (priceMin != null) {
                sql.append(" AND price >= ?");
                parameters.add(priceMin);
            }
            if (priceMax != null) {
                sql.append(" AND price <= ?");
                parameters.add(priceMax);
            }

            // Aggiunta del filtro per il tipo di RAM
            if (type != null && !type.isEmpty()) {
                sql.append(" AND type = ?");
                parameters.add(type);
            }

            // Aggiunta del filtro per la frequenza di clock
            if (clockMin != null) {
                sql.append(" AND clock >= ?");
                parameters.add(clockMin);
            }
            if (clockMax != null) {
                sql.append(" AND clock <= ?");
                parameters.add(clockMax);
            }

            // Creazione del PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            // Assegnazione dei parametri al PreparedStatement
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Esecuzione della query e popolamento della lista di RAM
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RAM ram = new RAM();
                ram.setId(resultSet.getInt("id"));
                ram.setName(resultSet.getString("name"));
                ram.setRating(resultSet.getInt("rating"));
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

    public void doSave(RAM ram) {
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
            resultSet.next();
            ram.setId(resultSet.getInt("id"));
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
}
