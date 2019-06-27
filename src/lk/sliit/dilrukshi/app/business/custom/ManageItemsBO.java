package lk.sliit.dilrukshi.app.business.custom;

import lk.sliit.dilrukshi.app.business.SuperBO;
import lk.sliit.dilrukshi.app.dto.ItemDTO;

import java.util.List;

public interface ManageItemsBO extends SuperBO {

    List<ItemDTO> getItems() throws Exception;

    boolean createItem(ItemDTO dto) throws Exception;

    boolean updateItem(ItemDTO dto) throws Exception;

    boolean deleteItem(String code) throws Exception;

    ItemDTO findItem(String itemCode) throws Exception;


}
