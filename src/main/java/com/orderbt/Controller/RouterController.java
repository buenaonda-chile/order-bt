package com.orderbt.Controller;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class RouterController {

    private final EstimateService estimateService;

    @GetMapping("/")
    public String index(Model model){

        List<Item> caseList = estimateService.getCase();

        System.out.println(caseList.toString());

        model.addAttribute("caseList", caseList);

        return "/index";
    }

    @RequestMapping(value = "/jusoPopup")
    public String addrPopup(HttpServletRequest request, Model model){
        String inputYn = request.getParameter("inputYn");
        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");
        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String addrDetail = request.getParameter("addrDetail");
        String admCd = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn = request.getParameter("bdMgtSn");
        String entX = request.getParameter("entX");
        String entY = request.getParameter("entY");
        String rtRoadAddr = request.getParameter("rtRoadAddr");
        String confmKey = "U01TX0FVVEgyMDIxMDcxNzAyNDU0NzExMTQyMzA=";


        model.addAttribute("confmKey", confmKey);
        model.addAttribute("inputYn", inputYn);
        model.addAttribute("roadFullAddr", roadFullAddr);
        model.addAttribute("roadAddrPart1", roadAddrPart1);
        model.addAttribute("roadAddrPart2", roadAddrPart2);
        model.addAttribute("rtRoadAddr", rtRoadAddr);
        model.addAttribute("addrDetail", addrDetail);



        return "/p_addr";
    }

    @GetMapping("/term/privacy")
    public String disTermPrivacy(){
        return "/bos_privacy";
    }

    @GetMapping("/term/service")
    public String disTermService(){
        return "/bos_TermsofService";
    }

    @GetMapping("/et/{id}")
    public String estimateLink(@PathVariable("id") Long id, Model model){
        EstimateDto dto = new EstimateDto();
        dto.setId(id);

        model.addAttribute("estimate", estimateService.getEstimate(dto));

        return "estimate";
    }

}
