package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GPUDAO {
    public GPU doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                return gpu;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GPU> doRetrieveByName(String name) {
        try {
            List<GPU> gpuList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            String[] keywords = name.split("\\s+");

            StringBuilder sql = new StringBuilder("SELECT DISTINCT * FROM gpu WHERE ");
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
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAll() {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByRatingDesc() {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByRatingAsc() {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveByRating(int rating) {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByPriceDesc() {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByPriceAsc() {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByMemory(String memoryType) {
        try {
            List<GPU> gpuList = new ArrayList<GPU>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from gpu where memory=?");
            preparedStatement.setString(1, memoryType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GPU> doRetrieveAllByLenght(int minLenght) {
        List<GPU> gpuList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gpu WHERE gpu.lenght >= ?");
            preparedStatement.setInt(1, minLenght);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gpuList;
    }

    public List<GPU> doRetrieveAllBySlotWidth(int minSlotWidth) {
        List<GPU> gpuList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gpu WHERE gpu.slot_width >= ? order by slot_width asc");
            preparedStatement.setInt(1, minSlotWidth);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gpuList;
    }

    public List<GPU> doRetrieveFiltered(Double minPrice, Double maxPrice, String memory, Integer maxLenght, Integer maxSlotWidth, Double minRating, Double maxRating) {
        try {
            List<GPU> gpuList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            StringBuilder query = new StringBuilder("SELECT * FROM gpu WHERE 1=1");

            if (minPrice != null) {
                query.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                query.append(" AND price <= ?");
            }
            if (memory != null && !memory.isEmpty()) {
                query.append(" AND memory = ?");
            }
            if (maxLenght != null) {
                query.append(" AND lenght <= ?");
            }
            if (maxSlotWidth != null) {
                query.append(" AND slot_width <= ?");
            }
            if (minRating != null) {
                query.append(" AND rating >= ?");
            }
            if (maxRating != null) {
                query.append(" AND rating <= ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            int paramIndex = 1;
            if (minPrice != null) {
                preparedStatement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(paramIndex++, maxPrice);
            }
            if (memory != null && !memory.isEmpty()) {
                preparedStatement.setString(paramIndex++, memory);
            }
            if (maxLenght != null) {
                preparedStatement.setInt(paramIndex++, maxLenght);
            }
            if (maxSlotWidth != null) {
                preparedStatement.setInt(paramIndex++, maxSlotWidth);
            }
            if (minRating != null) {
                preparedStatement.setDouble(paramIndex++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setDouble(paramIndex++, maxRating);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GPU gpu = new GPU();
                gpu.setId(resultSet.getInt("id"));
                gpu.setName(resultSet.getString("name"));
                gpu.setRating(resultSet.getDouble("rating"));
                gpu.setPrice(resultSet.getDouble("price"));
                gpu.setShop_URL(resultSet.getString("shop_URL"));
                gpu.setImage_URL(resultSet.getString("image_URL"));
                gpu.setTdp(resultSet.getInt("tdp"));
                gpu.setMemory(resultSet.getString("memory"));
                gpu.setMemory_clock(resultSet.getInt("memory_clock"));
                gpu.setCore_clock(resultSet.getInt("core_clock"));
                gpu.setBoost_clock(resultSet.getInt("boost_clock"));
                gpu.setLenght(resultSet.getInt("lenght"));
                gpu.setSlot_width(resultSet.getInt("slot_width"));
                gpu.setPower_cable(resultSet.getString("power_cable"));
                gpuList.add(gpu);
            }

            return gpuList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(GPU gpu) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into gpu (name, rating, price, shop_url, image_url, tdp, memory, memory_clock, core_clock, boost_clock, lenght, slot_width, power_cable) values (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, gpu.getName());
            preparedStatement.setDouble(2, gpu.getRating());
            preparedStatement.setDouble(3, gpu.getPrice());
            preparedStatement.setString(4, gpu.getShop_URL());
            preparedStatement.setString(5, gpu.getImage_URL());
            preparedStatement.setInt(6, gpu.getTdp());
            preparedStatement.setString(7, gpu.getMemory());
            preparedStatement.setInt(8, gpu.getMemory_clock());
            preparedStatement.setInt(9, gpu.getCore_clock());
            preparedStatement.setInt(10, gpu.getBoost_clock());
            preparedStatement.setInt(11, gpu.getLenght());
            preparedStatement.setInt(12, gpu.getSlot_width());
            preparedStatement.setString(13, gpu.getPower_cable());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                gpu.setId(generatedId);
                return generatedId;
            } else {
                throw new RuntimeException("Failed to obtain ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(GPU gpu) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE gpu SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, tdp=?, memory=?, memory_clock=?, core_clock=?, boost_clock=?, lenght=?, slot_width=?, power_cable=? WHERE id=?"
            );
            preparedStatement.setString(1, gpu.getName());
            preparedStatement.setDouble(2, gpu.getRating());
            preparedStatement.setDouble(3, gpu.getPrice());
            preparedStatement.setString(4, gpu.getShop_URL());
            preparedStatement.setString(5, gpu.getImage_URL());
            preparedStatement.setInt(6, gpu.getTdp());
            preparedStatement.setString(7, gpu.getMemory());
            preparedStatement.setInt(8, gpu.getMemory_clock());
            preparedStatement.setInt(9, gpu.getCore_clock());
            preparedStatement.setInt(10, gpu.getBoost_clock());
            preparedStatement.setInt(11, gpu.getLenght());
            preparedStatement.setInt(12, gpu.getSlot_width());
            preparedStatement.setString(13, gpu.getPower_cable());
            preparedStatement.setInt(14, gpu.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM gpu WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveDistinctMemoryTypes() {
        List<String> memoryTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT memory FROM gpu";
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                memoryTypes.add(resultSet.getString("memory"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memoryTypes;
    }
}