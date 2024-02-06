package peaksoft.dao.impl;

import peaksoft.connection.DatabaseConnection;
import peaksoft.dao.CustomerDao;
import peaksoft.entitiy.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDaoImpl implements CustomerDao {
    private final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void createCustomer() {
        try {
            String query = """
                    create table if not exists customers(
                    id serial primary key,
                    first_name varchar,
                    last_name varchar,
                    phone_number varchar
                    );
                    """;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try {
            String query = """
                    insert into customers(first_name, last_name,phone_number)
                    values (?, ?, ?);
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customer.getFirstName());
                preparedStatement.setString(2, customer.getLastName());
                preparedStatement.setString(3, customer.getPhoneNumber());

                int executed = preparedStatement.executeUpdate();
                System.out.println(executed != 0 ? "Successfully saved!" : "Error" + executed);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        String query = "select * from customers where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                return customer;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCustomerPhone(Long id, String phone) {
        try {
            String query = """
                    update customers set phone_number = ? where id = ? ;""";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, phone);
                preparedStatement.setLong(2, id);
                int executed = preparedStatement.executeUpdate();
                System.out.println(executed != 0 ? "Successfully updated!" : "Error");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        String query = "delete  from customers where id = ? ;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, customerId);
            return preparedStatement.executeUpdate() != 0 ? "Successfully deleted!" : "error";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Customer> sortCustomersByFirstName() {
        List<Customer> employees = new ArrayList<>();
        try {
            String query = "select * from customers order by first_name ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("id"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setPhoneNumber(resultSet.getString("phone_number"));
                    employees.add(customer);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public Customer searchCustomerByName(String name) {
        try {
            String query = "select * from customers where first_name = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("id"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setPhoneNumber(resultSet.getString("phone_number"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
