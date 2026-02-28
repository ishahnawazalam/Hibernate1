import org.hibernate.Session;

// 3.
// Main method ki help se session create krunga and then do DB related operations or interact with db.
public class HibernateMain {
    public static void main(String[] args) {

    Session session = HibernateUtil.getSessions();

     try{
         UserClassHibernate user = new UserClassHibernate("Alice");

        // Now session ki help se hame user object ko db mei krenge . Why session ?
        // Qk ye session ek connection hai to the database.
        // Transaction start kiya
         session.beginTransaction();
        // In hibernate, persist ki help se save krte hai db mei, JPA mei save(user) krte hai
         session.persist(user);
         session.getTransaction().commit();
         System.out.println("User saved: " + user.getId());
         System.out.println("User saved: " + user.getName());

     }
     catch (Exception e){
        e.printStackTrace();
     }
     finally {
         // this method close the session
         HibernateUtil.close();
     }


  }
}
