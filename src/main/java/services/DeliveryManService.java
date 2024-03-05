package services;

import models.DeliveryMan;
import utils.MyDatabase;

import java.sql.*;
import java.util.List;

public class DeliveryManService implements IService<DeliveryMan> {

    private Connection connection;

    public DeliveryManService() {
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(DeliveryMan deliveryMan) throws SQLException {

        String sql = "insert into user (FirstName,LastName,Email,Address,Role,Number,Rating,Password) values (?,?,?,?,?,?,?,?)";
        String sql2 = "insert into deliveryperson (Vehicle,Availability,Sector,idUser) values (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, deliveryMan.getFirstName());
        preparedStatement.setString(2, deliveryMan.getLastName());
        preparedStatement.setString(3, deliveryMan.getEmail());
        preparedStatement.setString(4, deliveryMan.getAddress());
        preparedStatement.setString(5, deliveryMan.getRole());
        preparedStatement.setInt(6, deliveryMan.getNumber());
        preparedStatement.setDouble(7, deliveryMan.getRating());
        preparedStatement.setString(8,deliveryMan.getVehicle());
        preparedStatement.executeUpdate();

        /*String  sql3 = "SELECT idUser FROM `user` WHERE email= ?";
        Statement statement = connection.createStatement();
        int rs = statement.executeQuery(sql3).getInt("Id");*/

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int userId = -1;
        if (generatedKeys.next()) {
            userId = generatedKeys.getInt(1);
        }

        preparedStatement.setString(1, deliveryMan.getEmail());
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setString(1,deliveryMan.getPassword());
        preparedStatement2.setString(2, deliveryMan.getAvailability());
        preparedStatement2.setString(3, deliveryMan.getSector());
        preparedStatement2.setInt(4,userId);
        preparedStatement2.executeUpdate();
    }

    @Override
    public void modifier(DeliveryMan deliveryMan) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<DeliveryMan> recuperer() throws SQLException {
        return null;
    }
}
