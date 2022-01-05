//package com.order.services.users;
//
//import com.order.domain.users.Address;
//import com.order.domain.users.Customer;
//import com.order.exceptions.ObjectAlreadyExistException;
//import com.order.exceptions.ObjectDoesNotExist;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.InOrder;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//
//@DisplayName("Customer service unit test")
//class CustomerServiceDbUnitTest {
//
//    private CustomerRepositoryDb customerRepositoryMock;
//    private CustomerServiceDb customerServiceDb;
//    private Customer testCustomer;
//    private final List<Customer> customerList = new ArrayList<>();
//    private int numberOfCustomers = 0;
//
//    @BeforeEach
//    void setUp() {
//        customerRepositoryMock = Mockito.mock(CustomerRepositoryDb.class);
//        customerServiceDb = new CustomerServiceDb(customerRepositoryMock);
//        testCustomer = createNewCustomer();
//        createNewCustomer();
//        createNewCustomer();
//    }
//
//
//    @Nested
//    @DisplayName("Adding a customer")
//    class AddingCustomer {
//        @Test
//        @DisplayName("Mocking/ When adding a customer, test if method <findAll()> and <save()> is called from repository")
//        void mock_whenAddCustomer_save_IsCalledInRepository() {
//            customerServiceDb.addCustomer(testCustomer);
//
//            InOrder expectedExecutionFlow = Mockito.inOrder(customerRepositoryMock);
//            expectedExecutionFlow.verify(customerRepositoryMock).findAll();
//            expectedExecutionFlow.verify(customerRepositoryMock).save(testCustomer);
//        }
//
//        @Test
//        @DisplayName("Stubbing, Exception/ When adding a customer who already exist, test if we get correct exception")
//        void stub_whenAddCustomer_thatAlreadyExist_GiveCorrectException() {
//            Mockito.when(customerRepositoryMock.findAll()).thenReturn(customerList);
//            String expectedMessage = "Customer " + testCustomer + " already exist.";
//
//            try {
//                customerServiceDb.addCustomer(testCustomer);
//                customerServiceDb.addCustomer(testCustomer);
//                fail("Customer service did not prevent implementing te same customer");
//
//            } catch (ObjectAlreadyExistException exception) {
//                Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
//
//            } catch (Exception exception) {
//                fail("Wrong exception" + exception.getMessage());
//            }
//        }
//
//        @Test
//        @DisplayName("Stubbing/ When adding a customer, test if we get the correct customer back")
//        void stub_whenAddingCustomer_stubbed_customerIsReturned() {
//            Mockito.when(customerRepositoryMock.save(testCustomer)).thenReturn(testCustomer);
//
//            Customer actualCustomer = customerServiceDb.addCustomer(testCustomer);
//
//            Assertions.assertThat(actualCustomer).isEqualTo(testCustomer);
//        }
//    }
//
//    @Nested
//    @DisplayName("Getting customer by email")
//    class GettingCustomersByEmail {
//        private String email;
//
//        @BeforeEach
//        void setUp() {
//            email = "testEmail";
//        }
//
//        @Test
//        @DisplayName("Mocking/ When getting customer by email, test if method <findAll()> is called from repository")
//        void mock_whenGetCustomersByEmail_findAll_IsCalledInRepository() {
//            customerServiceDb.getCustomersByEmail(email);
//
//            Mockito.verify(customerRepositoryMock).findAll();
//        }
//
//        @Test
//        @DisplayName("Stubbing/ When getting customer by email, test if we get the correct customer back")
//        void stub_WhenGetCustomersByEmail_correctEmailReceived() {
//            Mockito.when(customerRepositoryMock.findAll()).thenReturn(customerList);
//
//            var expectedList = List.of(customerList.get(1));
//            var actualList = customerServiceDb.getCustomersByEmail("jfk@outlook.com1");
//
//            Assertions.assertThat(actualList).isEqualTo(expectedList);
//        }
//
//        @Test
//        @DisplayName("Stubbing/ When getting customer by non existing email, test if we get empty list back")
//        void stub_WhenGetCustomersByEmail_NonExistingEmail_gives_EmptyList() {
//            var actualList = customerServiceDb.getCustomersByEmail("Non existing email");
//
//            Assertions.assertThat(actualList).isEmpty();
//        }
//    }
//
//    @Nested
//    @DisplayName("Getting all customers")
//    class GettingCustomers {
//
//
//        @Test
//        @DisplayName("Mocking/ When getting all customers, test if method <findAll()> is called from repository")
//        void mock_WhenGettingCustomers_FindAll_IsCalledInRepository() {
//            customerServiceDb.getCustomers();
//
//            Mockito.verify(customerRepositoryMock).findAll();
//        }
//
//        @Test
//        @DisplayName("Stubbing/ When getting all customers, test all customers are returned")
//        void stub_WhenGettingCustomers_GetCorrectCustomersBack() {
//            Mockito.when(customerRepositoryMock.findAll()).thenReturn(customerList);
//            var actualList = customerServiceDb.getCustomers();
//
//            Assertions.assertThat(actualList).isEqualTo(customerList);
//        }
//    }
//
//    @Nested
//    @DisplayName("Getting CustomerById")
//    class GettingCustomerByID {
//        @Test
//        @DisplayName("Mocking/ When getting customer by id, test if <findAll()> is called form repository")
//        void mock_WhenGettingCustomerById_FindAll_isCalledInRepository() {
//            Customer expectedCostumer = customerList.get(0);
//            Mockito.when(customerRepositoryMock.findById(expectedCostumer.getUniqueId())).thenReturn(java.util.Optional.of(expectedCostumer));
//
//            customerServiceDb.getCustomerById(expectedCostumer.getUniqueId());
//
//            Mockito.verify(customerRepositoryMock).findById(expectedCostumer.getUniqueId());
//        }
//
//        @Test
//        @DisplayName("Stubbing/ When getting customer by existing id, test if we get the correct customer back")
//        void stub_WhenGettingCustomerByExistingIdTestIfWeGetTheCorrectCustomerBack() {
//            String expectedMessage = "Customer with id: non ExistingId, does not exist in our database.";
//            try {
//                customerServiceDb.getCustomerById("non ExistingId");
//                fail();
//            } catch (ObjectDoesNotExist exception) {
//                Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
//
//            } catch (Exception exception) {
//                fail();
//            }
//        }
//    }
//
//    private Customer createNewCustomer() {
//        Address address = new Address("SesameStreet", Integer.toString(numberOfCustomers), "3000", "Leuven");
//        Customer customer = new Customer("Jhon", "Fitzgerald Kennedy", "jfk@outlook.com" + numberOfCustomers, address, "+1487565478");
//        testCustomer = customer;
//        numberOfCustomers++;
//        customerList.add(customer);
//        return customer;
//    }
//}