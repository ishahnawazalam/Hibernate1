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
    // why ? qk hibernate jo hai reflection ka use krta hai. Reflection means ke ye ek object phle bna leta hai jab application load hoti hai.
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
    // constructor use kar rhe hai isiliye setter use nhi kiye
}

/*
- Hibernate ko default (no-arg) constructor isliye chahiye kyunki woh reflection use krke entity ka object automatically bnata hai.
- Application start hote hi Hibernate saari entities ko scan krke unke in-memory objects bnata hai table mapping ke liye.
- Reflection object sirf no-arg constructor se hi bnana janta hai, isliye default constructor jruri hota hai.
*/

/*
-  Why Hibernate ko default constructor chahiye?
Hibernate entity object ko create karta hai reflection ke through, aur uske liye default (no-arg) constructor required hota hai.

- Simple Samjho
Hibernate internally aisa karta hai:
                User user = User.class.getDeclaredConstructor().newInstance();

- Ye tabhi possible hai jab:
                public User() {}
exist kare.

Important Rule
  - Default constructor public ya protected hona chahiye
  - Private bhi ho sakta hai (Hibernate use kar lega), but best practice public/protected
 */

/*
- Reflection Java ka feature hai jisse hum runtime pe class, methods, fields ko inspect aur manipulate kar sakte hain — bina direct access ke.

- Reflection kya kya kar sakta hai?
  - Object create karna (constructor call)
  - Private fields access karna
  - Methods call karna dynamically
  - Class metadata read karna
 */
