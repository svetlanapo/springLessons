package ru.spo;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entytyFactory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();

        EntityManager entityManager = entytyFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String[] clientsArray = new String[]{"lisa", "olga", "ivan"};
        for (String name : clientsArray) {
            Client client = new Client(0, name);
            entityManager.persist(client);
        }

        Product[] productsArray = new Product[]{
                new Product(0, "bread", 50.2),
                new Product(0, "milk", 88.8),
                new Product(0, "sugar", 35.6),
                new Product(0, "tea", 65.8),
                new Product(0, "honey", 130.4),
        };

        for (Product product : productsArray) {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();

        List<Client> clients = entityManager.createQuery("SELECT client FROM Client client", Client.class).getResultList();
        List<Product> products = entityManager.createQuery("SELECT product FROM Product product", Product.class).getResultList();

        List<Product> productList = new ArrayList<>();
        productList.add(products.get(1));
        productList.add(products.get(2));
        productList.add(products.get(3));

        entityManager.getTransaction().begin();
        Order order = new Order(0, clients.get(0), productList);
        entityManager.persist(order);

        List<Product> productList2 = new ArrayList<>();
        productList2.add(products.get(4));
        productList2.add(products.get(0));
        productList2.add(products.get(3));

        Order order2 = new Order(0, clients.get(2), productList2);
        entityManager.persist(order2);
        entityManager.getTransaction().commit();
        List<Order> orders = entityManager.createQuery("SELECT order FROM Order order", Order.class).getResultList();
        clients = entityManager.createQuery("SELECT client FROM Client client", Client.class).getResultList();


        System.out.println("Orders list");

        for (Order ord : orders) {
            System.out.println("Order #" + ord.getId() + " client: " + ord.getClient().getName());
            for (int i = 0; i < ord.getProductList().size(); i++) {
                System.out.println(ord.getProductList().get(i).getName());
            }
        }
        System.out.println();


    }




    private static <T> void createEntity(EntityManager em, T entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");



    }

    private static <T> T readEntity(EntityManager em, Class<T> clazz, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        T person = em.find(clazz, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + person);
        return person;
    }

    private static <T> T saveEntity(EntityManager em, T entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);

        return savedEntity;
    }

    private static <T> void deleteEntity(EntityManager em, T entity) {
        System.out.println("Start removing");
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        System.out.println("Removing completed->");

    }
}
