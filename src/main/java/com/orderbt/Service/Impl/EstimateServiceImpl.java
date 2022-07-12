package com.orderbt.Service.Impl;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Domain.Order;
import com.orderbt.Domain.Reservation;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.OrderDto;
import com.orderbt.Dto.ReservationDto;
import com.orderbt.Repository.EstimateRepository;
import com.orderbt.Service.EstimateService;
import com.orderbt.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstimateServiceImpl implements EstimateService {

    private final EstimateRepository estimateRepository;

    private final FileService fileService;

    @Override
    @Transactional
    public Long createEstimate(EstimateDto dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (dto.getId() == null) {

            Estimate estimate = new Estimate();

            estimate.setId(Long.parseLong(genSaveFileName()));
            estimate.setCompany(dto.getCompany());
            estimate.setName(dto.getName());
            estimate.setEmail(dto.getEmail());
            estimate.setName(dto.getName());
            estimate.setTel(dto.getTel());
            estimate.setCretDt(timestamp);

            estimateRepository.createEstimate(estimate);

            return estimate.getId();
        } else {
            Estimate estimate = estimateRepository.findById(dto.getId());

            estimate.setCompany(dto.getCompany());
            estimate.setName(dto.getName());
            estimate.setEmail(dto.getEmail());
            estimate.setName(dto.getName());
            estimate.setTel(dto.getTel());
            estimate.setCretDt(timestamp);

            return estimate.getId();
        }
    }

    @Override
    @Transactional
    public Reservation createReservation(ReservationDto dto) {
        Estimate estimate = estimateRepository.findById(dto.getId());

        if(Objects.isNull(estimate.getReservation())) {
            Reservation reservation = new Reservation(dto.getType(), dto.getTypeDtl(), LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_DATE), dto.getTime());

            estimateRepository.createReservation(reservation);

            estimate.setReservation(reservation);
        }else {
            estimate.getReservation().setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_DATE));
            estimate.getReservation().setType(dto.getType());
            estimate.getReservation().setTypeDtl(dto.getTypeDtl());
            estimate.getReservation().setTime(dto.getTime());
        }

        return estimate.getReservation();
    }

    @Override
    @Transactional
    public void saveData(OrderDto dto) {
        Estimate estimate = estimateRepository.findById(dto.getId());


        if (Objects.isNull(estimate.getOrder())) {
            Order order = new Order();

            order.setPurpose(dto.getPurpose());

            estimateRepository.createOrder(order);

            estimate.setOrder(order);
        } else {
            if (dto.getPurpose() != null) estimate.getOrder().setPurpose(dto.getPurpose());
            if (dto.getRating() != null) estimate.getOrder().setRating(dto.getRating());
            if (dto.getMaxRating() != null) estimate.getOrder().setMaxRating(dto.getMaxRating());
            if (dto.getBatteryCell() != null) estimate.getOrder().setBatteryCell(dto.getBatteryCell());
            if (dto.getCellModel() != null) estimate.getOrder().setCellModel(dto.getCellModel());
            if (dto.getVoltage() != null) estimate.getOrder().setVoltage(dto.getVoltage());
            if (dto.getAmpere() != null) estimate.getOrder().setAmpere(dto.getAmpere());
            if (dto.getPcm() != null) estimate.getOrder().setPcm(dto.getPcm());
            if (dto.getPcmRemark() != null) estimate.getOrder().setPcmRemark(dto.getPcmRemark());
            if (dto.getCaseType() != null) estimate.getOrder().setCaseType(dto.getCaseType());
            if (dto.getCaseRemark() != null) estimate.getOrder().setCaseRemark(dto.getCaseRemark());
            if (dto.getSampleQty() != null) estimate.getOrder().setSampleQty(dto.getSampleQty());
            if (dto.getSampleDate() != null) estimate.getOrder().setSampleDate(LocalDate.parse(dto.getSampleDate(), DateTimeFormatter.ISO_DATE));
            if (dto.getAddress() != null) estimate.getOrder().setAddress(dto.getAddress());
            if (dto.getAddressDtl() != null) estimate.getOrder().setAddressDtl(dto.getAddressDtl());
            if (dto.getRemark() != null) estimate.getOrder().setRemark(dto.getRemark());

            //다중파일 업로드
            if(dto.getAttachFile() != null){
                final String url = "https://orderbt.com/img/";
                HashMap<String, String> param = new HashMap<>();
                ArrayList<String> fileArray = new ArrayList<>();

                param.put("filePath","/img");
                dto.getAttachFile().forEach(file -> {
                    try {
                        String originalFileName = file.getOriginalFilename();	//오리지날 파일명
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
                        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
                        param.put("fileName",savedFileName);
                        fileService.uploadFile(file,param);
                        fileArray.add(url + savedFileName);
                        dto.setFiles(String.join(",",fileArray));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Estimate getEstimate(EstimateDto dto) {
        return estimateRepository.findEstimate(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getCellModel(ItemDto dto) {
        List<Item> items = estimateRepository.getCellModel(dto);

        items.forEach(item -> Collections.sort(item.getItemInfos()));

        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getPcm(ItemDto dto) {
        List<Item> items = estimateRepository.getPcm(dto);

        if(items.isEmpty()) {
            items = estimateRepository.getPcmAll(dto);
            items.forEach(item -> Collections.sort(item.getItemInfos()));
            return items;
        }else {
            items.forEach(item -> Collections
                                    .sort(item.getItemInfos()));

            return items;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getCase() {
        return estimateRepository.getCase();
    }

    // 현재 시간을 기준으로 견적번호 생성
    private String genSaveFileName() {
        String number = "";

        Calendar calendar = Calendar.getInstance();
        number += calendar.get(Calendar.YEAR);
        number += calendar.get(Calendar.MONTH + 1);
        number += calendar.get(Calendar.DATE);
        number += calendar.get(Calendar.HOUR);
        number += calendar.get(Calendar.MINUTE);
        number += calendar.get(Calendar.SECOND);
        number += calendar.get(Calendar.MILLISECOND);

        return number;
    }
}
