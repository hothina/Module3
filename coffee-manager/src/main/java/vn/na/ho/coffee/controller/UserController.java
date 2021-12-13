package vn.na.ho.coffee.controller;

import vn.na.ho.coffee.model.Role;
import vn.na.ho.coffee.model.User;
import vn.na.ho.coffee.model.UserStatus;
import vn.na.ho.coffee.services.IUserService;
import vn.na.ho.coffee.services.UserServices;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "User", value = "/users")
public class UserController extends HttpServlet {
    IUserService userService = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    addUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        UserStatus status = UserStatus.fromValue(Integer.parseInt(request.getParameter("status")));
        String birthDay = request.getParameter("birthDay");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        try {
            User user = userService.updateUser(new User(id, fullName, username, status, birthDay, phoneNumber, address));
            request.setAttribute("user", user);
            request.setAttribute("statusList", UserStatus.values());
            request.setAttribute("message", "Customer information was updated");
            request.setAttribute("classCss", "message success");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("classCss", "message error");
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request, response);

    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String fullName = request.getParameter("fullName").trim();
        String username = request.getParameter("username").trim();
        String birthDay = request.getParameter("birthDay").trim();
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address").trim();
        String password = request.getParameter("password".trim());

        User newUser = new User(fullName, username, birthDay, phoneNumber, address, password);
        try {
            userService.addUser(newUser);
            request.setAttribute("message", "Customer information was created");
            request.setAttribute("classCss", "message success");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("classCss", "message error");
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<User> listUser = userService.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        request.setAttribute("statusList", UserStatus.values());
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

}
