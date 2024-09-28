package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseboxDAO {
    public Casebox doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                return casebox;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Casebox doRetrieveByName(String name) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox where name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                return casebox;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Casebox> doRetrieveAll() {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByRatingDesc() {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByRatingAsc() {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveByRating(int rating) {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByPriceDesc() {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByPriceAsc() {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByCoolerHeight(int minCoolerHeight) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM casebox WHERE max_cooler_height >= ?");
            preparedStatement.setInt(1, minCoolerHeight);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public List<Casebox> doRetrieveAllByRadiatorSize(int minRadiatorSize) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM casebox WHERE radiator_size >= ?");
            preparedStatement.setInt(1, minRadiatorSize);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public List<Casebox> doRetrieveAllByGPULenght(int minGPULength) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM casebox WHERE gpu_lenght >= ?");
            preparedStatement.setInt(1, minGPULength);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public List<Casebox> doRetrieveAllByFormFactor(String formFactor) {
        try {
            List<Casebox> caseboxList = new ArrayList<Casebox>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from casebox where form_factor = ?");
            preparedStatement.setString(1, formFactor);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
            return caseboxList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casebox> doRetrieveAllByPSULenght(int minPSULength) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM casebox WHERE psu_lenght >= ?");
            preparedStatement.setInt(1, minPSULength);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public List<Casebox> doRetrieveAllByPCIeSlots(int minPCIeSlots) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM casebox WHERE pcie_slots >= ?");
            preparedStatement.setInt(1, minPCIeSlots);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public List<Casebox> doRetrieveFiltered(Integer minRating, Integer maxRating, Double minPrice, Double maxPrice, Integer minCoolerHeight, Integer minRadiatorSize, Integer minGPULength, String formFactor, Integer minPSULength, Integer minPCIeSlots) {
        List<Casebox> caseboxList = new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            StringBuilder query = new StringBuilder("SELECT * FROM casebox WHERE 1=1");

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
                query.append(" AND max_cooler_height >= ?");
            }
            if (minRadiatorSize != null) {
                query.append(" AND radiator_size >= ?");
            }
            if (minGPULength != null) {
                query.append(" AND gpu_lenght >= ?");
            }
            if (formFactor != null && !formFactor.isEmpty()) {
                query.append(" AND form_factor = ?");
            }
            if (minPSULength != null) {
                query.append(" AND psu_lenght >= ?");
            }
            if (minPCIeSlots != null) {
                query.append(" AND pcie_slots >= ?");
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
            if (minGPULength != null) {
                preparedStatement.setInt(paramIndex++, minGPULength);
            }
            if (formFactor != null && !formFactor.isEmpty()) {
                preparedStatement.setString(paramIndex++, formFactor);
            }
            if (minPSULength != null) {
                preparedStatement.setInt(paramIndex++, minPSULength);
            }
            if (minPCIeSlots != null) {
                preparedStatement.setInt(paramIndex++, minPCIeSlots);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Casebox casebox = new Casebox();
                casebox.setId(resultSet.getInt("id"));
                casebox.setName(resultSet.getString("name"));
                casebox.setRating(resultSet.getInt("rating"));
                casebox.setPrice(resultSet.getDouble("price"));
                casebox.setShop_URL(resultSet.getString("shop_URL"));
                casebox.setImage_URL(resultSet.getString("image_URL"));
                casebox.setMax_cooler_height(resultSet.getInt("max_cooler_height"));
                casebox.setRadiator_size(resultSet.getInt("radiator_size"));
                casebox.setGpu_lenght(resultSet.getInt("gpu_lenght"));
                casebox.setForm_factor(resultSet.getString("form_factor"));
                casebox.setPsu_lenght(resultSet.getInt("psu_lenght"));
                casebox.setPcie_slots(resultSet.getInt("pcie_slots"));
                caseboxList.add(casebox);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return caseboxList;
    }

    public void doSave(Casebox casebox) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into casebox (name, rating, price, shop_url, image_url, max_cooler_height, radiator_size, gpu_lenght, form_factor, psu_lenght, pcie_slots) values (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, casebox.getName());
            preparedStatement.setDouble(2, casebox.getRating());
            preparedStatement.setDouble(3, casebox.getPrice());
            preparedStatement.setString(4, casebox.getShop_URL());
            preparedStatement.setString(5, casebox.getImage_URL());
            preparedStatement.setInt(6, casebox.getMax_cooler_height());
            preparedStatement.setInt(7, casebox.getRadiator_size());
            preparedStatement.setInt(8, casebox.getGpu_lenght());
            preparedStatement.setString(9, casebox.getForm_factor());
            preparedStatement.setInt(10, casebox.getPsu_lenght());
            preparedStatement.setInt(11, casebox.getPcie_slots());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            casebox.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(Casebox casebox) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE casebox SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, max_cooler_height=?, radiator_size=?, gpu_lenght=?, form_factor=?, psu_lenght=?, pcie_slots=? WHERE id=?"
            );
            preparedStatement.setString(1, casebox.getName());
            preparedStatement.setDouble(2, casebox.getRating());
            preparedStatement.setDouble(3, casebox.getPrice());
            preparedStatement.setString(4, casebox.getShop_URL());
            preparedStatement.setString(5, casebox.getImage_URL());
            preparedStatement.setInt(6, casebox.getMax_cooler_height());
            preparedStatement.setInt(7, casebox.getRadiator_size());
            preparedStatement.setInt(8, casebox.getGpu_lenght());
            preparedStatement.setString(9, casebox.getForm_factor());
            preparedStatement.setInt(10, casebox.getPsu_lenght());
            preparedStatement.setInt(11, casebox.getPcie_slots());
            preparedStatement.setInt(12, casebox.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM casebox WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
