package org.example.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.model.Animal;
import java.util.List;

public class AnimalDAO {
    private static final Logger logger = Logger.getLogger(AnimalDAO.class.getName());

    public void addAnimal(Animal animal) {
        String query = "INSERT INTO animal (name, nickname, age, species, bio_class, bio_order, enclosure_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getNickname());
            stmt.setInt(3, animal.getAge());
            stmt.setString(4, animal.getSpecies());
            stmt.setString(5, animal.getBioClass());
            stmt.setString(6, animal.getBioOrder());
            stmt.setString(7, animal.getEnclosureId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while adding animal to DB 🦐", e);
        }
    }

    public Animal findById(int id) {
        String query = "SELECT * FROM animal WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int age = rs.getInt("age");
                    String name = rs.getString("name");
                    String nickname = rs.getString("nickname");
                    String species = rs.getString("species");
                    String bioClass = rs.getString("bio_class");
                    String bioOrder = rs.getString("bio_order");
                    String enclosureId = rs.getString("enclosure_id");

                    Animal animal = new Animal(age, name, nickname, species, bioClass, bioOrder, enclosureId);
                    animal.setId(id);
                    return animal;
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while finding animal at DB 🦐", e);
        }

        return null;
    }

    public boolean deleteAnimal(int id) {
        String query = "DELETE FROM animal WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while deleting animal from DB 🦐", e);
        }

        return false;
    }

    public boolean updateAnimal(int id, String name, String nickname, int age, String species, String bioClass, String bioOrder, String enclosureId) {
        String query = "UPDATE animal SET name = ?, nickname = ?, age = ?, species = ?, bio_class = ?, bio_order = ?, enclosure_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, nickname);
            stmt.setInt(3, age);
            stmt.setString(4, species);
            stmt.setString(5, bioClass);
            stmt.setString(6, bioOrder);
            stmt.setString(7, enclosureId);
            stmt.setInt(8, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while updating animal in DB 🦐", e);
        }

        return false;
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        String query = "SELECT * FROM animal";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("age"),
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("species"),
                        rs.getString("bio_class"),
                        rs.getString("bio_order"),
                        rs.getString("enclosure_id")
                );
                animal.setId(rs.getInt("id"));
                animals.add(animal);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while listing animals in DB 🦐", e);
        }

        return animals;
    }

}
