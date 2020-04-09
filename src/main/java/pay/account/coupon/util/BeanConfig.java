package pay.account.coupon.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pay.account.coupon.controller.CouponCtrl;
import pay.account.coupon.database.MariaConnection;
import pay.account.coupon.database.dao.MariaConnectDAO;
import pay.account.coupon.database.dao.MariaConnectDAOImpl;
import pay.account.coupon.service.CouponService;
import pay.account.coupon.service.CouponServiceImpl;

@Configuration
public class BeanConfig {
    @Bean
    public MariaConnection mariaConnection() {
        return new MariaConnection();
    }

    @Bean
    public CouponCtrl couponCtrl() {
        return new CouponCtrl();
    }

    @Bean
    public CouponService couponService() {
        return new CouponServiceImpl();
    }

    @Bean
    public MariaConnectDAO mariaConnectDAO() {
        return MariaConnectDAOImpl.getInstance();
    }

}
