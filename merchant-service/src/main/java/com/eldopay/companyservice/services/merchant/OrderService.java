package com.eldopay.companyservice.services.merchant;



import com.eldopay.companyservice.dtos.shop.OrderRequest;
import com.eldopay.companyservice.exceptions.OrderException;
import com.eldopay.companyservice.exceptions.UserException;
import com.eldopay.companyservice.models.Address;
import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.shop.Order;
import com.eldopay.companyservice.models.shop.OrderItem;
import com.eldopay.companyservice.repository.AddressRepository;
import com.eldopay.companyservice.repository.CustomerRepository;
import com.eldopay.companyservice.repository.merchant.OrderItemRepository;
import com.eldopay.companyservice.repository.merchant.OrderRepository;
import com.eldopay.companyservice.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService  {

    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CustomerRepository userRepository;
    private AddressRepository addressRepository;

    private UserService userService;

    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        //set payment status
        return orderRepository.save(order);
    }

    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        //set payment status
        return orderRepository.save(order);
    }

    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        //set payment status
        return orderRepository.save(order);
    }

    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return order;
    }

    public Order cancelledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        //set payment status
        return orderRepository.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long orderId) throws OrderException {
        Order order  = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }

    public Order createOrder(Long  userId, Address shippingAddress, OrderRequest orderRequest) throws UserException {
        Customer user =userService.findUserById(userId);
        shippingAddress.setCustomer(user);
        Address address = addressRepository.save(shippingAddress);
//        user.getAddress().add(address);
        userRepository.save(user);
        //cart implementation


        Order createdOrder = new Order();
        createdOrder.setDeliveryDate(orderRequest.getDeliveryDate());
        createdOrder.setTotalDiscountedPrice(orderRequest.getTotalDiscountedPrice());
        createdOrder.setOrderItems(orderRequest.getOrderItems());


        createdOrder.setOrderStatus("PENDING");
        createdOrder.setAddress(shippingAddress);
        //set payment status => PENDING
        createdOrder.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(createdOrder);

        for (OrderItem orderItem : orderRequest.getOrderItems()){
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }


        return orderRepository.save(createdOrder);
    }

    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById(orderId);
        if (opt.isPresent()){
            return  opt.get();
        }
        return throw new OrderException("order does not exist with id" +orderId);
    }


    public List<Order> userOrderHistory(Long userId) {
        List<Order> orders = orderRepository.findAll();
        var filteredOrders = orders.stream()
                .filter(order -> order.getCustomer().getId() == userId)
                .collect(Collectors.toList());
        return  filteredOrders;
    }



    public List<Order> merchantOrderHistory(Long merchantId) {
        List<Order> orders = orderRepository.findAll();
        var filteredOrders = orders.stream()
                .filter(order -> order.getMerchant().getId() == merchantId)
                .collect(Collectors.toList());
        return  filteredOrders;
    }

}
