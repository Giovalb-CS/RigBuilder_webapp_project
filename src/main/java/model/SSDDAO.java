package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SSDDAO {
    public SSD doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                return ssd;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public SSD doRetrieveByName(String name) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                return ssd;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<SSD> doRetrieveAll() {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByRatingDesc() {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByRatingAsc() {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveByRating(int rating) {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByPriceDesc() {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByPriceAsc() {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByPCIEGen(String pcie_gen) {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where pcie_gen=?");
            preparedStatement.setString(1, pcie_gen);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveAllByCapacity(String capacity) {
        try {
            List<SSD> ssdList = new ArrayList<SSD>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ssd where capacity=?");
            preparedStatement.setString(1, capacity);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SSD> doRetrieveFiltered(String name, Double minPrice, Double maxPrice, Integer minRating, Integer maxRating, String capacity, String pcieGen) {
        try {
            List<SSD> ssdList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            StringBuilder query = new StringBuilder("SELECT * FROM ssd WHERE 1=1");

            if (name != null && !name.isEmpty()) {
                query.append(" AND name LIKE ?");
            }
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
            if (capacity != null && !capacity.isEmpty()) {
                query.append(" AND capacity = ?");
            }
            if (pcieGen != null && !pcieGen.isEmpty()) {
                query.append(" AND pcie_gen = ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            int parameterIndex = 1;

            if (name != null && !name.isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + name + "%");
            }
            if (minPrice != null) {
                preparedStatement.setDouble(parameterIndex++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(parameterIndex++, maxPrice);
            }
            if (minRating != null) {
                preparedStatement.setInt(parameterIndex++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setInt(parameterIndex++, maxRating);
            }
            if (capacity != null && !capacity.isEmpty()) {
                preparedStatement.setString(parameterIndex++, capacity);
            }
            if (pcieGen != null && !pcieGen.isEmpty()) {
                preparedStatement.setString(parameterIndex++, pcieGen);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SSD ssd = new SSD();
                ssd.setId(resultSet.getInt("id"));
                ssd.setName(resultSet.getString("name"));
                ssd.setRating(resultSet.getInt("rating"));
                ssd.setPrice(resultSet.getDouble("price"));
                ssd.setShop_URL(resultSet.getString("shop_URL"));
                ssd.setImage_URL(resultSet.getString("image_URL"));
                ssd.setTdp(resultSet.getInt("tdp"));
                ssd.setPcie_gen(resultSet.getString("pcie_gen"));
                ssd.setCapacity(resultSet.getString("capacity"));
                ssd.setSpeed_read(resultSet.getInt("speed_read"));
                ssd.setSpeed_write(resultSet.getInt("speed_write"));
                ssdList.add(ssd);
            }
            return ssdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(SSD ssd) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ssd (name, rating, price, shop_url, image_url, tdp, pcie_gen, capacity, speed_read, speed_write) values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ssd.getName());
            preparedStatement.setDouble(2, ssd.getRating());
            preparedStatement.setDouble(3, ssd.getPrice());
            preparedStatement.setString(4, ssd.getShop_URL());
            preparedStatement.setString(5, ssd.getImage_URL());
            preparedStatement.setInt(6, ssd.getTdp());
            preparedStatement.setString(7, ssd.getPcie_gen());
            preparedStatement.setString(8, ssd.getCapacity());
            preparedStatement.setInt(9, ssd.getSpeed_read());
            preparedStatement.setInt(10, ssd.getSpeed_write());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            ssd.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(SSD ssd) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ssd SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, tdp=?, pcie_gen=?, capacity=?, speed_read=?, speed_write=? WHERE id=?"
            );
            preparedStatement.setString(1, ssd.getName());
            preparedStatement.setDouble(2, ssd.getRating());
            preparedStatement.setDouble(3, ssd.getPrice());
            preparedStatement.setString(4, ssd.getShop_URL());
            preparedStatement.setString(5, ssd.getImage_URL());
            preparedStatement.setInt(6, ssd.getTdp());
            preparedStatement.setString(7, ssd.getPcie_gen());
            preparedStatement.setString(8, ssd.getCapacity());
            preparedStatement.setInt(9, ssd.getSpeed_read());
            preparedStatement.setInt(10, ssd.getSpeed_write());
            preparedStatement.setInt(11, ssd.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ssd WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
