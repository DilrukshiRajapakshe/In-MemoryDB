package lk.sliit.dilrukshi.app.dao.custom;

import lk.sliit.dilrukshi.app.dao.CrudDAO;
import lk.sliit.dilrukshi.app.entity.Order;

public interface OrderDAO extends CrudDAO<Order, String> {

    int count() throws Exception;

}
