package com.haroldxie.myblog.Web;

import com.haroldxie.myblog.Business.UserService;
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

@WebServlet(name = "UserServer", value = "/UserServer")
public class UserServer extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String op = req.getParameter("op");
        updatePreviewBlogs(req, resp);
        switch (op) {
            case "index": index(req, resp); break;
            case "login": login(req, resp); break;
            case "toLogin": toLogin(req, resp); break;
            case "signup": signup(req, resp); break;
            case "createNewBlog": createNewBlog(req, resp); break;
            case "saveNewBlog": saveNewBlog(req, resp); break;
            case "seeBlogDetail": seeBlogDetail(req, resp); break;
            case "accountManagement": accountManagement(req, resp); break;
            case "deleteBlog": deleteBlog(req, resp); break;
            case "updateUserInfo": updateUserInfo(req, resp); break;
            case "logout": logout(req, resp); break;
            case "showAllBlogs": showAllBlogs(req, resp); break;
            case "search": search(req, resp); break;
//            case "showAllAuthors": showAllAuthors(req, resp); break;
            default: develepmentInProgress(req, resp);
        }
    }

    private void develepmentInProgress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("developmentInProgress.jsp").forward(req, resp);
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void updatePreviewBlogs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        HttpSession session = req.getSession();
        session.setAttribute("blogs", userService.updatePreviewBlog());
    }


    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String adgangskode = req.getParameter("adgangskode");
        System.out.println("Username/ID: " + username + " Password: " + adgangskode);
        HttpSession session = req.getSession();
        User user = userService.login(username, adgangskode);
        System.out.println("User ID:" + user.getUser_id());
        System.out.println(user.getUsername());
        if (user.getUsername() != null && user.getUsername() != "") {
            req.setAttribute("message", "Login Succeeded!");
            session.setAttribute("user", user);
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Wrong username/id or password, please try again.");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            String adgangskode = req.getParameter("adgangskode");
            if (!username.equals("") && !adgangskode.equals("")) {
                if (userService.signup(username, adgangskode)) {
                    req.setAttribute("message", "User already exits, please log in directly. ");
                } else {
                    req.setAttribute("message", "Signed up successfully, you can log in now! ");
                }
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNewBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createNewBlog.jsp").forward(req, resp);
    }

    private void saveNewBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (userService.saveNewBlog(currentUser.getUser_id(), req.getParameter("title"), Integer.parseInt(req.getParameter("readingTime")), req.getParameter("content"))) {
            req.setAttribute("message", "Blog saved successfully");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Blog not saved correctly, please try again.");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }

    private void seeBlogDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int blog_id = Integer.parseInt(req.getParameter("blog_id"));
        Blog thisBlog = userService.seeBlogDetail(blog_id);
        req.setAttribute("blog", thisBlog);
        req.getRequestDispatcher("blogDetail.jsp").forward(req, resp);
    }

    private void accountManagement (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Blog> blogs = userService.getBlogsByUserID(user.getUser_id());
        req.setAttribute("blogsByUserID", blogs);
        req.getRequestDispatcher("accountManagement.jsp").forward(req, resp);
    }

    public void deleteBlog (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int blog_id = Integer.parseInt(req.getParameter("blog_id"));
        if (userService.deleteBlogByID(blog_id)) {
            req.setAttribute("message", "Blog Deleted");
        } else {
            req.setAttribute("message", "Blog not corrected deleted, Please try again");
        }
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    private void search (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchFor = req.getParameter("searchLine");
        List<User> retUsers = userService.searchForUserByString(searchFor);
        List<Blog> retBlogs = userService.searchForBlogByString(searchFor);
        req.setAttribute("searchFor", searchFor);
        if (retUsers.size() >= 1) {
            req.setAttribute("retUsers", retUsers);
        }
        if (retBlogs.size() >= 1) {
            req.setAttribute("retBlogs", retBlogs);
        }
        req.getRequestDispatcher("searchRet.jsp").forward(req, resp);
    }

    private void showAllBlogs (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> allBlogs = userService.getAllBlogs();
        req.setAttribute("allBlogs", allBlogs);
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }

    public void updateUserInfo (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        boolean ret = userService.updateUserInfo(
                user.getUser_id(),
                req.getParameter("nickname"),
                req.getParameter("adgangskode"),
                req.getParameter("personalStatement")
                );
        if (ret) {
            req.setAttribute("message", "User Info Updated");
        } else {
            req.setAttribute("message", "User Info Not Updated, Please try again later.");
        }
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    public void showAllAuthors (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allAuthors", userService.getAllAuthors());
        req.getRequestDispatcher("authors.jsp").forward(req, resp);
    }

    public void logout (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.setAttribute("message", "User logged out.");
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
