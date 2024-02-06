package peaksoft.service.impl;

import peaksoft.dao.CustomerDao;
import peaksoft.dao.impl.CustomerDaoImpl;
import peaksoft.entitiy.Customer;
import peaksoft.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public void createCustomer() {
        customerDao.createCustomer();
    }

    @Override
    public void saveCustomer(Customer customer) {
      customerDao.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public void updateCustomerPhone(Long id, String phone) {
    customerDao.updateCustomerPhone(id,phone);
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        return customerDao.deleteCustomerById(customerId);
    }

    @Override
    public List<Customer> sortCustomersByFirstName() {
        return customerDao.sortCustomersByFirstName();
    }

    @Override
    public Customer searchCustomerByName(String name) {
        return customerDao.searchCustomerByName(name);
    }
}
