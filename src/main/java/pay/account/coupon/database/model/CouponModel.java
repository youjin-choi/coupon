package pay.account.coupon.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * COUPON_INFO Database
 */

@Entity
@Table(name = "TBL_COUPON_INFO")
public class CouponModel implements Serializable {
    @Id
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "CREATE_TIME")
    private String create_time;
    @Column(name = "EXPIRE_TIME")
    private String expire_time;
    @Column(name = "STATE")
    private String state;
    @Column(name = "USER_ID")
    private String user_id;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
