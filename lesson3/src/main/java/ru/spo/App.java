package ru.spo;

import javassist.tools.reflect.CannotCreateException;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entytyFactory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
        EntityManager entityManager = entytyFactory.createEntityManager();

        Client client = new Client();

        client.setName("Ivan");
        createEntity(entityManager, client);

        Client client1 = new Client();

        client1.setName("Igor");
        createEntity(entityManager, client1);
        Product milk = new Product();

        milk.setName("milk");
        createEntity(entityManager, milk);
        Product bread = new Product();

        bread.setName("bread");
        createEntity(entityManager, bread);
        List<Product> productList = new ArrayList<>();
        productList.add(milk);
        productList.add(bread);

        Order order = new Order();
        order.setClient(client);
        order.setProductList(productList);
        createEntity(entityManager, order);

        Order order1 = new Order();
        order1.setClient(client1);
        order1.setProductList(productList);
        createEntity(entityManager,order1);
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
}
