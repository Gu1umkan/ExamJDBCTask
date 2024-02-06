package peaksoft.dao;

import peaksoft.entitiy.Customer;

import java.util.List;

public interface CustomerDao {
    void createCustomer();
    void saveCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    void updateCustomerPhone(Long id,String phone);
    String deleteCustomerById(Long customerId);
    List<Customer> sortCustomersByFirstName();
    Customer searchCustomerByName(String name);

}
