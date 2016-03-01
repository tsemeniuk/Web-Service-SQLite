package main.java.com.study.userStore.handler;

import main.java.com.study.userStore.dao.User;
import main.java.com.study.userStore.main.PageGenerator;
import main.java.com.study.userStore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserListServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAll();
        pageData.put("users", users);
        pageData.put("info", "User added successful");

        resp.getWriter().println(PageGenerator.instance().getPage("users.main.resources.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.save();
        userService.initialize();
        pageData.put("info", "Data Base was updated successful.");

        resp.getWriter().println(PageGenerator.instance().getPage("users.main.resources.html", pageData));
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}