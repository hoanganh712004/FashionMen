/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.ContactusDTO;
import dto.FeedbackDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.FeedBack;
import model.User;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBContext {

    public boolean insertFeedback(ContactusDTO contactusDTO) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[FeedBack]
                                ([user_id]
                                ,[subject_name]
                                ,[note]
                                ,[feedback_date])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?)""";

        try {

            // Creat Object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object 
            statement.setObject(1, contactusDTO.getUser().getUserId());
            statement.setObject(2, contactusDTO.getSubjectName());
            statement.setObject(3, contactusDTO.getNote());
            statement.setObject(4, contactusDTO.getFeebackDate());

            // ExecuteUpdate
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<FeedbackDTO> getFeedback() {

        List<FeedbackDTO> feedbackLists = new ArrayList<>();

        connection = getConnection();

        String sql = "SELECT u.image, u.user_name, f.subject_name, f.feedback_date, f.note "
                + "FROM Users u "
                + "JOIN Feedback f ON u.user_id = f.user_id";

        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                String image = resultSet.getString("image");
                String userName = resultSet.getString("user_name");
                String subjectName = resultSet.getString("subject_name");
                Date feedbackDate = resultSet.getDate("feedback_date");
                String note = resultSet.getString("note");

                // Tạo đối tượng FeedbackDTO
                FeedbackDTO feedback = new FeedbackDTO(image, userName, subjectName, feedbackDate, note);

                feedbackLists.add(feedback);
            }
            return feedbackLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public List<FeedBack> getFeedbackAlls() {

        List<FeedBack> feedbackLists = new ArrayList<>();

        connection = getConnection();

        String sql = "SELECT f.feedback_id, f.user_id, f.subject_name, f.feedback_date, f.note "
                + "FROM Feedback f";

        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                int feedbackId = Integer.parseInt(resultSet.getString("feedback_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String subjectName = resultSet.getString("subject_name");
                Date feedbackDate = resultSet.getDate("feedback_date");
                String note = resultSet.getString("note");

                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserById(userId);

                FeedBack feedBack = new FeedBack(feedbackId, user, subjectName, note, feedbackDate);

                feedbackLists.add(feedBack);
            }
            return feedbackLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public FeedBack getFeedBack(int feedbackId) {
        connection = getConnection();

        String sql = """
                     SELECT [feedback_id]
                           ,[user_id]
                           ,[subject_name]
                           ,[note]
                           ,[feedback_date]
                       FROM [dbo].[FeedBack]
                       WHERE feedback_id = ?""";

        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, feedbackId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                int feedbackIdData = Integer.parseInt(resultSet.getString("feedback_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String subjectName = resultSet.getString("subject_name");
                Date feedbackDate = resultSet.getDate("feedback_date");
                String note = resultSet.getString("note");

                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserById(userId);

                FeedBack feedBack = new FeedBack(feedbackIdData, user, subjectName, note, feedbackDate);

                return feedBack;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateFeedBack(int feedbackId, String subject, String message) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[FeedBack]
                        SET [subject_name] = ?
                           ,[note] = ?
                      WHERE feedback_id = ?""";

        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, subject);
            statement.setObject(2, message);
            statement.setObject(3, feedbackId);

            // ExecuteUpdate
            int checkUpdateFeedback = statement.executeUpdate();

            return checkUpdateFeedback > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteFeedback(int feedbackId) {
        
        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[FeedBack]
                           WHERE feedback_id = ?""";

        try {
            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, feedbackId);
         

            // ExecuteUpdate
            int checkDeleteFeedback = statement.executeUpdate();

            return checkDeleteFeedback > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(new FeedbackDAO().getFeedBack(5));
    }


}
