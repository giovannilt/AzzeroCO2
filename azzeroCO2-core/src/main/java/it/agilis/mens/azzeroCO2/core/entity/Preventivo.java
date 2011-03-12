package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/11/11
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Preventivo {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany (fetch = FetchType.LAZY)
    private List<Calcolo> calcoli;

    @ManyToOne (fetch = FetchType.LAZY)
    private UserInfo userInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Calcolo> getCalcoli() {
        return calcoli;
    }

    public void setCalcoli(List<Calcolo> calcoli) {
        this.calcoli = calcoli;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
