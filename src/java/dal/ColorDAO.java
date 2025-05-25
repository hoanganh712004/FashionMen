/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Color;
import model.Order;

/**
 *
 * @author ADMIN
 */
public class ColorDAO extends DBContext {

    public Color getColors(int colorId) {

        // Create object order
        Color color = null;

        // Connect Database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [color_id]
                           ,[color]
                       FROM [dbo].[Colors]
                        WHERE color_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, colorId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int colorIdData = Integer.parseInt(resultSet.getString("color_id"));
                String colorName = resultSet.getString("color");

                color = new Color(colorIdData, colorName);
                return color;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Color> getColorsAll() {
        
        // Create color List
        List<Color> colorLists = new ArrayList<>();

        // Connect Database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [color_id]
                           ,[color]
                       FROM [dbo].[Colors]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int colorIdData = Integer.parseInt(resultSet.getString("color_id"));
                String colorName = resultSet.getString("color");
                Color color = new Color(colorIdData, colorName);
                colorLists.add(color);
            }
            return colorLists;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        for (Color color : new ColorDAO().getColorsAll()) {
            System.out.println(color.toString());
        }
    }


}
