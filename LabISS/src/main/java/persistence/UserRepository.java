package persistence;


import model.Book;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class UserRepository implements Repository{
    private static SessionFactory sessionFactory;

    public UserRepository() {
    }

    static void initialize(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            System.err.println("Exception:"+e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

    public User getAll(){
        initialize();
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //session.save(new Ticket("ClientName", "Destination", "Airport", LocalDateTime.now(), names, "Address"));
            session.save(new User("1", "a", "a", "010000", "a"));
            session.getTransaction().commit();
        }
        return user;
    }

    public User findByEmailPassword(String cnp, String password) {
        Transaction tx = null;
        initialize();
        try (Session session = sessionFactory.openSession()) {
            try {
                tx = session.beginTransaction();
                Query<User> query = session.createQuery("from User where cnp = :cnp and parola=:parola", User.class);
                query.setParameter("cnp", cnp);
                query.setParameter("parola", password);
                User result = query.uniqueResult();
                tx.commit();
                return result;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la find by email and password " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        close();
        return null;
    }
}
