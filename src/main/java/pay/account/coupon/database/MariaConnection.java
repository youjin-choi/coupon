package pay.account.coupon.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pay.account.coupon.database.model.CouponModel;

/**
 * MariaDB Connection Main
 * Hibernate 사용
 */
public class MariaConnection {
    private static SessionFactory sessionFactory;
    private static String configFile = "hibernate.cfg.xml";
    private Logger logger = LoggerFactory.getLogger(MariaConnection.class);

    public MariaConnection() {
    }

    public static void main(String[] args) {
        MariaConnection mariaConnection = new MariaConnection();
        mariaConnection.start();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * MariaDB Connection Start
     *
     * @return
     */
    public void start() {
        try {
            Configuration cfg = new Configuration().configure(configFile);
            cfg.addAnnotatedClass(CouponModel.class);
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();
            sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
        } catch (Throwable th) {
            logger.error("Enitial SessionFactory creation failed", th);
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }

}
