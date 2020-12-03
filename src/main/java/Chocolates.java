import org.jdbi.v3.core.Jdbi;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Test {
}

public class Chocolates {
    Map<String, Integer> chocolate = new HashMap<>();
    private Jdbi connection;
    //   private Object Chocolates;

    public Chocolates(Jdbi connection) {
        this.connection = connection;
    }

    public void setChocolate(String chocolateName, int qyt) {
        connection.withHandle(handle -> handle.createQuery("INSERT INTO chocolate (name,qty) values (?,?)")
                .bind(0, chocolateName)
                .bind(1, qyt)
        );
    }

    public Object getChocolate() throws Exception {
        Object a = connection.withHandle(
                handle -> handle.createQuery("SELECT * FROM chocolate").mapToMap()
                        .list());
        return a;

    }

    public void addChocolate(String chocolateName, int qty) {
        connection.withHandle(
                handle -> handle.createUpdate("INSERT INTO chocolate (name,qty) values (?,?)")
                        .bind(0, chocolateName)
                        .bind(1, qty)
                        .execute()
        );
        System.out.println("test");
    }

    public void deleteChocolate(String chocolateName, int qty) {
        connection.withHandle(
                handle -> handle.createQuery("delete from chocolate where name = ? and qty = ?")
                        .bind(0, chocolateName)
                        .bind(1, qty)

        );
    }

    public static void main(String[] args) throws Exception {
        Chocolates chocolates = new Chocolates(DatabaseConnection.getDatabaseConnection());
        chocolates.addChocolate("DarkChocolate", 7);
        chocolates.deleteChocolate("WhiteChocolate", 7);
        chocolates.deleteChocolate("White Chocolate", 2);
        chocolates.deleteChocolate("RawChocolate", 10);
        System.out.println(chocolates.getChocolate());
    }
}
