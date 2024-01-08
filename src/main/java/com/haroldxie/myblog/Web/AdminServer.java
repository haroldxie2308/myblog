package com.haroldxie.myblog.Web;

import com.haroldxie.myblog.Business.AdminService;
import com.haroldxie.myblog.Domain.Admin;
import com.haroldxie.myblog.Domain.Blog;
import com.haroldxie.myblog.Domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServer", value = "/AdminServer")
public class AdminServer extends HttpServlet {
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String op = req.getParameter("op");
        updatePreviewBlogs(req, resp);
        switch (op) {
            case "login": login(req, resp); break;
            case "siteManagement": siteManagement(req, resp); break;
            case "deleteUser": deleteUser(req, resp); break;
//            case "createNewBlog": createNewBlog(req, resp); break;
//            case "saveNewBlog": saveNewBlog(req, resp); break;
//            case "seeBlogDetail": seeBlogDetail(req, resp); break;
//            case "accountManagement": accountManagement(req, resp); break;
            case "deleteBlog": deleteBlog(req, resp); break;
            case "updateUserInfo": updateUserInfo(req, resp); break;
            case "logout": logout(req, resp); break;
            default: index(req, resp);
        }
    }

    private void updateUserInfo (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        adminService.updateUserInfoByID(
                Integer.parseInt(req.getParameter("user_id")),
                req.getParameter("nickname"),
                req.getParameter("adgangskode"),
                req.getParameter("personalStatement")
        );
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName = req.getParameter("adminName");
        String adgangskode = req.getParameter("adgangskode");
        System.out.println("Adminname/ID: " + adminName + " Password: " + adgangskode);
        HttpSession session = req.getSession();
        Admin admin = adminService.login(adminName, adgangskode);
        System.out.println("Admin ID:" + admin.getAdmin_id());
        if (admin.getAdminName() != null && admin.getAdminName() != "") {
            req.setAttribute("message", "Admin Login Succeeded!");
            session.setAttribute("admin", admin);
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Wrong adminname/id or password, please try again.");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }

    private void siteManagement (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> allBlogs = adminService.getAllBlogs();
        List<User> allUsers = adminService.getAllUsers();
        req.setAttribute("allBlogs", allBlogs);
        req.setAttribute("allUsers", allUsers);
        req.getRequestDispatcher("siteManagement.jsp").forward(req, resp);
    }

    private void updatePreviewBlogs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("blogs", adminService.updatePreviewBlog());
    }

    private void deleteUser (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        adminService.deleteUserByID(user_id);
        req.setAttribute("message", "User (ID:" + user_id + " deleted.");
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }
//
//    private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("login.jsp").forward(req, resp);
//    }
//
//    private void signup(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            String username = req.getParameter("username");
//            String adgangskode = req.getParameter("adgangskode");
//            HttpSession session = req.getSession();
//            adminService.signup(username, adgangskode);
//
//            boolean isExist = false;
//
//            if (!username.equals("") && !adgangskode.equals("")) {
//                isExist = adminService.signup(username, adgangskode);
//                if (isExist) {
//                    resp.getWriter().write("User already exists, please ");
//                    resp.getWriter().write("<a href='" + req.getContextPath() + "/UserServer?op=toLogin'>Login Here</a>");
//                } else {
//                    resp.getWriter().write("Sign up successful! ");
//                    resp.getWriter().write("Redirecting to Login Page in 3s...");
//                    resp.setHeader("Refresh", "3;URL=" + req.getContextPath() + "/UserServer?op=login");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void createNewBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/createNewBlog.jsp").forward(req, resp);
//    }
//
//    private void saveNewBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User currentUser = (User) session.getAttribute("user");
//        if (adminService.saveNewBlog(currentUser.getUser_id(), req.getParameter("title"), Integer.parseInt(req.getParameter("readingTime")), req.getParameter("content"))) {
//            req.setAttribute("message", "Blog saved successfully");
//            req.getRequestDispatcher("message.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("message", "Blog not saved correctly, please try again.");
//            req.getRequestDispatcher("message.jsp").forward(req, resp);
//        }
//    }
//
//    private void seeBlogDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int blog_id = Integer.parseInt(req.getParameter("blog_id"));
//        Blog thisBlog = adminService.seeBlogDetail(blog_id);
//        req.setAttribute("blog", thisBlog);
//        req.getRequestDispatcher("blogDetail.jsp").forward(req, resp);
//    }
//
//    private void accountManagement (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        List<Blog> blogs = adminService.getBlogsByUserID(user.getUser_id());
//        req.setAttribute("blogsByUserID", blogs);
//        req.getRequestDispatcher("accountManagement.jsp").forward(req, resp);
//    }
//
    public void deleteBlog (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int blog_id = Integer.parseInt(req.getParameter("blog_id"));
        if (adminService.deleteBlogByID(blog_id)) {
            req.setAttribute("message", "Blog Deleted");
        } else {
            req.setAttribute("message", "Blog not corrected deleted, Please try again");
        }
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    private void search (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
//
//    public void updateUserInfo (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = (User) req.getSession().getAttribute("user");
//        boolean ret = adminService.updateUserInfo(
//                user.getUser_id(),
//                req.getParameter("nickname"),
//                req.getParameter("adgangskode"),
//                req.getParameter("personalStatement")
//                );
//        if (ret) {
//            req.setAttribute("message", "User Info Updated");
//        } else {
//            req.setAttribute("message", "User Info Not Updated, Please try again later.");
//        }
//        req.getRequestDispatcher("message.jsp").forward(req, resp);
//    }

    public void logout (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("admin");
        req.setAttribute("message", "Admin logged out.");
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
