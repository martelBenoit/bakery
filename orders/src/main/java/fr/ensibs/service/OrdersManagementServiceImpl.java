package fr.ensibs.service;

import fr.ensibs.dao.OrderDAO;
import fr.ensibs.dao.ProductDAO;
import fr.ensibs.model.Order;
import fr.ensibs.model.Product;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {

    protected UsersManagementService usersManagementPort;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrdersManagementServiceImpl() {
        UsersManagementService_Service usersManagementService = new UsersManagementService_Service();
        usersManagementPort = usersManagementService.getUsersManagementPort();
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }

    @Override
    public boolean addOrder(String token, int id, int quantity) {
        boolean ret = false;
        try {
            // check if the token is correct
            User user = usersManagementPort.getUserFromToken(token);
            if (user != null) {
                float price = getPriceOf(token, id, quantity);
                // If the product exists
                if (productDAO.checkProductExists(id)) {
                    orderDAO.addOrder(new Order(0, user.getLogin(), price, false));
                    ret = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Gets the orders from a specified user
     *
     * @param token The token of the user
     * @return The list of the user's orders
     */
    @Override
    public List<Order> getOrders(String token) {
        List<Order> ret = new ArrayList<>();
        User user = usersManagementPort.getUserFromToken(token);
        if (user != null) {
            try {
                ret = orderDAO.getOrders(user.getLogin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Gets the orders from all users
     *
     * @param token The token of the admin
     * @return The list of all the orders
     */
    @Override
    public List<Order> getAllOrders(String token) {

        List<Order> ret = new ArrayList<>();
        try {
            if (usersManagementPort.userIsAdmin(token)) {
                ret = orderDAO.getAllOrders();
            }
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Product> getMenu(String token) {
        List<Product> ret = new ArrayList<>();
        try {
            if(usersManagementPort.getUserFromToken(token) != null) {
                ret = productDAO.getProducts();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void setPaid(String token, int id) {
        if(usersManagementPort.getUserFromToken(token) != null) {
            OrderDAO dao = new OrderDAO();
            dao.setPaid(id);
        }
    }

    @Override
    public void addProduct(String token, String name, float price) {
        try {
            if(usersManagementPort.getUserFromToken(token) != null) {
                productDAO.addProduct(new Product(0, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private float getPriceOf(String token, int id, int quantity) {
        for (Product p : this.getMenu(token)) {
            if (p.getId() ==id) {
                return p.getPrice()*quantity;
            }
        }
        return -1;
    }
}
