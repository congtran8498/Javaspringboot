package com.example.fastfood.service;

import com.example.fastfood.entity.*;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.*;
import com.example.fastfood.request.ItemCartRequest;
import com.example.fastfood.request.OrderDetailRequest;
import com.example.fastfood.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private ShoppingCartDetailRepository shoppingCartDetailRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private SizeToppingRepository sizeToppingRepository;


    public ShoppingCart addToCart(OrderDetailRequest request) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartRepository.save(shoppingCart);
        }
        List<ShoppingCartDetail> cartList = shoppingCart.getShoppingCartDetailList();


        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("không tìm thấy"));
        Size size = sizeRepository.findById(request.getSizeId()).orElseThrow(() -> new NotFoundException("không tìm thấy"));

        ShoppingCartDetail cartItem = find(cartList, request.getProductId(), request.getSizeId(), request.getToppingId());
        int itemQuantity = 0;
        double itemPrice = 0d;
        if (cartItem != null) {
            itemQuantity = cartItem.getQuantity()+ request.getQuantity();
            itemPrice = itemQuantity*cartItem.getUnitPrice()/cartItem.getQuantity();
            cartItem.setQuantity(itemQuantity);
            cartItem.setUnitPrice(itemPrice);
            shoppingCartDetailRepository.save(cartItem);
        }else {
            String toppingName = null;
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
            shoppingCartDetail.setProduct(product);
            shoppingCartDetail.setSize(size);
            shoppingCartDetail.setToppingId(request.getToppingId());
            shoppingCartDetail.setQuantity(request.getQuantity());
            // lay price
            double totalPrice = 0d;
            Double productSize = productSizeRepository.findByProductAndSize(product, size).getPrice();
            Double sizeTopping = 0d;
            if (request.getToppingId() != null) {
                Topping topping = toppingRepository.findById(request.getToppingId()).orElseThrow(() -> new NotFoundException("không tìm thấy"));
                sizeTopping = sizeToppingRepository.findBySizeAndTopping(size, topping).getPrice();
                toppingName = topping.getName();
            }
            shoppingCartDetail.setToppingName(toppingName);
            totalPrice = (productSize + sizeTopping)* request.getQuantity();
            shoppingCartDetail.setUnitPrice(totalPrice);
            shoppingCartDetail.setShoppingCart(shoppingCart);
            shoppingCartDetailRepository.save(shoppingCartDetail);
            cartList.add(shoppingCartDetail);
        }
        Integer totalQuantityCart = totalItem(cartList);
        Double totalPriceCart = totalPrice(cartList);
        shoppingCart.setShoppingCartDetailList(cartList);
        shoppingCart.setTotalItem(totalQuantityCart);
        shoppingCart.setTotalPrice(totalPriceCart);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart updateCart(OrderDetailRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<ShoppingCartDetail> cartList = shoppingCart.getShoppingCartDetailList();

        ShoppingCartDetail cartItem = find(cartList, request.getProductId(), request.getSizeId(), request.getToppingId());
        int itemQuantity = 0;
        double itemPrice = 0d;
        if(cartItem != null){
            itemQuantity = cartItem.getQuantity();
            itemPrice = cartItem.getUnitPrice()* request.getQuantity()/itemQuantity;
            cartItem.setUnitPrice(itemPrice);
            cartItem.setQuantity(request.getQuantity());
            shoppingCartDetailRepository.save(cartItem);
        }
        shoppingCart.setShoppingCartDetailList(cartList);
        Integer totalQuantityCart = totalItem(cartList);
        Double totalPriceCart = totalPrice(cartList);
        shoppingCart.setTotalItem(totalQuantityCart);
        shoppingCart.setTotalPrice(totalPriceCart);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart deleteItemFromCart(OrderDetailRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<ShoppingCartDetail> cartList = shoppingCart.getShoppingCartDetailList();

        ShoppingCartDetail cartItem = find(cartList, request.getProductId(), request.getSizeId(), request.getToppingId());
        if(cartItem != null){
            cartList.remove(cartItem);
//            shoppingCartDetailRepository.delete(cartItem);
        }
        shoppingCart.setShoppingCartDetailList(cartList);
        Integer totalQuantityCart = totalItem(cartList);
        Double totalPriceCart = totalPrice(cartList);
        shoppingCart.setTotalItem(totalQuantityCart);
        shoppingCart.setTotalPrice(totalPriceCart);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }
    public ShoppingCart deleteItem(Long itemId){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<ShoppingCartDetail> cartList = shoppingCart.getShoppingCartDetailList();
        ShoppingCartDetail cartItem = find2(cartList, itemId);
        if(cartItem != null){
            cartList.remove(cartItem);
        }
        shoppingCart.setShoppingCartDetailList(cartList);
        Integer totalQuantityCart = totalItem(cartList);
        Double totalPriceCart = totalPrice(cartList);
        shoppingCart.setTotalItem(totalQuantityCart);
        shoppingCart.setTotalPrice(totalPriceCart);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart updateItem(ItemCartRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<ShoppingCartDetail> cartList = shoppingCart.getShoppingCartDetailList();
        ShoppingCartDetail cartItem = find2(cartList, request.getItemId());
        int itemQuantity = 0;
        double itemPrice = 0d;
        if(cartItem != null){
            itemQuantity = cartItem.getQuantity();
            itemPrice = cartItem.getUnitPrice()* request.getQuantity()/itemQuantity;
            cartItem.setUnitPrice(itemPrice);
            cartItem.setQuantity(request.getQuantity());
            shoppingCartDetailRepository.save(cartItem);
        }
        shoppingCart.setShoppingCartDetailList(cartList);
        Integer totalQuantityCart = totalItem(cartList);
        Double totalPriceCart = totalPrice(cartList);
        shoppingCart.setTotalItem(totalQuantityCart);
        shoppingCart.setTotalPrice(totalPriceCart);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public void deleteCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Không có cart"));
        if(!ObjectUtils.isEmpty(shoppingCart) && !ObjectUtils.isEmpty(shoppingCart.getShoppingCartDetailList())){
            shoppingCartDetailRepository.deleteAll(shoppingCart.getShoppingCartDetailList());
        }
        shoppingCart.getShoppingCartDetailList().clear();
        shoppingCart.setTotalPrice(0d);
        shoppingCart.setTotalItem(0);
        shoppingCartRepository.save(shoppingCart);
    }
    private ShoppingCartDetail find2(List<ShoppingCartDetail> cartList,Long itemId){
        if(cartList.isEmpty()){
            return null;
        }
        ShoppingCartDetail cartItem = null;
        for(ShoppingCartDetail s : cartList){
            if(s.getId().equals(itemId)){
                cartItem = s;
            }
        }
        return cartItem;
    }

    public ShoppingCart getCart(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        return user.getShoppingCart();
    }
    private Integer totalItem(List<ShoppingCartDetail> cartList){
        int totalItem = 0;
        for (ShoppingCartDetail s : cartList){
            totalItem += s.getQuantity();
        }
        return totalItem;
    }
    private Double totalPrice(List<ShoppingCartDetail> cartList){
        double totalPrice = 0d;
        for (ShoppingCartDetail s : cartList){
            totalPrice += s.getUnitPrice();
        }
        return totalPrice;
    }

    private ShoppingCartDetail find(List<ShoppingCartDetail> cartList,Long productId,Long sizeId,Long toppingId){
        if(cartList.isEmpty()){
            return null;
        }
        ShoppingCartDetail cartItem = null;
        for(ShoppingCartDetail s : cartList){
            if(s.getProduct().getId().equals(productId)
                    && s.getSize().getId().equals(sizeId)
                    && s.getToppingId() == (toppingId)){
                cartItem = s;
            }
        }
        return cartItem;
    }
}
