package com.haroldxie.myblog.Business;

import com.haroldxie.myblog.DAO.AdminDAO;
import com.haroldxie.myblog.Domain.Admin;
import com.haroldxie.myblog.Domain.Blog;
import com.haroldxie.myblog.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();
    public Admin login (String adminName, String adgangskode) {
        return adminDAO.login(adminName, adgangskode);
    }

    public List<Blog> getAllBlogs () {
        List<Blog> allBlogs = new ArrayList<>();
        System.out.println("We have " + adminDAO.getBlogNumber() + " blogs.");
        List<Blog> blogList = adminDAO.getAllBlogs();
        for (Blog preprocessBlog : blogList) {
            if (preprocessBlog != null) {
                if (preprocessBlog.getContent().length() >= 50) {
                    preprocessBlog.setContent(preprocessBlog.getContent().substring(0,40) + "...");
                }
                allBlogs.add(preprocessBlog);
            }
        }
        return allBlogs;
    }

    public List<Blog> updatePreviewBlog () {  // show first 3 blogs
        List<Blog> previewBlogs = new ArrayList<>();
        System.out.println("We have " + adminDAO.getBlogNumber() + " blogs.");
        List<Blog> blogList = adminDAO.getAllBlogs();
        for (Blog preprocessBlog : blogList) {
            if (preprocessBlog != null) {
                if (preprocessBlog.getContent().length() >= 50) {
                    preprocessBlog.setContent(preprocessBlog.getContent().substring(0,40) + "...");
                }
                previewBlogs.add(preprocessBlog);
                if (previewBlogs.size() >= 3) break;
            }
        }
        return previewBlogs;
    }

    public List<User> getAllUsers () {
        return adminDAO.getAllUsers();
    }

    public boolean deleteUserByID (int user_id) {
        return adminDAO.deleteUserByID(user_id);
    }

    public boolean updateUserInfoByID (int user_id, String nickname, String adgangskode, String personalStatement) {
        return adminDAO.updateUserInfoByID(user_id, nickname, adgangskode, personalStatement);
    }

//    public boolean saveNewBlog (int user_id, String title, int readingTime, String content) {
//        return adminDAO.saveNewBlog(user_id, title, readingTime, content);
//    }
//    public Blog seeBlogDetail (int blog_id) {
//        return adminDAO.getBlogByID(blog_id);
//    }
//    public List<Blog> getBlogsByUserID (int user_id) {
//        return adminDAO.getBlogsByUserID(user_id);
//    }
//
    public boolean deleteBlogByID (int blog_id) {
        return adminDAO.deleteBlogByID(blog_id);
    }
//
//    public boolean updateUserInfo (int user_id, String nickname, String adgangskode, String personalStatement) {
//        return adminDAO.updateUserInfo(user_id, nickname, adgangskode, personalStatement);
//    }
}
