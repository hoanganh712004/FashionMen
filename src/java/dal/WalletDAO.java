/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.WalletAdminDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class WalletDAO extends DBContext {

    public float getBalanceOfAccount(int userId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [wallet_id]
                           ,[user_id]
                           ,[balance]
                       FROM [dbo].[Wallets]
                       WHERE user_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, userId);

            // 
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                float wallet = Float.parseFloat(resultSet.getString("balance"));
                return wallet;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean updateWalletOfUser(int userId, float walletAfter) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Wallets]
                        SET [balance] = ?
                      WHERE user_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, walletAfter);
            statement.setObject(2, userId);

            // ExecuteUpdate
            int checkUpdateWallet = statement.executeUpdate();

            return checkUpdateWallet > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<WalletAdminDTO> getWalletList() {

        // Create walletAdminLists
        List<WalletAdminDTO> walletAdminLists = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT w.wallet_id ,u.user_id,u.image, u.user_name, w.balance
                     FROM Wallets w
                     JOIN Users u on w.user_id = u.user_id""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int walletId = Integer.parseInt(resultSet.getString("wallet_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String image = resultSet.getString("image");
                String userName = resultSet.getString("user_name");
                float balance = Float.parseFloat(resultSet.getString("balance"));

                WalletAdminDTO walletAdminDTO = new WalletAdminDTO(walletId, userId, image, userName, balance);
                walletAdminLists.add(walletAdminDTO);
            }
            return walletAdminLists;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(int walletId, float balance) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Wallets]
                        SET [balance] = ?
                      WHERE wallet_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, balance);
            statement.setObject(2, walletId);

            // ExecuteUpdate
            int checkUpdateBalance = statement.executeUpdate();

            return checkUpdateBalance > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertWallet(int userId) {

        connection = getConnection();

        String sql = """
                     INSERT INTO [dbo].[Wallets]
                                ([user_id]
                                ,[balance])
                          VALUES
                                (?
                                ,?)""";
        try {

            statement = connection.prepareStatement(sql);

            statement.setObject(1, userId);
            statement.setObject(2, 0.0);

            int checkInsertWallet = statement.executeUpdate();

            return checkInsertWallet > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteWallet(int userId) {
        
        connection = getConnection();

        String sql = """
                     DELETE FROM [dbo].[Wallets]
                           WHERE user_id = ?""";
        try {

            statement = connection.prepareStatement(sql);

            statement.setObject(1, userId);

            int checkdeleteWallet = statement.executeUpdate();

            return checkdeleteWallet > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WalletDAO().updateWalletOfUser(3, 7000000));
        for (WalletAdminDTO walletAdminDTO : new WalletDAO().getWalletList()) {
            System.out.println(walletAdminDTO.toString());
        }
    }

}
