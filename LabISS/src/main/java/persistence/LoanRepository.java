package persistence;

import model.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


public class LoanRepository {

    static SessionFactory sessionFactory;

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

    public void addImprumut(Loan loan) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(loan);
            session.getTransaction().commit();
        }
    }

    public void removeImprumut(String isbn, String email) {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //session.delete(new Loan(Integer.parseInt(isbn), 0, null, email, 0, 0, 0));
                Query query = session.createQuery("delete Loan where isbn=:isbn and email=:email");
                query.setParameter("isbn", Integer.parseInt(isbn));
                query.setParameter("email", email);
                query.executeUpdate();
                tx.commit();
            }catch (RuntimeException e){
                e.printStackTrace();
                if(tx==null){
                    tx.rollback();
                }
            }
        }
    }
}
