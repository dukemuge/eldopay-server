package com.eldopay.companyservice.controller.merchant;


import com.eldopay.companyservice.dtos.ApiResponse;
import com.eldopay.companyservice.dtos.shop.OrderRequest;
import com.eldopay.companyservice.exceptions.OrderException;
import com.eldopay.companyservice.exceptions.UserException;
import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.shop.Order;
import com.eldopay.companyservice.services.UserService;
import com.eldopay.companyservice.services.merchant.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class OrderController {
    private OrderService orderService;
    private UserService userService;

    public ResponseEntity<OrderResponse> createOrder(Address address, OrderRequest orderRequest,
                                                     @RequestHeader("Authorization") String jwt) throws UserException {
        Customer user =  userService.findUserProfileByToken(jwt);
        Order order = orderService.createOrder(user.getId(),address,orderRequest );
        OrderResponse orderResponse = new OrderResponse();


    }


    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrderHandler() {
        List<Order> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }


    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandler(
            @PathVariable long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrderHandler(
            @PathVariable long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> deliveredOrderHandler(
            @PathVariable long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrderHandler(
            @PathVariable long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.cancelledOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(
            @PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder(orderId);
        ApiResponse res = new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setSuccess(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>>  getOrdersByUserHandler(@PathVariable long userId){
        List<Order> orders = orderService.userOrderHistory(userId);
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<List<Order>>  getOrdersByMerchantHandler(@PathVariable long merchantId){
        List<Order> orders = orderService.merchantOrderHistory(merchantId);
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }
}
