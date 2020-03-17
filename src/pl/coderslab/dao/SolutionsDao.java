package pl.coderslab.dao;

import pl.coderslab.model.Solutions;
import pl.coderslab.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.sql.Date;

public class SolutionsDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO solutions(created, updated , description, excercise_id, user_id) VALUES (? , ? , ? , ? , ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE solutions SET updated= ?, description = ?,  where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM solutions";
    private static final String FIND_ALL_USERID_QUERY =
            "SELECT * FROM solutions WHERE user_id= ?";
    private static final String FIND_ALL_EXERCISEID_QUERY =
            "SELECT * FROM solutions WHERE exercise_id= ?";

    public Solutions create(Solutions solutions) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, convToDate(solutions.getCreated()));
            preparedStatement.setDate(2, convToDate(solutions.getUpdated()));
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
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Solutions solutions = new Solutions();
                solutions.setId(resultSet.getInt("id"));
                solutions.setCreated(convertToLocalDateTimeViaInstant(resultSet.getDate("created")));
                solutions.setUpdated(convertToLocalDateTimeViaInstant(resultSet.getDate("updated")));
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
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solutions = new Solutions();
                solutions.setId(resultSet.getInt("id"));
                solutions.setCreated(convertToLocalDateTimeViaInstant(resultSet.getDate("created")));
                solutions.setUpdated(convertToLocalDateTimeViaInstant(resultSet.getDate("updated")));
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
                solution.setCreated(convertToLocalDateTimeViaInstant(resultSet.getDate("created")));
                solution.setUpdated(convertToLocalDateTimeViaInstant(resultSet.getDate("updated")));
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
                solution.setCreated(convertToLocalDateTimeViaInstant(resultSet.getDate("created")));
                solution.setUpdated(convertToLocalDateTimeViaInstant(resultSet.getDate("updated")));
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
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setDate(1, convToDate(solutions.getUpdated()));
            statement.setString(2, solutions.getDescription());
            statement.setInt(4, solutions.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Solutions[] addToArray(Solutions solution,Solutions[] solutions){
        solutions= Arrays.copyOf(solutions,solutions.length+1);
        solutions[solutions.length]=solution;
        return solutions;
    }
    public Date convToDate(LocalDateTime dateToConvert) {
        return (Date) java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
