package pl.coderslab.dao;

import pl.coderslab.model.Exercise;
import pl.coderslab.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ExercisesDao {
    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercises where id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercises SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT * FROM exercises";


    public Exercise create(Exercise excercise) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXERCISE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
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

    public Exercise read(int excerciseId) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_EXERCISE_QUERY);
            preparedStatement.setInt(1, excerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
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

    public Exercise[] findAll() {
        try (Connection conn = DBUtils.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise newExercise = new Exercise();
                newExercise.setId(resultSet.getInt("id"));
                newExercise.setTitle(resultSet.getString("title"));
                newExercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(newExercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Exercise excercise) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
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
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] addToArray(Exercise excercise, Exercise[] excercises) {
        Exercise[] tmpExcercises = Arrays.copyOf(excercises, excercises.length + 1);
        tmpExcercises[excercises.length] = excercise;
        return tmpExcercises;
    }
}
