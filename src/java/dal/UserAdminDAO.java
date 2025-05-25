/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.TopCustomAdminDTO;
import dto.UserListAdminDTO;
import dto.UserVericationDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Product;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserAdminDAO extends DBContext {

    public List<UserListAdminDTO> getUserList() {

        // Create userAdminDTOLists
        List<UserListAdminDTO> userAdminLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT u.user_id, u.image, u.full_name, u.email, u.phone_number, COALESCE(SUM(od.quantity), 0) as Total_quantity, u.role_id, u.created_at 
                     FROM Users u
                     LEFT JOIN Orders o on u.user_id = o.user_id
                     LEFT JOIN Order_Details od on o.Order_id = od.order_id
                     GROUP BY u.user_id, u.image, u.full_name, u.email, u.phone_number,u.role_id, u.created_at """;

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String image = resultSet.getString("image");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                int totalBuy = Integer.parseInt(resultSet.getString("Total_quantity"));
                int roleId = Integer.parseInt(resultSet.getString("role_id"));
                Date createdAt = resultSet.getDate("created_at");

                Role role = new Role();
                RoleDAO roleDAO = new RoleDAO();
                role = roleDAO.getRole(roleId);

                UserListAdminDTO userListAdminDTO = new UserListAdminDTO(userId, image, fullName, email, phoneNumber, totalBuy, role, createdAt);
                userAdminLists.add(userListAdminDTO);
            }

            return userAdminLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(int userId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[Users]
                           WHERE user_id = ?""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, userId);

            // ExecuteQuery
            int checkDeleteUser = statement.executeUpdate();

            return checkDeleteUser > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalUser() {

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT COUNT(user_id) as Total_User
                       FROM [Users]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // executeQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalUser = Integer.parseInt(resultSet.getString("Total_User"));

                return totalUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<TopCustomAdminDTO> getTopCustomer() {

        // Create topCustomerlList
        List<TopCustomAdminDTO> topCustomerlList = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     With TopCustomer as(
                     SELECT user_id, Sum(total_money) as Total_Money
                     FROM Orders
                     GROUP BY user_id
                     ) SELECT Top 10 u.user_id, u.image, u.full_name, u.email, tc.Total_Money
                     FROM Users u
                     JOIN TopCustomer tc on u.user_id = tc.user_id ORDER BY tc.Total_Money desc""";
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String image = resultSet.getString("image");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                float totalMoney = Float.parseFloat(resultSet.getString("Total_Money"));

                TopCustomAdminDTO topCustomAdminDTO = new TopCustomAdminDTO(userId, image, fullName, email, totalMoney);
                topCustomerlList.add(topCustomAdminDTO);
            }
            return topCustomerlList;

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertUser(User user) {

        // Connect database
        connection = getConnection();

        if (checkUsername(user.getUserName())) {
            return false;
        }

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([role_id]
                                ,[user_name]
                                ,[pass_word]
                                ,[full_name]
                                ,[birth_day]
                                ,[image]
                                ,[phone_number]
                                ,[address]
                                ,[email]
                                ,[created_at])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, user.getRoleId());
            statement.setObject(2, user.getUserName());
            statement.setObject(3, user.getPassWord());
            statement.setObject(4, user.getFullName());
            statement.setObject(5, user.getBirthDay());
            statement.setObject(6, user.getImage());
            statement.setObject(7, user.getPhoneNumber());
            statement.setObject(8, user.getAddress());
            statement.setObject(9, user.getEmail());
            statement.setObject(10, user.getCreatedAt());

            // ExecuteUpdate
            int checkInsertUser = statement.executeUpdate();

            return checkInsertUser > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean checkUsername(String username) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [user_id]
                           ,[role_id]
                           ,[user_name]
                           ,[pass_word]
                           ,[full_name]
                           ,[birth_day]
                           ,[image]
                           ,[phone_number]
                           ,[address]
                           ,[email]
                           ,[created_at]
                       FROM [dbo].[Users]
                       WHERE user_name = ? """;

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, username);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            // if have result will be true
            return resultSet.next();

        } catch (SQLException e) {

        }
        return false;
    }

    public boolean updateVerification(String verificationCode, String email) {

        connection = getConnection();

        String sql = """
                     UPDATE [dbo].[Users]
                        SET [verification_code] = ?
                      WHERE email = ?""";

        try {

            statement = connection.prepareStatement(sql);

            statement.setObject(1, verificationCode);
            statement.setObject(2, email);

            int checkUpdateVerificationCode = statement.executeUpdate();

            return checkUpdateVerificationCode > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<UserVericationDTO> getUserListAfterupdatVerificattion() {

        List<UserVericationDTO> list = new ArrayList<>();

        connection = getConnection();
        
        String sql = """
                     SELECT *
                       FROM [dbo].[Users]""";

        
        try {
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {                
                String email = resultSet.getString("email");
                String verificationCode = resultSet.getString("verification_code");
                
                UserVericationDTO userVericationDTO = new UserVericationDTO();
                userVericationDTO.setEmail(email);
                userVericationDTO.setVerificationCode(verificationCode);
                
                list.add(userVericationDTO);
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    
    public List<UserListAdminDTO> getUserListnpassword() {
          // Create userAdminDTOLists
        List<UserListAdminDTO> userAdminLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT u.user_id, u.image, u.user_name, u.email, u.phone_number, COALESCE(SUM(od.quantity), 0) as Total_quantity, u.role_id, u.created_at 
                                          FROM Users u
                                          LEFT JOIN Orders o on u.user_id = o.user_id
                                          LEFT JOIN Order_Details od on o.Order_id = od.order_id
                                          GROUP BY u.user_id, u.image, u.user_name, u.email, u.phone_number,u.role_id, u.created_at""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String image = resultSet.getString("image");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                int totalBuy = Integer.parseInt(resultSet.getString("Total_quantity"));
                int roleId = Integer.parseInt(resultSet.getString("role_id"));
                Date createdAt = resultSet.getDate("created_at");

                Role role = new Role();
                RoleDAO roleDAO = new RoleDAO();
                role = roleDAO.getRole(roleId);

                UserListAdminDTO userListAdminDTO = new UserListAdminDTO(userId, image, userName, email, phoneNumber, totalBuy, role, createdAt);
                userAdminLists.add(userListAdminDTO);
            }

            return userAdminLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
     public static void main(String[] args) {
//        for (UserListAdminDTO userListAdminDTO : new UserAdminDAO().getUserList()) {
//            System.out.println(userListAdminDTO.toString());
//        }
//        System.out.println(new UserAdminDAO().deleteUser(51));
//        for (TopCustomAdminDTO topCustomAdminDTO : new UserAdminDAO().getTopCustomer()) {
//            System.out.println(topCustomAdminDTO.toString());
//        }
            for (UserVericationDTO userVericationDTO : new UserAdminDAO().getUserListAfterupdatVerificattion()) {
                System.out.println(userVericationDTO.toString());
         }
    }

}
