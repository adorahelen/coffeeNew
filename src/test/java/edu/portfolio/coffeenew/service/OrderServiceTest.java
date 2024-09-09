//package edu.portfolio.coffeenew.service;
//
//import edu.portfolio.coffeenew.entity.Category;
//import edu.portfolio.coffeenew.entity.Order;
//import edu.portfolio.coffeenew.entity.OrderItem;
//import edu.portfolio.coffeenew.entity.Product;
//import edu.portfolio.coffeenew.repository.OrderRepository;
//import edu.portfolio.coffeenew.repository.ProductRepository;
//import edu.portfolio.coffeenew.service.ProductNotFoundException;
//import edu.portfolio.coffeenew.controller.OrderItemRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class OrderServiceTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private OrderService orderService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateOrder() {
//        // Mock Product
//        Product mockProduct = new Product();
//        mockProduct.setProductId(1602L);
//        // Add other necessary product setup here
//
//        // Mock Product Repository
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));
//
//        // Create OrderItemRequest
//        OrderItemRequest itemRequest = new OrderItemRequest();
//        itemRequest.setProductId(1602L);
//        itemRequest.setCategory(Category.COFFEE_BEAN_PACKAGE); // 예시
//        itemRequest.setPrice(1000L);
//        itemRequest.setQuantity(2);
//
//        List<OrderItemRequest> itemRequests = List.of(itemRequest);
//
//        // Mock OrderRepository
//        Order mockOrder = new Order();
//        mockOrder.setOrderId(1L);
//        mockOrder.setEmail("test@example.com");
//        mockOrder.setAddress("123 Test Address");
//        mockOrder.setPostcode("12345");
//        // Initialize other necessary fields
//        mockOrder.setOrderItems(List.of(new OrderItem(mockOrder, mockProduct, Category.COFFEE_BEAN_PACKAGE, 1000L, 2)));
//
//        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
//
//        // Call the service method
//        Order createdOrder = orderService.createOrder(
//                "test@example.com",
//                "123 Test Address",
//                "12345",
//                itemRequests
//        );
//
//        // Verify interactions
//        verify(productRepository, times(1602)).findById(anyLong());
//        verify(orderRepository, times(1)).save(any(Order.class));
//
//        // Verify the results
//        assertEquals(mockOrder, createdOrder);
//        assertEquals(1, createdOrder.getOrderItems().size());
//        assertEquals(mockProduct, createdOrder.getOrderItems().get(0).getProduct());
//        assertEquals(Category.COFFEE_BEAN_PACKAGE, createdOrder.getOrderItems().get(0).getCategory());
//        assertEquals(1000L, createdOrder.getOrderItems().get(0).getPrice());
//        assertEquals(2, createdOrder.getOrderItems().get(0).getQuantity());
//    }
//}