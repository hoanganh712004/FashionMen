/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class VerificationCodeGenerator {
        // Hàm tạo mã xác minh 6 ký tự ngẫu nhiên
    public static String generateVerificationCode() {
        // Tạo một chuỗi gồm tất cả các chữ cái (hoa và thường) và các chữ số
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        
        // Khởi tạo đối tượng Random
        Random random = new Random();

        // StringBuilder để lưu trữ mã xác minh
        StringBuilder code = new StringBuilder(6);

        // Chọn ngẫu nhiên 6 ký tự từ chuỗi characters
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }

        return code.toString();  // Trả về mã xác minh
    }

    public static void main(String[] args) {
        // Tạo mã xác minh và in ra
        String verificationCode = generateVerificationCode();
        System.out.println("Mã xác minh của bạn là: " + verificationCode);
    }
}
