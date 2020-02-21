package fr.ensibs.orders.service;

import fr.ensibs.orders.dao.OrderDAO;
import fr.ensibs.orders.dao.ProductDAO;
import fr.ensibs.orders.model.Order;
import fr.ensibs.orders.model.Product;
import fr.ensibs.users.model.User;
import fr.ensibs.users.service.UsersManagementServiceImpl;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Orders management service, to manage products and orders.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 * @see OrdersManagementService
 */
@WebService(endpointInterface = "fr.ensibs.orders.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {

    private UsersManagementServiceImpl usersManagementService = UsersManagementServiceImpl.getInstance();

    private static final OrdersManagementServiceImpl ordersManagementService = new OrdersManagementServiceImpl();

    public static final OrdersManagementServiceImpl getInstance(){
        return ordersManagementService;
    }

    /**
     * Order DAO, to perform actions on the order table
     */
    private static final OrderDAO orderDAO = new OrderDAO();


    /**
     * Product DAO, to perform actions on the product table
     */
    private static final ProductDAO productDAO = new ProductDAO();

    /**
     * Constructor
     */
    public OrdersManagementServiceImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addOrder(String token, int id, int quantity) {
        boolean ret = false;
        try {
            // check if the token is correct
            User user = usersManagementService.getUserFromToken(token);
            if (user != null) {
                float price = ordersManagementService.getPriceOf(token, id, quantity);
                // If the product exists
                if (productDAO.checkProductExists(id)) {
                    orderDAO.addOrder(new Order(user.getLogin(), price, false));
                    ret = true;
                }
            }
        } catch (Exception e) {
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
            User user = usersManagementService.getUserFromToken(token);
            if (user != null) {
                System.out.println(user.getLogin());
                ret = orderDAO.getOrders(user.getLogin());
            }
        } catch (Exception e) {
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
            if (usersManagementService.userIsAdmin(token)) {
                ret = orderDAO.getAllOrders();
            }
        } catch (Exception e) {
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
            if (usersManagementService.getUserFromToken(token) != null) {
                ret = productDAO.getProducts();
                System.out.println(ret.get(0).getId());
            }
        } catch (Exception e) {
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
            if (usersManagementService.getUserFromToken(token) != null) {
                OrderDAO dao = new OrderDAO();
                dao.setPaid(id);
            }
        } catch (Exception e) {
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
            if (usersManagementService.userIsAdmin(token)) {
                productDAO.addProduct(new Product(0, name, price));
                ret = true;
            }
        } catch (Exception e) {
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
        for (Product p : ordersManagementService.getMenu(token)) {
            System.out.println(p.getName()+" "+p.getPrice()+" "+p.getId());
            if (p.getId() == id) {
                return p.getPrice() * quantity;
            }
        }
        return -1;
    }
}
