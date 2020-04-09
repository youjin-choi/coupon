package pay.account.coupon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pay.account.coupon.util.BeanConfig;

/**
 * 쿠폰 CRUD Controller
 */
@Component
public class CouponCtrl extends BeanConfig {
    private Logger logger = LoggerFactory.getLogger(CouponCtrl.class);

    @PostMapping("/coupon/create/{num}")
    public void create(@PathVariable("num") int num) {
        couponService().create(num);
    }

    @GetMapping("/coupon/issue")
    public String issued() {
        return couponService().issued();
    }

    @GetMapping("/coupon/list")
    public void list() {
        couponService().list();
    }

    @PostMapping("/coupon/use/{num}")
    public void used(@PathVariable("num") String num) {
        couponService().used(num);
    }

    @GetMapping("/coupon/cancel/{num}")
    public void cancel(@PathVariable("num") String num) {
        couponService().cancel(num);
    }

    @GetMapping("/coupon/expire")
    public void expire() {
        couponService().expire();
    }

}
