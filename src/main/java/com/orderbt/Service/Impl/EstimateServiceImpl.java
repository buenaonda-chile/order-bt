package com.orderbt.Service.Impl;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Domain.Order;
import com.orderbt.Domain.Reservation;
import com.orderbt.Dto.*;
import com.orderbt.Mapper.EstimateMapper;
import com.orderbt.Repository.EstimateRepository;
import com.orderbt.Service.EstimateService;
import com.orderbt.Service.FileService;
import com.orderbt.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private final EstimateMapper estimateMapper;
    private final FileService fileService;
    private final Environment env;

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
            Reservation reservation = new Reservation(dto.getType(), dto.getTypeDtl(), LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_DATE), dto.getTime(), "N");

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

            //???????????? ?????????
            if(dto.getAttachFile() != null){
                final String url = "https://orderbt.com/img/";
                HashMap<String, String> param = new HashMap<>();
                ArrayList<String> fileArray = new ArrayList<>();

                param.put("filePath","/img");
                dto.getAttachFile().forEach(file -> {
                    try {
                        String originalFileName = file.getOriginalFilename();	//???????????? ?????????
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//?????? ?????????
                        String savedFileName = UUID.randomUUID() + extension;	//????????? ?????? ???
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

    @Override
    public void sendEmail(MessageDto dto) {
        String host = "smtp.gmail.com";
        String user = env.getProperty("gmail.id");
        String password = env.getProperty("gmail.password");
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        try {
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(user, password);
                }
            });
            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(dto.getEmail())));
                message.setHeader("content-type", "text/html;charset=UTF-8");
                String mailText = "";

                message.setSubject("[(???) CTNS ???????????? ????????? ?????? ?????? ]");
                mailText +="<!DOCTYPE html>\n" +
                        "<html lang=\"en\" dir=\"ltr\">\n" +
                        "  <head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>ctns_email</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <div style=\" width :60%; margin:0 auto;margin-top:10%;max-width: 1500px;min-width: 1000px;\" >\n" +
                        "      <header style=\" margin-bottom: 0rem;height: 6.5rem;align-items: end;justify-content: center;display: flex;\">\n" +
                        "        <h1 style=\"width: 30%;line-height: 100%;font-weight: 700;\">\n" +
                        "          <img src=\"https://orderbt.com/img/img_1.png\" alt=\"ctnslogo\" style=\"width:13rem;object-fit: cover;\">\n" +
                        "        </h1>\n" +
                        "        <h2 style=\"width: 70%;line-height: 100%;\">\n" +
                        "          <img src=\"https://orderbt.com/img/img_2.png\" alt=\"\"  style=\"object-fit: cover;width: 100%;\">\n" +
                        "        </h2>\n" +
                        "      </header>\n" +
                        "      <div class=\"content\" style=\"border:1px solid #222;padding: 3rem;height: 29rem;\">\n" +
                        "         <h3 style=\"font-size: 1.7rem;padding: 1rem 0rem;font-weight: 600;\">[(???) CTNS ???????????? ????????? ?????? ?????? ]</h3>\n" +
                        "        <p>??????????????? "+ dto.getName()+"???!<br>\n" +
                        "            ?????? ???????????? ????????? ?????? ??????????????????.</p>\n" +
                        "            <ul>\n" +
                        "              <li>?????? ?????? : "+ dto.getEstiNum() +"</li>\n" +
                        "              <li>?????? ?????? : "+ dto.getMeet() +"</li>\n" +
                        "              <li>?????? ?????? : "+ dto.getDate()+"</li>\n" +
                        "            </ul>\n" +
                        "            <br>\n" +
                        "            <p>???????????? ???????????? ???????????? ???????????????.</p>\n" +
                        "            <br>\n" +
                        "            <p>????????? ?????? ???????????? ????????? ????????? ???????????????.</p>\n" +
                        "            <br>\n" +
                        "            <p>?????? ?????? : 010-9928-3137</p>\n" +
                        "      </div>\n" +
                        "      <footer style=\"margin-top: 2rem;\">\n" +
                        "        <h4 style=\"font-size: 1.3rem;font-weight: 600;margin-bottom: 1rem;\">???????????? ???????????????</h4>\n" +
                        "        <p style=\"padding: .2rem;font-weight: 600;\">???????????? : ?????????  &nbsp;&nbsp; ??? &nbsp;&nbsp;  ????????????????????? : 307-81-50055</p>\n" +
                        "        <p style=\"padding: .2rem;font-weight: 600;\">?????? : ???????????? ????????? ????????? ????????? 23, 641??? (?????????????????????)</p>\n" +
                        "        <p style=\"padding: .2rem;font-weight: 600;\">tel : 055-294-9555  &nbsp;&nbsp; ???  &nbsp;&nbsp;  fax : 055-294-9556</p>\n" +
                        "      </footer>\n" +
                        "    </div>\n" +
                        "  </body>\n" +
                        "</html>";
                message.setContent(mailText,"text/html;charset=UTF-8");
                Transport.send(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<EstimateDto> getEstimateGrid(SearchDto dto) {
        if(dto.getKeyword() != null)
            dto.setKeywordList(Util.makeForeach(dto.getKeyword(), ","));

        return estimateMapper.getEstimateGrid(dto);
    }

    @Override
    public void saveEstimate(List<EstimateDto> dtos) {
        for(EstimateDto dto : dtos){
            System.out.println(dto.getConsultYn());
            estimateMapper.saveEstimate(dto);
        }
    }

    @Override
    public HashMap<String, Object> getEstimateBoard() {
        return estimateMapper.getEstimateBoard();
    }

    @Override
    public CtnsEstimateDto getCtnsEstimate(Integer estimateId) {
        return estimateMapper.getCtnsEstimate(estimateId);
    }

    @Override
    public void saveCtnsEstimate(CtnsEstimateDto dto) {
        estimateMapper.saveCtnsEstimate(dto);
    }

    @Override
    public List<CtnsOrderDto> getCtnsOrder(Integer estimateId) {
        return estimateMapper.getCtnsOrder(estimateId);
    }

    @Override
    public void saveCtnsOrder(List<CtnsOrderDto> dtos) {
        for(CtnsOrderDto dto : dtos){
            estimateMapper.saveCtnsOrder(dto);
        }
    }

    @Override
    public void updateCtnsOrder(List<CtnsOrderDto> dtos) {
        for(CtnsOrderDto dto : dtos){
            estimateMapper.updateCtnsOrder(dto);
        }
    }

    @Override
    public void deleteCtnsOrder(List<CtnsOrderDto> dtos) {
        for(CtnsOrderDto dto : dtos){
            estimateMapper.deleteCtnsOrder(dto);
        }
    }

    // ?????? ????????? ???????????? ???????????? ??????
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
