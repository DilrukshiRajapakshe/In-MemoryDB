import lk.sliit.dilrukshi.app.business.Converter;
import lk.sliit.dilrukshi.app.dto.CustomerDTO;
import lk.sliit.dilrukshi.app.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class TestClass5 {

    public static void main(String[] args) {

        List<Customer> c = new ArrayList<>();
//        c.add(new Customer());
        List<CustomerDTO> dtoList = Converter.<CustomerDTO>getDTOList(c);
        System.out.println(dtoList);
    }

}
