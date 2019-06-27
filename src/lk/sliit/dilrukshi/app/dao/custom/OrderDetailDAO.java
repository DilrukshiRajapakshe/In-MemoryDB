package lk.sliit.dilrukshi.app.dao.custom;

import lk.sliit.dilrukshi.app.dao.CrudDAO;
import lk.sliit.dilrukshi.app.entity.OrderDetail;
import lk.sliit.dilrukshi.app.entity.OrderDetailPK;

import java.util.List;
import java.util.Optional;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {

    Optional<List<OrderDetail>> find(String orderId) throws Exception;

}
