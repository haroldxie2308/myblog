package com.haroldxie.myblog.Business;

import com.haroldxie.myblog.DAO.UserDAO;
import com.haroldxie.myblog.Domain.Blog;
import com.haroldxie.myblog.Domain.User;

import java.util.*;

public class UserService {
    UserDAO userDAO = new UserDAO();
    public User login (String username, String adgangskode) {
        return userDAO.login(username, adgangskode);
    }
    public boolean signup (String realName, String adgangskode) {
        User newUser = new User(adgangskode, realName);
        boolean ret = userDAO.signup(newUser);
        System.out.println("Sign up status: " + ret);
        return ret;
    }
    public List<Blog> updatePreviewBlog () {
        List<Blog> previewBlogs = new ArrayList<>();
        int blogNumber = userDAO.getBlogNumber();
        System.out.println("We have " + userDAO.getBlogNumber() + " blogs.");
        List<Blog> blogList = userDAO.getAllBlogs();
        Set<Integer> seenPosts = new HashSet<>();
        Random rand = new Random();
        while (previewBlogs.size() <= 2) {
            int getThisBlog = rand.nextInt(blogNumber);
            if (seenPosts.contains(getThisBlog)) {
                continue;
            } else {
                seenPosts.add(getThisBlog);
            }
            Blog preprocessBlog = blogList.get(getThisBlog);
            if (preprocessBlog != null) {
                if (preprocessBlog.getContent().length() >= 200) {
                    // Cut too long content out
                    preprocessBlog.setContent(preprocessBlog.getContent().substring(0,100) + "...");
                }
                previewBlogs.add(preprocessBlog);
            }
        }
        return previewBlogs;
    }

    public boolean saveNewBlog (int user_id, String title, int readingTime, String content) {
        return userDAO.saveNewBlog(user_id, title, readingTime, content);
    }
    public Blog seeBlogDetail (int blog_id) {
        return userDAO.getBlogByID(blog_id);
    }
    public List<Blog> getBlogsByUserID (int user_id) {
        return userDAO.getBlogsByUserID(user_id);
    }

    public boolean deleteBlogByID (int blog_id) {
        return userDAO.deleteBlogByID(blog_id);
    }

    public boolean updateUserInfo (int user_id, String nickname, String adgangskode, String personalStatement) {
        return userDAO.updateUserInfo(user_id, nickname, adgangskode, personalStatement);
    }

    public List<Blog> getAllBlogs () {
        return userDAO.getAllBlogs();
    }

    public List<User> searchForUserByString (String str) {
        return userDAO.searchForUserByString(str);
    }

    public List<Blog> searchForBlogByString (String str) {
        return userDAO.searchForBlogByString(str);
    }

    public List<User> getAllAuthors() {
        return userDAO.getAllAuthors();
    }
}
