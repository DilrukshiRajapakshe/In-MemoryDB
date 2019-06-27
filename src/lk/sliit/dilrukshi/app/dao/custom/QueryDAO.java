package lk.sliit.dilrukshi.app.dao.custom;

import lk.sliit.dilrukshi.app.dao.SuperDAO;
import lk.sliit.dilrukshi.app.entity.CustomEntity;

import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    Optional<List<CustomEntity>> findOrderDetailsWithItemDescriptions(String orderId) throws Exception;

    Optional<List<CustomEntity>> findAllOrdersWithCustomerNameAndTotal() throws Exception;

}
