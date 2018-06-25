package cn.aouo.sys.sec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by liu.w on 2017/8/31.
 */
public class AppUser extends User {

    private String userId; //用户唯一标识
    private String phone;  //手机号
    private String email;  //邮件地址



    private String coreCustomerNo;//核心客户号
//    private String unionId;//微信id


    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean locked) {
        super(username, password, true, true, true, !locked, authorities);
//        super(username, password, authorities);
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getUnionId() {
//        return unionId;
//    }
//
//    public void setUnionId(String unionId) {
//        this.unionId = unionId;
//    }
    public String getCoreCustomerNo() {
        return coreCustomerNo;
    }

    public void setCoreCustomerNo(String coreCustomerNo) {
        this.coreCustomerNo = coreCustomerNo;
    }
}
