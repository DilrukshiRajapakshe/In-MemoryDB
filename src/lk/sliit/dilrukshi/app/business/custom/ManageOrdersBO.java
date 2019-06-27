package lk.sliit.dilrukshi.app.business.custom;

import lk.sliit.dilrukshi.app.business.SuperBO;
import lk.sliit.dilrukshi.app.dto.OrderDTO;
import lk.sliit.dilrukshi.app.dto.OrderDTO2;

import java.util.List;

public interface ManageOrdersBO extends SuperBO {

    List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws Exception;

    List<OrderDTO> getOrders() throws Exception;

    String generateOrderId() throws Exception;

    void createOrder(OrderDTO dto) throws Exception;

    OrderDTO findOrder(String orderId) throws Exception;

}
