package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSUDAO {
    public PSU doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                return psu;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<PSU> doRetrieveByName(String name) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();

            String[] keywords = name.split("\\s+");

            StringBuilder sql = new StringBuilder("SELECT DISTINCT * FROM psu WHERE ");
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
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAll() {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByRatingDesc() {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByRatingAsc() {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveByRating(int rating) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByPriceDesc() {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByPriceAsc() {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByType(String type) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where type = ?");
            preparedStatement.setString(1, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByEfficiency(String efficiency) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where efficiency = ?");
            preparedStatement.setString(1, efficiency);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByMinWattage(Integer minWattage) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where wattage >= ? order by wattage");
            preparedStatement.setInt(1, minWattage);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveAllByMaxLenght(Integer maxLenght) {
        try {
            List<PSU> psuList = new ArrayList<PSU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from psu where lenght <= ? order by lenght desc");
            preparedStatement.setInt(1, maxLenght);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }
            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PSU> doRetrieveFiltered(Double minPrice, Double maxPrice, Double minRating, Double maxRating,
                                        String type, String efficiency, Integer minWattage, Integer maxWattage, Integer minLength, Integer maxLength) {
        try {
            List<PSU> psuList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            // Base query
            StringBuilder query = new StringBuilder("SELECT * FROM psu WHERE 1=1");

            // Dynamic filters
            if (minPrice != null) {
                query.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                query.append(" AND price <= ?");
            }
            if (minRating != null) {
                query.append(" AND rating >= ?");
            }
            if (maxRating != null) {
                query.append(" AND rating <= ?");
            }
            if (type != null && !type.isEmpty()) {
                query.append(" AND type = ?");
            }
            if (efficiency != null && !efficiency.isEmpty()) {
                query.append(" AND efficiency = ?");
            }
            if (minWattage != null) {
                query.append(" AND wattage >= ?");
            }
            if (maxWattage != null) {
                query.append(" AND wattage <= ?");
            }
            if (minLength != null) {
                query.append(" AND lenght >= ?");
            }
            if (maxLength != null) {
                query.append(" AND lenght <= ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            // Bind parameters in order
            int index = 1;
            if (minPrice != null) {
                preparedStatement.setDouble(index++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(index++, maxPrice);
            }
            if (minRating != null) {
                preparedStatement.setDouble(index++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setDouble(index++, maxRating);
            }
            if (type != null && !type.isEmpty()) {
                preparedStatement.setString(index++, type);
            }
            if (efficiency != null && !efficiency.isEmpty()) {
                preparedStatement.setString(index++, efficiency);
            }
            if (minWattage != null) {
                preparedStatement.setInt(index++, minWattage);
            }
            if (maxWattage != null) {
                preparedStatement.setInt(index++, maxWattage);
            }
            if (minLength != null) {
                preparedStatement.setInt(index++, minLength);
            }
            if (maxLength != null) {
                preparedStatement.setInt(index++, maxLength);
            }

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                PSU psu = new PSU();
                psu.setId(resultSet.getInt("id"));
                psu.setName(resultSet.getString("name"));
                psu.setRating(resultSet.getDouble("rating"));
                psu.setPrice(resultSet.getDouble("price"));
                psu.setShop_URL(resultSet.getString("shop_URL"));
                psu.setImage_URL(resultSet.getString("image_URL"));
                psu.setType(resultSet.getString("type"));
                psu.setEfficiency(resultSet.getString("efficiency"));
                psu.setWattage(resultSet.getInt("wattage"));
                psu.setLenght(resultSet.getInt("lenght"));
                psuList.add(psu);
            }

            return psuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(PSU psu) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into psu (name, rating, price, shop_url, image_url, type, efficiency, wattage, lenght) values (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, psu.getName());
            preparedStatement.setDouble(2, psu.getRating());
            preparedStatement.setDouble(3, psu.getPrice());
            preparedStatement.setString(4, psu.getShop_URL());
            preparedStatement.setString(5, psu.getImage_URL());
            preparedStatement.setString(6, psu.getType());
            preparedStatement.setString(7, psu.getEfficiency());
            preparedStatement.setInt(8, psu.getWattage());
            preparedStatement.setInt(9, psu.getLenght());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                psu.setId(generatedId);
                return generatedId; // Restituisci l'ID generato
            } else {
                throw new RuntimeException("Failed to obtain ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(PSU psu) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE psu SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, type=?, efficiency=?, wattage=?, lenght=? WHERE id=?"
            );
            preparedStatement.setString(1, psu.getName());
            preparedStatement.setDouble(2, psu.getRating());
            preparedStatement.setDouble(3, psu.getPrice());
            preparedStatement.setString(4, psu.getShop_URL());
            preparedStatement.setString(5, psu.getImage_URL());
            preparedStatement.setString(6, psu.getType());
            preparedStatement.setString(7, psu.getEfficiency());
            preparedStatement.setInt(8, psu.getWattage());
            preparedStatement.setInt(9, psu.getLenght());
            preparedStatement.setInt(10, psu.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM psu WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveDistinctTypes() {
        List<String> types = new ArrayList<>();
        String sql = "SELECT DISTINCT type FROM psu";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public List<String> doRetrieveDistinctEfficiencyTypes() {
        List<String> efficiencies = new ArrayList<>();
        String sql = "SELECT DISTINCT efficiency FROM psu";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                efficiencies.add(rs.getString("efficiency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return efficiencies;
    }
}
