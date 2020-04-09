package pay.account.coupon.service;

/**
 * Coupon CRUD Service Interface
 */
public interface CouponService {
    void create(int num);

    String issued();

    void list();

    void used(String number);

    void cancel(String number);

    void expire();
}
