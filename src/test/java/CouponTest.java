import org.junit.Test;
import pay.account.coupon.database.model.CouponModel;
import pay.account.coupon.database.util.coupon.CouponState;
import pay.account.coupon.util.BeanConfig;
import pay.account.coupon.util.CouponCreateUtil;

import static org.junit.Assert.assertNull;

/**
 * Coupon Ctrl Test
 */
public class CouponTest extends BeanConfig {
    /**
     * 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관
     */
    @Test
    public void testA() {
        mariaConnection().start();
        couponCtrl().create(10);
    }

    /**
     * 생성된 쿠폰중 하나를 사용자에게 지급
     */
    @Test
    public void testB() {
        assertNull(couponCtrl().issued());
    }

    /**
     * 사용자에게 지급된 쿠폰을 조회
     */
    @Test
    public void testC() {
        couponCtrl().list();
    }

    /**
     * 지급된 쿠폰중 하나를 사용
     */
    @Test
    public void testD() {
        CouponModel couponModel = new CouponModel();
        String num = "12345-ASDFGGGG-QWEKF12";
        couponModel.setNumber(num);
        couponModel.setCreate_time(CouponCreateUtil.createDate());
        couponModel.setExpire_time(CouponCreateUtil.expireDate());
        couponModel.setState(CouponState.AVAILABLE.getValue());
        mariaConnectDAO().create(couponModel);
        couponCtrl().used(num);
    }

    @Test
    public void testE() {
        String num = "12345-ASDFGGGG-QWEKF12";
        couponCtrl().cancel(num);
    }

    @Test
    public void testF() {
        couponCtrl().expire();
    }


}
