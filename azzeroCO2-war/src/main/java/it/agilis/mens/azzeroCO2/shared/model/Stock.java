package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/9/11
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */

public class Stock extends BaseModel {

  public Stock() {
  }

  public Stock(String name,  int busPiu50) {
    set("name", name);
    set("busPiu50", busPiu50);

  }

  public int getBusPiu50() {
    return (Integer) get("busPiu50");
  }


  public String getName() {
    return (String) get("name");
  }

  public String toString() {
    return getName();
  }

}
