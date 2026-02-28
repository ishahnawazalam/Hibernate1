// 2.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// ye class ko mapped krna hai
@Entity
public class UserClassHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // default constructor jruri hai. Hibernate constructor injection nahi karta, sirf no-arg constructor chahiye.
    // why ? qk hibernate jo hai reflextion ka use krta hai. Reflection means ke ye ek object phle bna leta hai jab application load hoti hai.
    // It sees ke entity kitni hai application mei(qk usse table bnani hai).To ye class ko phle hee load kar leta hai and uska object phle hee in-memory bna leta hai jiske liye usse ek default constructor ki jrurat parti hai
    public UserClassHibernate() {
    }


//    id set and pass krni ki jrurat nhi qk auto_increment wala annotation use kiye hai and
//    if use nhi krte hai wo annotation then pass krna hoga id constructor mei.
    public UserClassHibernate(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

/*
- Hibernate ko default (no-arg) constructor isliye chahiye kyunki woh reflection use krke entity ka object automatically bnata hai.
- Application start hote hi Hibernate saari entities ko scan krke unke in-memory objects bnata hai table mapping ke liye.
- Reflection object sirf no-arg constructor se hi bnana janta hai, isliye default constructor jruri hota hai.
*/
