/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.UpdateUserDTO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    public User getUserById(int getUserId) {

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
                        WHERE user_id = ?""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, getUserId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                int roleId = Integer.parseInt(resultSet.getString("role_id"));
                String userName = resultSet.getString("user_name");
                String passWord = resultSet.getString("pass_word");
                String fullName = resultSet.getString("full_name");
                Date birthDay = resultSet.getDate("birth_day");
                String image = resultSet.getString("image");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                Date createdAt = resultSet.getDate("created_at");

                // Create object user
                User user = new User(userId, roleId, userName, passWord, fullName, birthDay, image, phoneNumber, address, email, createdAt);

                return user;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public User checkLogin(String username, String password) {

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
                       WHERE user_name = ? AND pass_word = ? """;

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, username);
            String maHoa = encodeMD5(password); // md5
            statement.setObject(2, maHoa);

            //
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                int role = Integer.parseInt(resultSet.getString("role_id"));
                String userName = resultSet.getString("user_name");
                String passWord = resultSet.getString("pass_word");
                String fullName = resultSet.getString("full_name");
                Date birthDay = resultSet.getDate("birth_day");
                String image = resultSet.getString("image");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                Date createdAt = resultSet.getDate("created_at");

                // Create object user
                User user = new User(userId, role, userName, passWord, fullName, birthDay, image, phoneNumber, address, email, createdAt);

                return user;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public boolean checkRegister(String username, String password) {

        // Connect database
        connection = getConnection();

        if (checkUsername(username)) {
            return false;
        }

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([role_id]
                                ,[user_name]
                                ,[pass_word]
                                ,[created_at])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?)""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, 2);
            statement.setObject(2, username);
            String maHoa = encodeMD5(password);
            statement.setObject(3, maHoa);
            statement.setTimestamp(4, Timestamp.from(Instant.now())); // Set time now

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
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

    public boolean updateUser(UpdateUserDTO updateUserDTO) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [pass_word] = ?
                           ,[full_name] = ?
                           ,[birth_day] = ?
                           ,[image] = ?
                           ,[phone_number] = ?
                           ,[address] = ?
                           ,[email] = ?
                      WHERE user_id = ?""";

        try {

            // Creat Object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            String maHoa = encodeMD5(updateUserDTO.getNewPassword()); //md5
            statement.setObject(1, maHoa);
            statement.setObject(2, updateUserDTO.getFullName());
            java.sql.Date date = java.sql.Date.valueOf(updateUserDTO.getBirthday());
            statement.setObject(3, date);
            statement.setObject(4, updateUserDTO.getImage());
            statement.setObject(5, updateUserDTO.getPhoneNumber());
            statement.setObject(6, updateUserDTO.getAddress());
            statement.setObject(7, updateUserDTO.getEmail());
            statement.setObject(8, updateUserDTO.getId());

            // ExecuteUpdate
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String encodeMD5(String password) {
        try {
            // Lấy một instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Thêm mật khẩu vào để tính toán
            md.update(password.getBytes());

            // Lấy giá trị băm
            byte[] digest = md.digest();

            // Chuyển đổi byte array thành chuỗi hex
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);

            // Đảm bảo chuỗi hex có đủ 32 ký tự
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public boolean updatepasswordUserByEmail(String email, String npassword) {

        connection = getConnection();

        String sql = """
                     UPDATE [dbo].[Users]
                        SET [pass_word] = ?
                      WHERE [email] = ?""";

        try {
            statement = connection.prepareStatement(sql);

            statement.setObject(1, npassword);
            statement.setObject(2, email);

            int checkUpdatepasswordUserByEmail = statement.executeUpdate();

            return checkUpdatepasswordUserByEmail > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new UserDAO().checkLogin("admin", "1234"));

    }

}
