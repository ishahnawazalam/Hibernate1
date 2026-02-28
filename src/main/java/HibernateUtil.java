// 1.
// Ye class Hibernate ki session factory create kar dega.

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    // Note: Session factory ek class mei ek hee rakhte hai, qk heavy hota hai.

    // Now defining static block: Jab bhi ye class load hogi memory mei,then static block ke andar wala code execute ho jayega.
    // ye isiliye bnaya hai, qk jab bhi class load ho, then session factory build ya initialize ho jaye for our application
    // .addAnnotatedClass: Entity class ko register krne ke liye so that hibernate ko pta ho ki ye classes load hogi
    // Hibernate 6 se phle aise Session factory buid krte the
//    static {
//        try {
//            sessionFactory = new Configuration()
//                    .configure("hibernate.cfg.xml")
//                    .addAnnotatedClass()
//                    .buildSessionFactory();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    // After Hibernate 6 wala code ::
    static {
        try {
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure("hibernate.cfg.xml")
                            .build();

            Metadata metadata = new MetadataSources(registry)
                    .addAnnotatedClass(UserClassHibernate.class)   // your entity
                    .buildMetadata();

            sessionFactory = metadata.buildSessionFactory();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // session factory mei se Current session return kar dega ye methods
    public static Session getSessions() {
        return sessionFactory.getCurrentSession();
    }

    // closing the session
    public static void close() {
        sessionFactory.close();
    }
}

/*
- Before Hibernate 6 code ::
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(UserClassHibernate.class);

            sessionFactory = configuration.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // these two niche method "before hibernate 6" wala code ka part ka hissa nhi hai. ye sirf info ke liye hai
    public static Session getSessions() {
        return sessionFactory.openSession();
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
*/
