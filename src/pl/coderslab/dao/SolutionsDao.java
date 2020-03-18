package pl.coderslab.dao;

import pl.coderslab.model.Solutions;
import pl.coderslab.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SolutionsDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solutions(created, updated , description, exercise_id, user_id) VALUES (? , ? , ? , ? , ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solutions SET updated = ?, description = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solutions";
    private static final String FIND_ALL_USERID_QUERY =
            "SELECT * FROM solutions WHERE user_id= ?";
    private static final String FIND_ALL_EXERCISEID_QUERY =
            "SELECT * FROM solutions WHERE exercise_id= ?";

    public Solutions create(Solutions solutions) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SOLUTION_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, solutions.getCreated());
            preparedStatement.setDate(2, solutions.getUpdated());
            preparedStatement.setString(3, solutions.getDescription());
            preparedStatement.setInt(4, solutions.getExcerciseId());
            preparedStatement.setInt(5, solutions.getUsersId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                solutions.setId(resultSet.getInt(1));
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solutions read(int solutionId) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_SOLUTION_QUERY);
            preparedStatement.setInt(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Solutions solutions = new Solutions();
                solutions.setId(resultSet.getInt("id"));
                solutions.setCreated(resultSet.getDate("created"));
                solutions.setUpdated(resultSet.getDate("updated"));
                solutions.setDescription(resultSet.getString("description"));
                solutions.setExcerciseId(resultSet.getInt("exercise_id"));
                solutions.setUsersId(resultSet.getInt("user_id"));
                return solutions;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Solutions[] findAll() {
        try (Connection conn = DBUtils.getConnection()) {
            Solutions[] solutionsGroup = new Solutions[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solutions = new Solutions();
                solutions.setId(resultSet.getInt("id"));
                solutions.setCreated(resultSet.getDate("created"));
                solutions.setUpdated(resultSet.getDate("updated"));
                solutions.setDescription(resultSet.getString("description"));
                solutions.setExcerciseId(resultSet.getInt("exercise_id"));
                solutions.setUsersId(resultSet.getInt("user_id"));
                solutionsGroup = addToArray(solutions, solutionsGroup);
            }
            return solutionsGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Solutions[] findAllByUserId(int userId) {
        try (Connection conn = DBUtils.getConnection()) {
            Solutions[] solutions = new Solutions[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERID_QUERY);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solution = new Solutions();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExcerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Solutions[] findAllByExcerciseId(int exerciseId) {
        try (Connection conn = DBUtils.getConnection()) {
            Solutions[] solutions = new Solutions[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISEID_QUERY);
            statement.setInt(1,exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solution = new Solutions();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExcerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update(Solutions solutions) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setDate(1, solutions.getUpdated());
            statement.setString(2, solutions.getDescription());
            statement.setInt(3, solutions.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solutions[] addToArray(Solutions solution, Solutions[] solutions) {
        solutions = Arrays.copyOf(solutions, solutions.length + 1);
        solutions[solutions.length - 1] = solution;
        return solutions;
    }

}
