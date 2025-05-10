package org.example.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.model.Animal;
import org.example.model.Enclosure;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class EnclosureDAO {
    private static final Logger logger = Logger.getLogger(EnclosureDAO.class.getName());
    Gson gson = new Gson();

    public void addEnclosure(Enclosure enclosure) {
        String query = "INSERT INTO enclosure (name, capacity, current_occupancy, orders) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, enclosure.getName());
            stmt.setInt(2, enclosure.getCapacity());
            stmt.setInt(3, enclosure.getCurrentOccupancy());
            String jsonOrders = gson.toJson(enclosure.getOrders());
            stmt.setString(4, jsonOrders);;

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while adding enclosure to DB 🦐", e);
        }
    }

    public boolean deleteEnclosure(int id) {
        String query = "DELETE FROM enclosure WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while deleting enclosure from DB 🦐", e);
        }

        return false;
    }

    public boolean updateEnclosure(int id, int capacity, String name, List<String> orders) {
        String query = "UPDATE enclosure SET capacity = ?, name = ?, orders = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, capacity);
            stmt.setString(2, name);
            String jsonOrders = gson.toJson(orders);
            stmt.setString(3, jsonOrders);
            stmt.setInt(4, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while updating animal in DB 🦐", e);
        }

        return false;
    }

    public List<Enclosure> getEnclosures() {
        List<Enclosure> enclosures = new ArrayList<>();
        String query = "SELECT * FROM enclosure";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Enclosure enclosure = new Enclosure(
                        rs.getInt("current_occupancy"),
                        rs.getInt("capacity"),
                        rs.getString("name"),
                        Collections.singletonList(rs.getString("orders"))
                );

                enclosure.setId(rs.getInt("id"));
                enclosures.add(enclosure);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while listing animals in DB 🦐", e);
        }

        return enclosures;
    }

    public boolean existsById(int id) {
        String query = "SELECT 1 FROM enclosure WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while checking enclosure 🦐", e);
            return false;
        }
    }

    public Enclosure findById(int id) {
        String query = "SELECT * FROM enclosure WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int currentOccupancy = rs.getInt("current_occupancy");
                    int capacity = rs.getInt("capacity");
                    String name = rs.getString("name");
                    String ordersStr = rs.getString("orders");

                    List<String> orders = ordersStr != null ? Arrays.asList(ordersStr.split(",")) : Arrays.asList();

                    Enclosure enclosure = new Enclosure(currentOccupancy, capacity, name, orders);
                    enclosure.setId(id);
                    return enclosure;
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while finding enclosure in DB 🦐", e);
        }

        return null;
    }

    public boolean hasAnimal(int enclosureId) {
        String query = "SELECT current_occupancy FROM enclosure WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enclosureId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int currentOccupancy = rs.getInt("current_occupancy");

                    return currentOccupancy > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while checking if enclosure has animals 🦐", e);
        }
        return false;
    }


    public boolean isAlreadyFull(int enclosureId) {
        String query = "SELECT (capacity - current_occupancy) AS space FROM enclosure WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enclosureId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int space = rs.getInt("space");
                    return space < 1;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while checking enclosure's occupancy 🦐", e);
        }
        return true;
    }

    public boolean isAppropriate(int enclosureId, String animalOrder) {
        String query = "SELECT orders FROM enclosure WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, enclosureId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String jsonOrders = rs.getString("orders");
                    String[] ordersArray = gson.fromJson(jsonOrders, String[].class);
                    List<String> ordersList = Arrays.asList(ordersArray);

                    return ordersList.contains(animalOrder);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while checking if enclosure is appropriate 🦐", e);
        }

        return false;
    }

    public void incrementOccupancy(int enclosureId) {
        String query = "UPDATE enclosure SET current_occupancy = current_occupancy + 1 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enclosureId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while incrementing enclosure occupancy 🦐", e);
        }
    }

    public void decrementOccupancy(int enclosureId) {
        String query = "UPDATE enclosure SET current_occupancy = current_occupancy - 1 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enclosureId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while decrementing enclosure occupancy 🦐", e);
        }
    }
}
