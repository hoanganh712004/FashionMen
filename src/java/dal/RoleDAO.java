/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class RoleDAO extends DBContext {

    public Role getRole(int roleId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [role_id]
                           ,[name]
                       FROM [dbo].[Roles]
                     WHERE role_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, roleId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int roleIdData = Integer.parseInt(resultSet.getString("role_id"));
                String roleName = resultSet.getString("name");
                Role role = new Role(roleIdData, roleName);
                return role;
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(new RoleDAO().getRole(1));
    }

}
