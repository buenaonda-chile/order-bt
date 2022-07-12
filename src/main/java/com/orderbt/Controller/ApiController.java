package com.orderbt.Controller;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Domain.Order;
import com.orderbt.Domain.Reservation;
import com.orderbt.Dto.*;
import com.orderbt.Service.EstimateService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final EstimateService estimateService;

    @GetMapping("")
    public String getController(){
        return "test";
    }

    @PostMapping("/estimate")
    public Long createEstimate(EstimateDto dto){

        return estimateService.createEstimate(dto);
    }

    @PutMapping("/estimate")
    public void saveData(OrderDto dto) {
        estimateService.saveData(dto);
    }

    @GetMapping("/estimate")
    public Estimate getEstimate(EstimateDto dto){
        System.out.println("cpcpcppcpcpcp");

        return estimateService.getEstimate(dto);
    }

    @PostMapping("/reservation")
    public Reservation createReservation(ReservationDto dto){
        return estimateService.createReservation(dto);
    }

    @GetMapping("/cell_model")
    public List<Item> getCellItem(ItemDto dto) {
        return estimateService.getCellModel(dto);
    }

    @GetMapping("/pcm")
    public List<Item> getPcm(ItemDto dto){
        return estimateService.getPcm(dto);
    }

    @PostMapping("/message")
    public void sendMessage(MessageDto dto){
        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSCTSU8GDVZ2JXR", "EDYKGFFBLDFO0XR0NBLR2KUYW7SKZBUK", "https://api.solapi.com");

        Message message = new Message();
        message.setFrom("01030491928");
        message.setTo("01030491928");
        message.setText("안녕하세요");

        try {
            // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
