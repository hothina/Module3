package vn.na.ho.coffee.controller;

import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.OrderDetail;
import vn.na.ho.coffee.model.UserStatus;
import vn.na.ho.coffee.services.DrinkServices;
import vn.na.ho.coffee.services.IDrinkServices;
import vn.na.ho.coffee.services.IOrderDetailServices;
import vn.na.ho.coffee.services.OrderDetailServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderDetail", value = "/orderdetail")

public class OrderDetailContronller extends HttpServlet {
    IOrderDetailServices  orderDetailServices = new OrderDetailServices();
    IDrinkServices drinkServices = new DrinkServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action+"Je;");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addOrderDetail(request, response);
                break;

        }

    }

    private void addOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("qwer");

        int idDrink = Integer.parseInt(request.getParameter("idDrink"));
        System.out.println(idDrink);
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        request.setAttribute("idOrder",idOrder);
        System.out.println(idOrder);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println(quantity);


        OrderDetail newOrderDetail = new OrderDetail(idDrink,idOrder, quantity);
        try {

            orderDetailServices.addOrderDetail(newOrderDetail);
            request.setAttribute("message", "Drink information was created");
            request.setAttribute("classCss", "message success");
            List<Drink> list = drinkServices.selectAllDrinks();
            request.setAttribute("listDrink",list);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("classCss", "message error");
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetail/create.jsp");
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

                default:
                    listOrderDetail(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrderDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<OrderDetail> listOrderDetail = orderDetailServices.selectAllOrderDetail();
        request.setAttribute("listOrderDetail", listOrderDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetail/list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetail/create.jsp");
        dispatcher.forward(request, response);
    }

}
