import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class HelloWorld {
    public HelloWorld() {
        System.out.println("WelcomeBean instantiated");
    }
    public String getMessage() {
        return "I'm alive!";
    }
}