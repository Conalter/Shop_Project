package db;

import models.Customer;
import models.Order;
import models.OrderQuantity;
import models.ShopStock;
import models.items.Item;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results){
                session.delete(result);
            }
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try {
            Criteria cr = session.createCriteria(classType);
            cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        return results;
    }

    public static <T> T find(int id, Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        try {
            Criteria cr = session.createCriteria(classType);
            cr.add(Restrictions.eq("id", id));
            result = (T)cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(result);
        return result;
    }

    public static void addItemToOrder(Item item, Order order, int quantity){
        OrderQuantity newOrderQuantity = new OrderQuantity(order, item, quantity);
        save(newOrderQuantity);
        item.addOrderQuantityEntry(newOrderQuantity);
        order.addOrderQuantityToOrderQuantity(newOrderQuantity);
        order.updatePrice(item.getPrice(), quantity);
        save(order);
        save(item);
    }

    public static void addItemToStock(Item item, int quantity){
        ShopStock newStock = new ShopStock(item, quantity);
        save(newStock);
        item.setStock(newStock);
        save(item);

    }

    public static List<Item> listAllItemsForOrder(Order order){
        List<Item> items = new ArrayList<Item>();

        List<OrderQuantity> quantities = listAllOrderQuantitiesForOrder(order);

        for(OrderQuantity quantity : quantities){
            Item newItem = showItemForOrderQuantity(quantity);
            items.add(newItem);
        }
        return items;
    }

    public static List<OrderQuantity> listAllOrderQuantitiesForOrder(Order order){
        session = HibernateUtil.getSessionFactory().openSession();
        List<OrderQuantity> orderQuantities = null;

        try {
            Criteria cr = session.createCriteria(OrderQuantity.class);
            cr.add(Restrictions.eq("order", order));
            orderQuantities = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderQuantities;
    }

    public static Item showItemForOrderQuantity(OrderQuantity orderQuantity){

        Item item = find(orderQuantity.getItem().getId(), Item.class);
        return item;
    }

    public static List<Order> listAllOrdersForCustomer(Customer customer){
        List<Order> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("customer", customer));
            results = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;

    }

    public static Order showCurrentOrder(Customer customer){
        Order order = null;

        List<Order> orders = listAllOrdersForCustomer(customer);

        for (Order eachOrder : orders){
            if (!eachOrder.getCompleteOrder()){
                return eachOrder;
            }
        }
        return order;
    }


}
