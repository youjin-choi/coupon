package pay.account.coupon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pay.account.coupon.database.model.CouponModel;
import pay.account.coupon.database.model.UserModel;
import pay.account.coupon.database.util.coupon.CouponState;
import pay.account.coupon.util.BeanConfig;
import pay.account.coupon.util.CouponCreateUtil;

import java.util.List;

/**
 * Coupon CRUD Service
 */
@Configuration
public class CouponServiceImpl extends BeanConfig implements CouponService {
    private static String TBL_COUPON_INFO = "TBL_COUPON_INFO";
    private static String TBL_USER_INFO = "TBL_USER_INFO";
    private static String STATE = "STATE";
    @Autowired
    CouponModel couponModel;
    @Autowired
    UserModel userModel;
    private Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Override
    @Bean
    public void create(int num) {
        for (int i = 0; i < num; i++) {
            couponModel = new CouponModel();
            couponModel.setNumber(CouponCreateUtil.createRandomNum(19));
            couponModel.setCreate_time(CouponCreateUtil.createDate());
            couponModel.setExpire_time(CouponCreateUtil.expireDate());
            couponModel.setState(CouponState.AVAILABLE.getValue());
            mariaConnectDAO().create(couponModel);
        }
    }

    @Override
    public String issued() {
        List<?> userList = mariaConnectDAO().readList(TBL_USER_INFO,null,null);
        List<?> couponList = mariaConnectDAO().readList(TBL_COUPON_INFO, STATE, CouponState.AVAILABLE.getValue());
        if (userList == null || couponList == null)
            return "ISSUED DATA IS NULL";

        loop:
        for (Object user : userList) {
            userModel = (UserModel) user;
            for (Object coupon : couponList) {
                couponModel = (CouponModel) coupon;
                couponModel.setUser_id(userModel.getName());
                couponModel.setState(CouponState.ISSUED.getValue());
                mariaConnectDAO().update(couponModel);
                break loop;
            }
        }

        logger.debug("coupon number [{}]", couponModel.getNumber());
        return couponModel.getNumber();

    }

    @Override
    public void list() {
        mariaConnectDAO().readList(TBL_COUPON_INFO, STATE, CouponState.AVAILABLE.getValue());
    }

    @Override
    public void used(String number) {
        couponModel = (CouponModel) mariaConnectDAO().read(number);
        if (couponModel == null)
            throw new NullPointerException();

        if (couponModel.getState().equals(CouponState.AVAILABLE.getValue()) || couponModel.getState().equals(CouponState.CANCEL.getValue())) {
            couponModel.setState(CouponState.USED.getValue());
            mariaConnectDAO().update(couponModel);
        }
    }

    @Override
    public void cancel(String number) {
        couponModel = (CouponModel) mariaConnectDAO().read(number);
        if (couponModel == null)
            throw new NullPointerException();

        if (couponModel.getState().equals(CouponState.USED.getValue())) {
            couponModel.setState(CouponState.CANCEL.getValue());
            mariaConnectDAO().update(couponModel);
        }
    }

    @Override
    public void expire() {
        mariaConnectDAO().readList(TBL_COUPON_INFO, STATE, CouponState.EXPIRE.getValue());
    }


}
