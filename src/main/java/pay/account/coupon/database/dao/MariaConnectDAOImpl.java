package pay.account.coupon.database.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pay.account.coupon.database.MariaConnection;
import pay.account.coupon.database.model.CouponModel;

import java.util.List;

/**
 * MariaDB Module
 * Create, Read, Update, Delete
 */
@Repository("MariaConnectDAO")
public class MariaConnectDAOImpl implements MariaConnectDAO {
    static MariaConnectDAOImpl connectModule = null;
    private static Logger logger = LoggerFactory.getLogger(MariaConnectDAOImpl.class);
    SessionFactory factory = MariaConnection.getSessionFactory();

    public static MariaConnectDAOImpl getInstance() {
        if (connectModule == null) {
            connectModule = new MariaConnectDAOImpl();
        }
        return connectModule;
    }

    @Override
    public void create(Object couponModel) {
        Transaction tx = null;
        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();
            session.save(couponModel);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        }
    }

    @Override
    public List<?> readList(String table, String type, String value) {
        List<?> list = null;
        Transaction tx = null;
        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();
            String sqlQuery = "";
            if (type == null && value == null) {
                sqlQuery = "from " + table;
                logger.debug("user sqlQuery is [{}]", sqlQuery);
            } else {
                sqlQuery = "from " + table + " where " + type + " = " + value;
                logger.debug("coupon sqlQuery is [{}]", sqlQuery);
            }
            Query query = session.createQuery(sqlQuery);
            list = query.getResultList();
            logger.debug("list data is [{}]", list);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        }
        return list;
    }

    @Override
    public CouponModel read(String number) {
        CouponModel model = null;
        Transaction tx = null;
        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();
            model = session.get(CouponModel.class, number);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        }
        return model;
    }

    @Override
    public void update(Object model) {
        Transaction tx = null;
        try (Session session = factory.getCurrentSession()) {
            CouponModel couponModel = (CouponModel) model;
            tx = session.beginTransaction();
            session.update(couponModel);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        }
    }
}
