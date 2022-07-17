package com.orderbt.Controller;

import com.orderbt.Dto.EstimateDto;
import com.orderbt.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/cms")
@RequiredArgsConstructor
public class CmsRouterController {

    private final ItemService itemService;

    @GetMapping("/item")
    public String disItem(Model model){

        HashMap<String, Integer> dashBoard = itemService.getItemBoard();

        model.addAttribute("categoryCnt", dashBoard.get("categorycnt"));
        model.addAttribute("totalCnt", dashBoard.get("totalcnt"));
        model.addAttribute("activeCnt", dashBoard.get("activecnt"));

        return "cms/item";
    }

    @GetMapping("/estimate")
    public String disEstimate(Model model){

        return "cms/Estimate";
    }
}
