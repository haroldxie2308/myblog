package com.haroldxie.myblog.DAO;

import com.haroldxie.myblog.Domain.Blog;
import com.haroldxie.myblog.Domain.User;
import com.haroldxie.myblog.Utils.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO {
    public User login(String username, String adgangskode) {
        System.out.println("DAO-login called");
        User user = new User();
        try {
            Connection con = JDBCHelper.getConnection();
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM user WHERE username=? AND adgangskode=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, adgangskode);
            ResultSet executeQuery = preparedStatement.executeQuery();
            if (executeQuery.next()) {
                user.setUser_id(executeQuery.getInt("user_id"));
                user.setAdgangskode(executeQuery.getString("adgangskode"));
                user.setUsername(executeQuery.getString("username"));
                user.setGender(executeQuery.getInt("Gender"));
                user.setEmailAddr(executeQuery.getString("emailAddr"));
                user.setPersonalStatement(executeQuery.getString("personalStatement"));
                user.setAccountCreationDate(executeQuery.getDate("accountCreationDate"));
            }
        } catch (Exception e) {
            System.err.println("FATAL: Not Correctly Connected!!!!!!!!!!!!!");
            e.printStackTrace();
        }
        return user;
    }

    public boolean signup(User user) {
        Connection con = JDBCHelper.getConnection();
        System.out.println("DAO-signup called");
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM user WHERE username='" + user.getUsername() + "'");
            if (resultSet.next()) {
                return true;
            } else {
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(
                            "INSERT INTO user (username,adgangskode,accountCreationDate) VALUES (?,?,?)");
                    preparedStatement.setString(1, user.getUsername());
                    preparedStatement.setString(2, user.getAdgangskode());
                    preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getBlogNumber () {
        Connection con = JDBCHelper.getConnection();
        System.out.println("DAO-getBlogNumber called");
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

    public Blog getBlogByID(int blog_id) {
        Connection con = JDBCHelper.getConnection();
        System.out.println("DAO-getBlogByID called");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM blog WHERE blog_id=" + blog_id);
            if (rs.next()) {
                return addAuthor2Blog(rs);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Blog addAuthor2Blog(ResultSet rs) throws SQLException {
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
        return preprocessBlog;
    }

    public List<Blog> getAllBlogs() {
        Connection con = JDBCHelper.getConnection();
        List<Blog> blogList = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM blog");
            while (rs.next()) {
                blogList.add(addAuthor2Blog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }

    public boolean saveNewBlog(int user_id, String title, int readingTime, String content) {
        Connection con = JDBCHelper.getConnection();
        System.out.println("DAO-saveNewBlog called");
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO blog (author_id,title,readingTime,content,createdTime,lastEditedTime) VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, readingTime);
            preparedStatement.setString(4, content);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(java.time.LocalDateTime.now()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(java.time.LocalDateTime.now()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Blog> getBlogsByUserID (int user_id) {
        Connection con = JDBCHelper.getConnection();
        List<Blog> blogList = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM blog WHERE author_id=" + user_id);
            while (rs.next()) {
                blogList.add(addAuthor2Blog(rs));
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

    public boolean updateUserInfo (int user_id, String nickname, String adgangskode, String personalStatement) {
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

    public List<User> searchForUserByString (String str) {
        List<User> retUsers = new ArrayList<>();
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM user WHERE nickname LIKE '%" + str + "%'");  //  + "OR username LIKE '%?%'"
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
                retUsers.add(oneUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retUsers;
    }

    public List<Blog> searchForBlogByString (String str) {  // can only search for content now
        List<Blog> retBlogs = new ArrayList<>();
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM blog WHERE content LIKE '%" + str + "%'");  //  + "OR username LIKE '%?%'"
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                retBlogs.add(addAuthor2Blog(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retBlogs;
    }

    public List<User> getAllAuthors() {
        List<User> allAuthors = new ArrayList<>();
        Connection con = JDBCHelper.getConnection();
        try {
            PreparedStatement preparedStatement = con
                    .prepareStatement("SELECT * FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User oneUser = new User();
                oneUser.setUser_id(rs.getInt("user_id"));
                oneUser.setUsername(rs.getString("username"));
                oneUser.setNickname(rs.getString("nickname"));
                oneUser.setGender(rs.getInt("Gender"));
                oneUser.setEmailAddr(rs.getString("emailAddr"));
                oneUser.setPersonalStatement(rs.getString("personalStatement"));
                oneUser.setAccountCreationDate(rs.getDate("accountCreationDate"));
                allAuthors.add(oneUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allAuthors;
    }
}
