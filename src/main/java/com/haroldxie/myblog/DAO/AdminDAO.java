package com.haroldxie.myblog.DAO;

import com.haroldxie.myblog.Domain.Admin;
import com.haroldxie.myblog.Domain.Blog;
import com.haroldxie.myblog.Domain.User;
import com.haroldxie.myblog.Utils.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminDAO {
    public Admin login(String adminName, String adgangskode) {
        Connection con = JDBCHelper.getConnection();
        Admin admin = new Admin();
        try {
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM admin WHERE adminName=? AND adgangskode=?");
            preparedStatement.setString(1, adminName);
            preparedStatement.setString(2, adgangskode);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                admin.setAdmin_id(rs.getInt("admin_id"));
                admin.setAdgangskode(rs.getString("adgangskode"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAvatarPath(rs.getString("avatarPath"));
                admin.setGender(rs.getInt("Gender"));
                admin.setEmailAddr(rs.getString("emailAddr"));
                admin.setPersonalStatement(rs.getString("personalStatement"));
            }
        } catch (Exception e) {
            System.err.println("FATAL: Not Correctly Connected!!!!!!!!!!!!!");
            e.printStackTrace();
        }
        return admin;
    }

    public int getBlogNumber () {
        Connection con = JDBCHelper.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT COUNT(blog_id) AS blogNumber FROM blog");
            if (rs.next()) {
                return rs.getInt("blogNumber");
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Blog> getAllBlogs() {
        Connection con = JDBCHelper.getConnection();
        List<Blog> blogList = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM blog");
            while (rs.next()) {
                Blog preprocessBlog = new Blog(
                        rs.getInt("blog_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getString("tags_id"),
                        rs.getInt("category_id"),
                        rs.getTimestamp("createdTime"),
                        rs.getTimestamp("lastEditedTime"),
                        rs.getInt("readingTime"),
                        rs.getString("content"),
                        rs.getString("picPath")
                );
                preprocessBlog.setAuthorName(getAuthorNameByID(rs.getInt("author_id")));
                blogList.add(preprocessBlog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }

    public boolean deleteBlogByID (int blog_id) {
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "DELETE FROM blog WHERE blog_id=?");
            preparedStatement.setInt(1, blog_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<User> getAllUsers () {
        List<User> allUsers = new ArrayList<>();
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User oneUser = new User();
                oneUser.setUser_id(rs.getInt("user_id"));
                oneUser.setAdgangskode(rs.getString("adgangskode"));
                oneUser.setUsername(rs.getString("username"));
                oneUser.setNickname(rs.getString("nickname"));
                oneUser.setGender(rs.getInt("Gender"));
                oneUser.setEmailAddr(rs.getString("emailAddr"));
                oneUser.setPersonalStatement(rs.getString("personalStatement"));
                oneUser.setAccountCreationDate(rs.getDate("accountCreationDate"));
                allUsers.add(oneUser);
            }
        } catch (Exception e) {
            System.err.println("FATAL: Not Correctly Connected!!!!!!!!!!!!!");
            e.printStackTrace();
        }
        return allUsers;
    }

    public boolean deleteUserByID (int user_id) {
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "DELETE FROM user WHERE user_id=?");
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserInfoByID (int user_id, String nickname, String adgangskode, String personalStatement) {
        Connection con = JDBCHelper.getConnection();
        try {
            if (Objects.equals(adgangskode, "")) {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "UPDATE user SET nickname=?, personalStatement=? WHERE user_id=?");
                preparedStatement.setString(1, nickname);
                preparedStatement.setString(2, personalStatement);
                preparedStatement.setInt(3, user_id);
                preparedStatement.executeUpdate();
            } else {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "UPDATE user SET nickname=?, adgangskode=? ,personalStatement=? WHERE user_id=?");
                preparedStatement.setString(1, nickname);
                preparedStatement.setString(2, adgangskode);
                preparedStatement.setString(3, personalStatement);
                preparedStatement.setInt(4, user_id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String getAuthorNameByID (int author_id) {
        Connection con = JDBCHelper.getConnection();
        String authorName = "";
        try {  // if user has a nickname, returns nickname, if not, returns username
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT username FROM user WHERE user_id=" + author_id);
            if (rs.next()) {
                authorName = rs.getString("username");
                System.out.println("username is " + authorName);
            }
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt
                    .executeQuery("SELECT nickname FROM user WHERE user_id=" + author_id);
            if (resultSet.next()) {
                if (resultSet.getString("nickname") != null) {
                    authorName = resultSet.getString("nickname");
                }
                System.out.println("nickname is " + authorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorName;
    }
}
