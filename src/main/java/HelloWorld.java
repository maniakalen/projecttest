import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ManagedBean
@ApplicationScoped
public class HelloWorld {
    private DataSource dsc;

    public HelloWorld() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dsc = (DataSource)
                    envCtx.lookup("jdbc/DSTest");
        } catch (NamingException ex) {
            System.out.println("WelcomeBean instantiated");
        }
    }
    public String getMessage() {
        String data = "";
        try {
            Connection conn = this.dsc.getConnection();
            PreparedStatement s = conn.prepareStatement("SELECT * FROM users");
            s.execute();
            ResultSet set = s.getResultSet();
            while (set.next()) {
                data += set.getInt("id") + ":" + set.getString("userscol");
                data += "<br/>";
            }
        } catch (SQLException ex) {
            System.err.println("Catched");
        }
        return data;
    }
}