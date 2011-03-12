package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    @OneToMany (fetch = FetchType.LAZY)
    private List<Preventivo> preventivi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Preventivo> getPreventivi() {
        return preventivi;
    }

    public void setPreventivi(List<Preventivo> preventivi) {
        this.preventivi = preventivi;
    }
}
