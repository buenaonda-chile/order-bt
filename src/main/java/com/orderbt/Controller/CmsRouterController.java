package com.orderbt.Controller;

import com.orderbt.Dto.EstimateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class CmsRouterController {

    @GetMapping("/item")
    public String disItem(){

        return "cms/item";
    }
}
