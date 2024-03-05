package services;

import models.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {

    private Connection connection;

    public UserService() {
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(User user) throws SQLException {
        String sql = "insert into user (FirstName,LastName,Email,Address,Role,Number,Rating,Password) values (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getAddress());
        preparedStatement.setString(5, user.getRole());
        preparedStatement.setInt(6, user.getNumber());
        preparedStatement.setDouble(7, user.getRating());
        preparedStatement.setString(8,user.getPassword());
        preparedStatement.executeUpdate();

    }

    @Override
    public void modifier(User user) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<User> recuperer() throws SQLException {
        String sql = "SELECT * FROM `user`";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User p = new User();
            p.setId(rs.getInt("idUser"));
            p.setFirstName(rs.getString("FirstName"));
            p.setLastName(rs.getString("LastName"));
            p.setEmail(rs.getString("Email"));
            p.setAddress(rs.getString("Address"));
            p.setRole(rs.getString("Role"));
            p.setRating(rs.getDouble("Rating"));
            p.setNumber(rs.getInt("Number"));
            // p.setPassword(rs.getString("Password"));



            users.add(p);
        }
        return users;
    }
    public User recupererUtilisateur(int id) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("idUser"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setEmail(rs.getString("Email"));
            user.setAddress(rs.getString("Address"));
            user.setRole(rs.getString("Role"));
            user.setRating(rs.getDouble("Rating"));
            user.setNumber(rs.getInt("Number"));
        }
        return user;
    }

}
