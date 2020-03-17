package pl.coderslab.dao;

import pl.coderslab.model.Excercises;
import pl.coderslab.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ExcercisesDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM exercises where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE exercises SET title = ?, description = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM exercises";


    public Excercises create(Excercises excercise) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, excercise.getTitle());
            preparedStatement.setString(2, excercise.getDescription());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                excercise.setId(resultSet.getInt(1));
            }
            return excercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Excercises read(int excerciseId) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, excerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Excercises exercise = new Excercises();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Excercises[] findAll() {
        try (Connection conn = DBUtils.getConnection()) {
            Excercises[] exercises = new Excercises[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Excercises excercise = new Excercises();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(excercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update(Excercises excercise) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, excercise.getTitle());
            statement.setString(2, excercise.getDescription());
            statement.setInt(3, excercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int exerciseId) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Excercises[] addToArray(Excercises excercise,Excercises[] excercises){
        Excercises[] tmpExcercises = Arrays.copyOf(excercises, excercises.length + 1);
        tmpExcercises[excercises.length] = excercise;
        return excercises;
    }
}
