package com.orderbt.Controller;

import com.orderbt.Dto.EstimateDto;
import com.orderbt.Service.EstimateService;
import com.orderbt.Service.ItemService;
import com.orderbt.Service.StaffService;
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

    private final EstimateService estimateService;

    private final StaffService staffService;

    @GetMapping("")
    public String disLogin(){
        return "cms/login";
    }

    @GetMapping("/staff")
    public String disStaff(Model model){
        HashMap<String, Integer> dashBoard = staffService.getStaffBoard();

        model.addAttribute("totalCnt", dashBoard.get("totalcnt"));

        return "cms/staff";
    }

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

        HashMap<String, Object> dashBoard = estimateService.getEstimateBoard();

        model.addAttribute("totalCnt", dashBoard.get("totalcnt"));
        model.addAttribute("consultYCnt", dashBoard.get("consultycnt"));
        model.addAttribute("consultNCnt", dashBoard.get("consultncnt"));
        model.addAttribute("maxType", dashBoard.get("maxtype"));

        return "cms/Estimate";
    }
}
