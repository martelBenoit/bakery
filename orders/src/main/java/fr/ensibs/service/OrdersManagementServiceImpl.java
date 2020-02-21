package fr.ensibs.service;

import fr.ensibs.dao.OrderDAO;
import fr.ensibs.dao.ProductDAO;
import fr.ensibs.model.Order;
import fr.ensibs.model.Product;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orders management service, to manage products and orders.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 * @see OrdersManagementService
 */
@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {

    /**
     * Users Management Service, to check tokens and get logins
     */
    protected UsersManagementService usersManagementPort;

    /**
     * Order DAO, to perform actions on the order table
     */
    private OrderDAO orderDAO;

    /**
     * Product DAO, to perform actions on the product table
     */
    private ProductDAO productDAO;

    /**
     * Constructor
     */
    public OrdersManagementServiceImpl() {
        UsersManagementService_Service usersManagementService = new UsersManagementService_Service();
        usersManagementPort = usersManagementService.getUsersManagementPort();
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }

    /**
     * {@inheritDoc}
     */
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
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getOrders(String token) {
        List<Order> ret = new ArrayList<>();
        try {
            User user = usersManagementPort.getUserFromToken(token);
            if (user != null) {
                ret = orderDAO.getOrders(user.getLogin());
            }
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getMenu(String token) {
        List<Product> ret = new ArrayList<>();
        try {
            if (usersManagementPort.getUserFromToken(token) != null) {
                ret = productDAO.getProducts();
            }
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaid(String token, int id) {
        try {
            if (usersManagementPort.getUserFromToken(token) != null) {
                OrderDAO dao = new OrderDAO();
                dao.setPaid(id);
            }
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addProduct(String token, String name, float price) {
        boolean ret = false;
        try {
            if (usersManagementPort.userIsAdmin(token)) {
                productDAO.addProduct(new Product(0, name, price));
                ret = true;
            }
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Gets the price of a product with a quantity
     *
     * @param token    The user token
     * @param id       The product id
     * @param quantity The quantity of products
     * @return The total price
     */
    private float getPriceOf(String token, int id, int quantity) {
        for (Product p : this.getMenu(token)) {
            if (p.getId() == id) {
                return p.getPrice() * quantity;
            }
        }
        return -1;
    }
}
