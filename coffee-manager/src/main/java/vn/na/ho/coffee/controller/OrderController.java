package vn.na.ho.coffee.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.Order;
import vn.na.ho.coffee.model.OrderDetail;
import vn.na.ho.coffee.services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "Order", value = "/orders")

public class OrderController extends HttpServlet {
    IOrderServices orderServices = new OrderServices();
    IDrinkServices drinkServices = new DrinkServices();
    IOrderDetailServices orderDetailServices = new OrderDetailServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":

                addOrderr(request, response);

                break;


        }
    }


    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String nameCustomer = request.getParameter("nameCustomer").trim();
//            System.out.println(nameCustomer);
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String address = request.getParameter("address").trim();
        String createdAt = request.getParameter("createdAt").trim();

        int idUser = Integer.parseInt(request.getParameter("idUser"));


        Order newOrder = new Order(nameCustomer, phoneNumber, address, createdAt, idUser);
        try {

            orderServices.addOrder(newOrder);
            int id = orderServices.getIdOrder();
            request.setAttribute("message", "Order information was created");
            request.setAttribute("classCss", "message success");
            request.setAttribute("idOrder", id);
            List<Drink> list = drinkServices.selectAllDrinks();
            request.setAttribute("listDrink", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetail/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("classCss", "message error");
            e.printStackTrace();
//                RequestDispatcher dispatcher = request.getRequestDispatcher("order/create.jsp");
//                dispatcher.forward(request, response);
        }

    }

    private void addOrderr(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String nameCustomer = request.getParameter("nameCustomer").trim();
//            System.out.println(nameCustomer);
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String address = request.getParameter("address").trim();
        String createdAt = (new Date()).toString();
        Order newOrder = new Order(nameCustomer, phoneNumber, address, "2021-12-23", 2);
        Integer orderId = null;
        try {

            orderServices.addOrder(newOrder);
            orderId = orderServices.getIdOrder();

            request.setAttribute("message", "Order information was created");
            request.setAttribute("classCss", "message success");
//            request.setAttribute("idOrder", id);
//            List<Drink> list = drinkServices.selectAllDrinks();
//            request.setAttribute("listDrink", list);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetail/create.jsp");
            // dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("classCss", "message error");
            e.printStackTrace();

        }
        if (orderId != null) {
            List<OrderDetail> orderDetails = new ArrayList<>();

            String[] drinkIds = request.getParameterValues("id");
            String[] qtys = request.getParameterValues("qty");
            for (int i = 0; i < drinkIds.length; i++) {
                int drinkId = Integer.parseInt(drinkIds[i]);
                Drink drink = drinkServices.getById(drinkId);

                int qty = Integer.parseInt(qtys[i]);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setIdDrink(drinkId);
                orderDetail.setIdOrder(orderId);
                orderDetail.setQuantity(qty);
                orderDetail.setPrice(drink.getPrice());

                orderDetails.add(orderDetail);
                orderDetailServices.addOrderDetail(orderDetail);
                response.sendRedirect(request.getContextPath() + "/orders");
                return;
            }
        }


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
                    listOrder(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Order> listOrder = orderServices.selectAllOrders();
        request.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("order/list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("drinks", drinkServices.getDrinks());
        RequestDispatcher dispatcher = request.getRequestDispatcher("order/createe.jsp");
        dispatcher.forward(request, response);
    }


}
