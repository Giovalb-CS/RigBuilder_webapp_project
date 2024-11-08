package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotherboardDAO {
    public Motherboard doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                return motherboard;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Motherboard> doRetrieveByName(String name) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();

            String[] keywords = name.split("\\s+");

            StringBuilder sql = new StringBuilder("SELECT DISTINCT * FROM motherboard WHERE ");
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
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAll() {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAllByRatingDesc() {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAllByRatingAsc() {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveByRating(int rating) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAllByPriceDesc() {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAllByPriceAsc() {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveBySocket(String socketType) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where socket = ?");
            preparedStatement.setString(1, socketType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveByChipset(String chipsetType) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where chipset = ?");
            preparedStatement.setString(1, chipsetType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveByRAMType(String ramType) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where ram_type = ?");
            preparedStatement.setString(1, ramType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveByFormFactor(String formFactor) {
        try {
            List<Motherboard> motherboardList = new ArrayList<Motherboard>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from motherboard where form_factor = ?");
            preparedStatement.setString(1, formFactor);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }
            return motherboardList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Motherboard> doRetrieveFiltered(Double minRating, Double maxRating, Double minPrice, Double maxPrice, String socket, String chipset, String ramType, String formFactor) {
        List<Motherboard> motherboardList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            StringBuilder query = new StringBuilder("SELECT * FROM motherboard WHERE 1=1");

            // Filtri dinamici
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
            if (socket != null && !socket.isEmpty()) {
                query.append(" AND socket = ?");
            }
            if (chipset != null && !chipset.isEmpty()) {
                query.append(" AND chipset = ?");
            }
            if (ramType != null && !ramType.isEmpty()) {
                query.append(" AND ram_type = ?");
            }
            if (formFactor != null && !formFactor.isEmpty()) {
                query.append(" AND form_factor = ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            // Impostazione parametri query
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
            if (socket != null && !socket.isEmpty()) {
                preparedStatement.setString(paramIndex++, socket);
            }
            if (chipset != null && !chipset.isEmpty()) {
                preparedStatement.setString(paramIndex++, chipset);
            }
            if (ramType != null && !ramType.isEmpty()) {
                preparedStatement.setString(paramIndex++, ramType);
            }
            if (formFactor != null && !formFactor.isEmpty()) {
                preparedStatement.setString(paramIndex++, formFactor);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setId(resultSet.getInt("id"));
                motherboard.setName(resultSet.getString("name"));
                motherboard.setRating(resultSet.getDouble("rating"));
                motherboard.setPrice(resultSet.getDouble("price"));
                motherboard.setShop_URL(resultSet.getString("shop_URL"));
                motherboard.setImage_URL(resultSet.getString("image_URL"));
                motherboard.setTdp(resultSet.getInt("tdp"));
                motherboard.setSocket(resultSet.getString("socket"));
                motherboard.setChipset(resultSet.getString("chipset"));
                motherboard.setRam_type(resultSet.getString("ram_type"));
                motherboard.setRam_max_speed(resultSet.getInt("ram_max_speed"));
                motherboard.setRam_slot(resultSet.getInt("ram_slot"));
                motherboard.setRam_max(resultSet.getInt("ram_max"));
                motherboard.setPcie_x16_slot(resultSet.getInt("pcie_x16_slot"));
                motherboard.setPcie_x1_slot(resultSet.getInt("pcie_x1_slot"));
                motherboard.setM2_slot(resultSet.getInt("m2_slot"));
                motherboard.setSata_slot(resultSet.getInt("sata_slot"));
                motherboard.setLan(resultSet.getString("lan"));
                motherboard.setWifi(resultSet.getString("wifi"));
                motherboard.setForm_factor(resultSet.getString("form_factor"));
                motherboardList.add(motherboard);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return motherboardList;
    }

    public int doSave(Motherboard motherboard) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into motherboard (name, rating, price, shop_url, image_url, tdp, socket, chipset, ram_type, ram_max_speed, ram_slot, ram_max, pcie_x16_slot, pcie_x1_slot, m2_slot, sata_slot, lan, wifi, form_factor) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, motherboard.getName());
            preparedStatement.setDouble(2, motherboard.getRating());
            preparedStatement.setDouble(3, motherboard.getPrice());
            preparedStatement.setString(4, motherboard.getShop_URL());
            preparedStatement.setString(5, motherboard.getImage_URL());
            preparedStatement.setInt(6, motherboard.getTdp());
            preparedStatement.setString(7, motherboard.getSocket());
            preparedStatement.setString(8, motherboard.getChipset());
            preparedStatement.setString(9, motherboard.getRam_type());
            preparedStatement.setInt(10, motherboard.getRam_max_speed());
            preparedStatement.setInt(11, motherboard.getRam_slot());
            preparedStatement.setInt(12, motherboard.getRam_max());
            preparedStatement.setInt(13, motherboard.getPcie_x16_slot());
            preparedStatement.setInt(14, motherboard.getPcie_x1_slot());
            preparedStatement.setInt(15, motherboard.getM2_slot());
            preparedStatement.setInt(16, motherboard.getSata_slot());
            preparedStatement.setString(17, motherboard.getLan());
            preparedStatement.setString(18, motherboard.getWifi());
            preparedStatement.setString(19, motherboard.getForm_factor());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                motherboard.setId(generatedId);
                return generatedId;
            } else {
                throw new RuntimeException("Failed to obtain ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(Motherboard motherboard) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE motherboard SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, tdp=?, socket=?, chipset=?, ram_type=?, ram_max_speed=?, ram_slot=?, ram_max=?, pcie_x16_slot=?, pcie_x1_slot=?, m2_slot=?, sata_slot=?, lan=?, wifi=?, form_factor=? WHERE id=?"
            );
            preparedStatement.setString(1, motherboard.getName());
            preparedStatement.setDouble(2, motherboard.getRating());
            preparedStatement.setDouble(3, motherboard.getPrice());
            preparedStatement.setString(4, motherboard.getShop_URL());
            preparedStatement.setString(5, motherboard.getImage_URL());
            preparedStatement.setInt(6, motherboard.getTdp());
            preparedStatement.setString(7, motherboard.getSocket());
            preparedStatement.setString(8, motherboard.getChipset());
            preparedStatement.setString(9, motherboard.getRam_type());
            preparedStatement.setInt(10, motherboard.getRam_max_speed());
            preparedStatement.setInt(11, motherboard.getRam_slot());
            preparedStatement.setInt(12, motherboard.getRam_max());
            preparedStatement.setInt(13, motherboard.getPcie_x16_slot());
            preparedStatement.setInt(14, motherboard.getPcie_x1_slot());
            preparedStatement.setInt(15, motherboard.getM2_slot());
            preparedStatement.setInt(16, motherboard.getSata_slot());
            preparedStatement.setString(17, motherboard.getLan());
            preparedStatement.setString(18, motherboard.getWifi());
            preparedStatement.setString(19, motherboard.getForm_factor());
            preparedStatement.setInt(20, motherboard.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM motherboard WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveDistinctSockets() {
        List<String> sockets = new ArrayList<>();
        String sql = "SELECT DISTINCT socket FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sockets.add(rs.getString("socket"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sockets;
    }

    public List<String> doRetrieveDistinctChipsets() {
        List<String> chipsets = new ArrayList<>();
        String sql = "SELECT DISTINCT chipset FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chipsets.add(rs.getString("chipset"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chipsets;
    }

    public List<String> doRetrieveDistinctRamTypes() {
        List<String> ramTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT ram_type FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ramTypes.add(rs.getString("ram_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramTypes;
    }

    public List<String> doRetrieveDistinctFormFactors() {
        List<String> formFactors = new ArrayList<>();
        String sql = "SELECT DISTINCT form_factor FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                formFactors.add(rs.getString("form_factor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formFactors;
    }

    public List<String> doRetrieveDistinctLanTypes() {
        List<String> lanTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT lan FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lanTypes.add(rs.getString("lan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lanTypes;
    }

    public List<String> doRetrieveDistinctWifiTypes() {
        List<String> wifis = new ArrayList<>();
        String sql = "SELECT DISTINCT wifi FROM motherboard";
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wifis.add(rs.getString("wifi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wifis;
    }
}
