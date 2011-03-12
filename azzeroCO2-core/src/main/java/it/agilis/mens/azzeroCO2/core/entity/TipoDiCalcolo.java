package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/11/11
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class TipoDiCalcolo {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Calcolo> calcoli;


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
}
