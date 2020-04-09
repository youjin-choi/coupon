package pay.account.coupon.database.dao;

import java.util.List;

/**
 * MariaDB CRUD Interface
 */
public interface MariaConnectDAO {
    void create(Object object);

    List<?> readList(String table, String type, String value);

    Object read(String id);

    void update(Object object);

}