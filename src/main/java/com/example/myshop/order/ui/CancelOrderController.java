package com.example.myshop.order.ui;

import com.example.myshop.order.command.application.CancelOrderService;
import com.example.myshop.order.command.domain.Canceller;
import com.example.myshop.order.command.domain.OrderNo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CancelOrderController {

    private final CancelOrderService cancelOrderService;

    @RequestMapping("/my/orders/{orderNo}/cancel")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cancelOrderService.cancel(new OrderNo(orderNo), new Canceller(user.getUsername()));
        return "/my/orderCanceled";
    }

}
