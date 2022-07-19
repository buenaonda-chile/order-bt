package com.orderbt.Controller;

import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;
import com.orderbt.Service.EstimateService;
import com.orderbt.Service.ItemService;
import com.orderbt.Service.LoginService;
import com.orderbt.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/cms/api")
@RequiredArgsConstructor
public class CmsApiController {

    private final LoginService loginService ;
    private final ItemService itemService;
    private final EstimateService estimateService;
    private final StaffService staffService;

    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@ModelAttribute StaffDto dto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String returnVal = "";
        String autoLogin = request.getParameter("autoLogin");
        request.setAttribute("autoLogin", autoLogin);
        String pwdCheck = loginService.getPasswordCheck(dto, request, response);

        switch(pwdCheck){
            case "login_fail" :  // 아이디 , 패스워드 오류
                returnVal = "아이디 또는 비밀번호를 확인해주세요.";
                break;
            case "login_auth_fail" :  // 관리자가 아닌경우 / 비활성화 상태인 경우
                returnVal = "해당 계정은 관리자 화면에 접속이 불가합니다.\n로그인 필요 시, 관리자에게 문의바랍니다.";
                break;
            case "login" :  // 로그인 성공

                if(autoLogin.equals("on")) {
                    Cookie cookie = new Cookie("id", dto.getId());
                    cookie.setMaxAge(60*60*24*7);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                returnVal = "/cms/staff";
                break;
        }

        return returnVal;
    }

    @GetMapping("/staff")
    public List<StaffDto> getStaffGrid(SearchDto dto) { return staffService.getStaffGrid(dto); }

    @PostMapping("/staff")
    public void joinStaff(StaffDto dto){ staffService.joinStaff(dto);}

    @PutMapping("/staff")
    public void updateStaff(StaffDto dto){staffService.updateStaff(dto);}

    @DeleteMapping("/staff")
    public void deleteStaff(StaffDto dto) {staffService.deleteStaff(dto.getId());}

    @GetMapping("/staff/dupCheckId")
    public Integer dupCheckId(StaffDto dto){ return staffService.dupCheckId(dto.getId());}

    @PutMapping("/staffActive")
    public void updateStaffActive(@RequestBody List<StaffDto> dtos){staffService.updateStaffActive(dtos);}

    @GetMapping("/estimate")
    public List<EstimateDto> getEstimateGrid(SearchDto dto) { return estimateService.getEstimateGrid(dto); }

    @PutMapping("/estimate")
    public void saveEstimate(@RequestBody List<EstimateDto> dtos){
        estimateService.saveEstimate(dtos);
    }

    @GetMapping("/item")
    public List<ItemDto> getItem(SearchDto dto){
        return itemService.getItem(dto);
    }

    @PostMapping("/item")
    public Integer saveItem(ItemDto dto){
        return itemService.saveItem(dto);
    }

    @PutMapping("/item")
    public void updateItem(ItemDto dto){
        itemService.updateItem(dto);
    }

    @DeleteMapping("/item")
    public void deleteItem(ItemDto dto){
        itemService.deleteItem(dto);
    }

    @DeleteMapping("/items")
    public void deleteItems(@RequestBody List<ItemDto> dtos){
        itemService.deleteItems(dtos);
    }

    @PutMapping("/items")
    public void updateItems(@RequestBody List<ItemDto> dtos){
        itemService.updateItems(dtos);
    }

    @GetMapping("/itemInfo")
    public List<ItemDto> getItemInfo(@RequestParam Integer itemId){
        return itemService.getItemInfo(itemId);
    }

    @PutMapping("/itemInfo")
    @ResponseBody
    public List<ItemDto> saveItemInfo(@RequestBody List<ItemDto> dtos){
        itemService.saveItemInfo(dtos);
        return itemService.getItemInfo(dtos.get(0).getItemId());
    }

    @DeleteMapping("/itemInfo")
    @ResponseBody
    public List<ItemDto> deleteItemInfo(@RequestBody List<ItemDto> dtos){
        itemService.deleteItemInfo(dtos);
        return itemService.getItemInfo(dtos.get(0).getItemId());
    }
}
