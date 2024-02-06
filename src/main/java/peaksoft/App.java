package peaksoft;

import peaksoft.connection.DatabaseConnection;
import peaksoft.entitiy.Customer;
import peaksoft.service.CustomerService;
import peaksoft.service.impl.CustomerServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerServiceImpl();
//        System.out.println(DatabaseConnection.getConnection());
//        customerService.createCustomer();
//      Customer customer = new Customer("Gulumkan","Satybaeva","+996990550171");
//       customerService.saveCustomer(customer);
        //    System.out.println(customerService.getCustomerById(1L));
        //   customerService.updateCustomerPhone(1L,"+996708893068");
//        System.out.println(customerService.searchCustomerByName("Gulumkan"));
//        System.out.println(customerService.sortCustomersByFirstName());
         customerService.deleteCustomerById(1L);
    }
}
