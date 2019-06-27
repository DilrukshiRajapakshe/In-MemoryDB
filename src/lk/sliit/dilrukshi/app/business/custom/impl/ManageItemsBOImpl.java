package lk.sliit.dilrukshi.app.business.custom.impl;

import lk.sliit.dilrukshi.app.business.Converter;
import lk.sliit.dilrukshi.app.business.custom.ManageItemsBO;
import lk.sliit.dilrukshi.app.dao.DAOFactory;
import lk.sliit.dilrukshi.app.dao.custom.ItemDAO;
import lk.sliit.dilrukshi.app.dto.ItemDTO;

import java.util.List;

public class ManageItemsBOImpl implements ManageItemsBO {

    private ItemDAO itemDAO;

    public ManageItemsBOImpl() {
        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    }

    public List<ItemDTO> getItems() throws Exception {
        return itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
    }

    public boolean createItem(ItemDTO dto) throws Exception {
        return itemDAO.save(Converter.getEntity(dto));
    }

    public boolean updateItem(ItemDTO dto) throws Exception {
        return itemDAO.update(Converter.getEntity(dto));
    }

    public boolean deleteItem(String code) throws Exception {
        return itemDAO.delete(code);

    }

    public ItemDTO findItem(String itemCode) throws Exception {
        return itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
    }
}
