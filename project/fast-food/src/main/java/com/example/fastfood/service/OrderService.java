package com.example.fastfood.service;

import com.example.fastfood.entity.*;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.OrderDetailRepository;
import com.example.fastfood.repository.OrderRepository;
import com.example.fastfood.request.OrderStatusRequest;
import com.example.fastfood.request.UserInforRequest;
import com.example.fastfood.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private VoucherService voucherService;

    public Order save(UserInforRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setName(request.getName());
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setNote(request.getNote());
        order.setStatus(Order.Status.CHO_XAC_NHAN);
        order.setQuantity(shoppingCart.getTotalItem());
        order.setDiscountValue(request.getDiscountValue());
        order.setVoucherId(request.getVoucherId());
        order.setProvine(request.getProvince());
        order.setDistrict(request.getDistrict());
        order.setWard(request.getWard());
        order.setUser(user);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(ShoppingCartDetail s : shoppingCart.getShoppingCartDetailList()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(s.getProduct());
            orderDetail.setSize(s.getSize());
            orderDetail.setToppingId(s.getToppingId());
            orderDetail.setToppingName(s.getToppingName());
            orderDetail.setOrder(order);
            orderDetail.setQuantity(s.getQuantity());
            orderDetail.setPrice(s.getUnitPrice());
            orderDetailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);

        }
        order.setOrderDetailList(orderDetailList);
        shoppingCartService.deleteCartById(shoppingCart.getId());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    public Page<Order> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("orderDate").descending());
        return orderRepository.findAll(pageable);
    }

    public Page<Order> findAllByUser(Integer page, Integer limit){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
//        return orderRepository.findByUser_IdOrderByOrderDateDesc(user.getId());
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("orderDate").descending());
        return orderRepository.findByUser(user,pageable);
    }
    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new NotFoundException("không tìm thấy order này"));

    }

    public Object updateStatus(OrderStatusRequest request){
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("Không tìm thấy order"));
        Order.Status status;
        switch (request.getStatus()) {
            case "Đang đóng gói" -> status = Order.Status.DANG_DONG_GOI;
            case "Đang giao hàng" -> status = Order.Status.DANG_GIAO_HANG;
            case "Chờ xác nhận" -> status = Order.Status.CHO_XAC_NHAN;
            case "Đã giao" -> status = Order.Status.DA_GIAO;
            case "Hủy đơn" -> status = Order.Status.HUY_DON;
            default -> {
                return new BadRequestException("yêu cầu không đúng");
            }
        }
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }
    public Order updateStatusByUser(OrderStatusRequest request){
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("Không tìm thấy order"));
        order.setStatus(Order.Status.HUY_DON);
        orderRepository.save(order);
        return order;
    }

    public List<Order> findOrderByStatusValue(){
        return orderRepository.findByStatus(Order.Status.DA_GIAO);
    }
}
