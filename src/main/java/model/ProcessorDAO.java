package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessorDAO {
    public Processor doRetrieveByID(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                return processor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Processor doRetrieveByName(String name) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                return processor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Processor> doRetrieveAll() {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveAllByRatingDesc() {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor order by rating desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveAllByRatingAsc() {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor order by rating");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveByRating(int rating) {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where rating >= ? and rating < ? order by rating");
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating+1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveAllByPriceDesc() {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor order by price desc");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveAllByPriceAsc() {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor order by price");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveAllByPriceBetween(double start, double end) {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where price between ? and ?");
            preparedStatement.setDouble(1, start);
            preparedStatement.setDouble(2, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveBySocket(String socketType) {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where socket=?");
            preparedStatement.setString(1, socketType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveByRAMType(String ramType) {
        try {
            List<Processor> processorList = new ArrayList<Processor>();
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from processor where ram_type=?");
            preparedStatement.setString(1, ramType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }
            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Processor> doRetrieveFiltered(Double minPrice, Double maxPrice, String socket, String ramType, Integer minRating, Integer maxRating) {
        try {
            List<Processor> processorList = new ArrayList<>();
            Connection connection = ConPool.getConnection();

            StringBuilder query = new StringBuilder("SELECT * FROM processor WHERE 1=1");

            // Aggiunta delle condizioni in base ai parametri passati
            if (minPrice != null) {
                query.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                query.append(" AND price <= ?");
            }
            if (socket != null) {
                query.append(" AND socket = ?");
            }
            if (ramType != null) {
                query.append(" AND ram_type = ?");
            }
            if (minRating != null) {
                query.append(" AND rating >= ?");
            }
            if (maxRating != null) {
                query.append(" AND rating <= ?");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            // Impostazione dei valori dei parametri nella query
            int paramIndex = 1;
            if (minPrice != null) {
                preparedStatement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                preparedStatement.setDouble(paramIndex++, maxPrice);
            }
            if (socket != null) {
                preparedStatement.setString(paramIndex++, socket);
            }
            if (ramType != null) {
                preparedStatement.setString(paramIndex++, ramType);
            }
            if (minRating != null) {
                preparedStatement.setInt(paramIndex++, minRating);
            }
            if (maxRating != null) {
                preparedStatement.setInt(paramIndex++, maxRating);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterazione sui risultati e creazione della lista di processori
            while (resultSet.next()) {
                Processor processor = new Processor();
                processor.setId(resultSet.getInt("id"));
                processor.setName(resultSet.getString("name"));
                processor.setRating(resultSet.getInt("rating"));
                processor.setPrice(resultSet.getDouble("price"));
                processor.setShop_URL(resultSet.getString("shop_URL"));
                processor.setImage_URL(resultSet.getString("image_URL"));
                processor.setTdp(resultSet.getInt("tdp"));
                processor.setSocket(resultSet.getString("socket"));
                processor.setRam_type(resultSet.getString("ram_type"));
                processor.setCore(resultSet.getInt("core"));
                processor.setThread(resultSet.getInt("thread"));
                processor.setClock_base(resultSet.getDouble("clock_base"));
                processor.setClock_boost(resultSet.getDouble("clock_boost"));
                processor.setCache(resultSet.getInt("cache"));
                processor.setScale(resultSet.getInt("scale"));
                processor.setGeneration(resultSet.getString("generation"));
                processorList.add(processor);
            }

            return processorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Processor processor) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into processor (name, rating, price, shop_url, image_url, tdp, socket, ram_type, core, thread, clock_base, clock_boost, cache, scale, generation) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, processor.getName());
            preparedStatement.setDouble(2, processor.getRating());
            preparedStatement.setDouble(3, processor.getPrice());
            preparedStatement.setString(4, processor.getShop_URL());
            preparedStatement.setString(5, processor.getImage_URL());
            preparedStatement.setInt(6, processor.getTdp());
            preparedStatement.setString(7, processor.getSocket());
            preparedStatement.setString(8, processor.getRam_type());
            preparedStatement.setInt(9, processor.getCore());
            preparedStatement.setInt(10, processor.getThread());
            preparedStatement.setDouble(11, processor.getClock_base());
            preparedStatement.setDouble(12, processor.getClock_boost());
            preparedStatement.setInt(13, processor.getCache());
            preparedStatement.setInt(14, processor.getScale());
            preparedStatement.setString(15, processor.getGeneration());

            if(preparedStatement.executeUpdate()!= 1) throw  new RuntimeException("INSERT error");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            processor.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doModify(Processor processor) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE processor SET name=?, rating=?, price=?, shop_URL=?, image_URL=?, tdp=?, socket=?, ram_type=?, core=?, thread=?, clock_base=?, clock_boost=?, cache=?, scale=?, generation=? WHERE id=?"
            );
            preparedStatement.setString(1, processor.getName());
            preparedStatement.setDouble(2, processor.getRating());
            preparedStatement.setDouble(3, processor.getPrice());
            preparedStatement.setString(4, processor.getShop_URL());
            preparedStatement.setString(5, processor.getImage_URL());
            preparedStatement.setInt(6, processor.getTdp());
            preparedStatement.setString(7, processor.getSocket());
            preparedStatement.setString(8, processor.getRam_type());
            preparedStatement.setInt(9, processor.getCore());
            preparedStatement.setInt(10, processor.getThread());
            preparedStatement.setDouble(11, processor.getClock_base());
            preparedStatement.setDouble(12, processor.getClock_boost());
            preparedStatement.setInt(13, processor.getCache());
            preparedStatement.setInt(14, processor.getScale());
            preparedStatement.setString(15, processor.getGeneration());
            preparedStatement.setInt(16, processor.getId());

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM processor WHERE id=?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() != 1) throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
