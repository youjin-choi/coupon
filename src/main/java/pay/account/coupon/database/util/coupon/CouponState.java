package pay.account.coupon.database.util.coupon;

/**
 * Coupon State Type
 * 유효, 발급, 취소, 만료
 */
public enum CouponState {
    AVAILABLE("AVAILABLE"), ISSUED("ISSUED"), USED("USED"), CANCEL("CANCEL"), EXPIRE("EXPIRE");

    private String value;

    CouponState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
