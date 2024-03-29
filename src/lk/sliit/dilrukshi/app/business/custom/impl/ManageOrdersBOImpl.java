package lk.sliit.dilrukshi.app.business.custom.impl;

import lk.sliit.dilrukshi.app.business.Converter;
import lk.sliit.dilrukshi.app.business.custom.ManageOrdersBO;
import lk.sliit.dilrukshi.app.dao.DAOFactory;
import lk.sliit.dilrukshi.app.dao.custom.ItemDAO;
import lk.sliit.dilrukshi.app.dao.custom.OrderDAO;
import lk.sliit.dilrukshi.app.dao.custom.OrderDetailDAO;
import lk.sliit.dilrukshi.app.dao.custom.QueryDAO;
import lk.sliit.dilrukshi.app.db.DBConnection;
import lk.sliit.dilrukshi.app.dto.OrderDTO;
import lk.sliit.dilrukshi.app.dto.OrderDTO2;
import lk.sliit.dilrukshi.app.dto.OrderDetailDTO;
import lk.sliit.dilrukshi.app.entity.Item;
import lk.sliit.dilrukshi.app.entity.Order;
import lk.sliit.dilrukshi.app.entity.OrderDetail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ManageOrdersBOImpl implements ManageOrdersBO {

    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private ItemDAO itemDAO;
    private QueryDAO queryDAO;

    public ManageOrdersBOImpl() {
        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    public List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws Exception {

        return queryDAO.findAllOrdersWithCustomerNameAndTotal().map(ce -> {
            return Converter.getDTOList(ce, OrderDTO2.class);
        }).get();

    }

    public List<OrderDTO> getOrders() throws Exception {

        List<Order> orders = orderDAO.findAll().get();
        ArrayList<OrderDTO> tmpDTOs = new ArrayList<>();

        for (Order order : orders) {
            List<OrderDetailDTO> tmpOrderDetailsDtos = queryDAO.findOrderDetailsWithItemDescriptions(order.getId()).map(ce -> {
                return Converter.getDTOList(ce, OrderDetailDTO.class);
            }).get();

            OrderDTO dto = new OrderDTO(order.getId(),
                    order.getDate().toLocalDate(),
                    order.getCustomerId(), tmpOrderDetailsDtos);
            tmpDTOs.add(dto);
        }

        return tmpDTOs;
    }

    public String generateOrderId() throws Exception {
        return orderDAO.count() + 1 + "";
    }

    public void createOrder(OrderDTO dto) throws Exception {

        DBConnection.getConnection().setAutoCommit(false);

        try {

            boolean result = orderDAO.save(new Order(dto.getId(), Date.valueOf(dto.getDate()), dto.getCustomerId()));

            if (!result) {
                return;
            }

            for (OrderDetailDTO detailDTO : dto.getOrderDetailDTOS()) {
                result = orderDetailDAO.save(new OrderDetail(dto.getId(),
                        detailDTO.getCode(), detailDTO.getQty(), detailDTO.getUnitPrice()));

                if (!result) {
                    DBConnection.getConnection().rollback();
                    return;
                }

                Item item = itemDAO.find(detailDTO.getCode()).get();
                int qty = item.getQtyOnHand() - detailDTO.getQty();
                item.setQtyOnHand(qty);
                itemDAO.update(item);

            }

            DBConnection.getConnection().commit();

        } catch (Exception ex) {
            DBConnection.getConnection().rollback();
            ex.printStackTrace();
        } finally {
            DBConnection.getConnection().setAutoCommit(true);
        }

    }

    public OrderDTO findOrder(String orderId) throws Exception {
        Order order = orderDAO.find(orderId).get();

        List<OrderDetailDTO> tmpOrderDetailsDtos = queryDAO.findOrderDetailsWithItemDescriptions(order.getId()).map(ce -> {
            return Converter.getDTOList(ce, OrderDetailDTO.class);
        }).get();

        return new OrderDTO(order.getId(), order.getDate().toLocalDate(), order.getCustomerId(), tmpOrderDetailsDtos);
    }
}
