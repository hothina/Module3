package vn.na.ho.coffee.controller;

import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.User;
import vn.na.ho.coffee.model.UserStatus;
import vn.na.ho.coffee.services.DrinkServices;
import vn.na.ho.coffee.services.IDrinkServices;
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
import java.util.List;
@WebServlet(name = "Drink", value = "/drinks")

public class DrinkController extends HttpServlet {

        IDrinkServices drinkServices = new DrinkServices();

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "create":
                        addDrink(request, response);
                        break;
                    case "edit":
                        updateDrink(request, response);
                        break;
                }

            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }


        private void updateDrink(HttpServletRequest request, HttpServletResponse response) throws
                SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            long price = Long.parseLong(request.getParameter("price"));

            try {
                Drink drink = drinkServices.updateDrink(new Drink(id, name, quantity, price));
                request.setAttribute("drink", drink);

                request.setAttribute("message", "Drink information was updated");
                request.setAttribute("classCss", "message success");
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.setAttribute("classCss", "message error");
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("drink/edit.jsp");
            dispatcher.forward(request, response);

        }

        private void addDrink(HttpServletRequest request, HttpServletResponse response) throws
                ServletException, IOException {
            String name = request.getParameter("name").trim();
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            long price = Long.parseLong(request.getParameter("price"));

            Drink newDrink = new Drink(name, quantity, price);
            try {
                drinkServices.addDrink(newDrink);
                request.setAttribute("message", "Drink information was created");
                request.setAttribute("classCss", "message success");
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.setAttribute("classCss", "message error");
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("drink/create.jsp");
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
            List<Drink> listDrink = drinkServices.selectAllDrinks();
            request.setAttribute("listDrink", listDrink);
            RequestDispatcher dispatcher = request.getRequestDispatcher("drink/list.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Drink existingUser = drinkServices.getById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("drink/edit.jsp");
            request.setAttribute("drink", existingUser);
            request.setAttribute("statusList", UserStatus.values());
            dispatcher.forward(request, response);
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("drink/create.jsp");
            dispatcher.forward(request, response);
        }

    }
