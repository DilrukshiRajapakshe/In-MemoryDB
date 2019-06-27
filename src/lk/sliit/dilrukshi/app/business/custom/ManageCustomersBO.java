package lk.sliit.dilrukshi.app.business.custom;

import lk.sliit.dilrukshi.app.business.SuperBO;
import lk.sliit.dilrukshi.app.dto.CustomerDTO;

import java.util.List;

public interface ManageCustomersBO extends SuperBO {

    List<CustomerDTO> getCustomers() throws Exception;

    boolean createCustomer(CustomerDTO dto) throws Exception;

    boolean updateCustomer(CustomerDTO dto) throws Exception;

    boolean deleteCustomer(String customerID) throws Exception;

    CustomerDTO findCustomer(String id) throws Exception;


}
