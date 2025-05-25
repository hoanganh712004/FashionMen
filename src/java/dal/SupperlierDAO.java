/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
public class SupperlierDAO extends DBContext {

    public Supperlier getSupperlier(int supperlierId) {

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [supperlier_id]
                           ,[company_name]
                           ,[phone_number]
                           ,[country]
                       FROM [dbo].[Supperlier]
                       WHERE supperlier_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Objet
            statement.setObject(1, supperlierId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int supperlierIdData = Integer.parseInt(resultSet.getString("supperlier_id"));
                String companyName = resultSet.getString("company_name");
                String phoneNumber = resultSet.getString("phone_number");
                String contry = resultSet.getString("country");

                Supperlier supperlier = new Supperlier(supperlierIdData, companyName, phoneNumber, contry);
                return supperlier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getTotalSupperliers() {

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT COUNT(supperlier_id) as Total_Supperlier
                       FROM Supperlier""";
        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // executeQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int Supperliers = Integer.parseInt(resultSet.getString("Total_Supperlier"));

                return Supperliers;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Supperlier> getSupperlierAll() {

        // Create supperlierAdminList
        List<Supperlier> supperlierAdminList = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [supperlier_id]
                           ,[company_name]
                           ,[phone_number]
                           ,[country]
                       FROM [dbo].[Supperlier]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int supperlierIdData = Integer.parseInt(resultSet.getString("supperlier_id"));
                String companyName = resultSet.getString("company_name");
                String phoneNumber = resultSet.getString("phone_number");
                String contry = resultSet.getString("country");

                Supperlier supperlier = new Supperlier(supperlierIdData, companyName, phoneNumber, contry);
                supperlierAdminList.add(supperlier);
            }
            
            return supperlierAdminList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        for (Supperlier supperlier : new SupperlierDAO().getSupperlierAll()) {
            System.out.println(supperlier.toString());
        }
    }

}
