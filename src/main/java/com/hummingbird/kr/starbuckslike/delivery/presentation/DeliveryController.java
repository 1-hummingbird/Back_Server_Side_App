package com.hummingbird.kr.starbuckslike.delivery.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.delivery.application.DeliveryService;
import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;
import com.hummingbird.kr.starbuckslike.delivery.vo.DeliveryRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j  //log보려고..작성함
@RequestMapping("/api/v1/delivery")
public class DeliveryController {



    private final DeliveryService deliveryService;

    @PostMapping("/delivery-create")
    public CommonResponseEntity<Void> createDelivery(
            @RequestBody DeliveryRequestVo deliveryRequestVo
    ){
        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .userUuid(deliveryRequestVo.getUserUuid())
//                .id(deliveryRequestVo.getId())
                .name(deliveryRequestVo.getName())
                .address(deliveryRequestVo.getAddress())
                .addressnickname(deliveryRequestVo.getAddressnickname())
                .mainphone(deliveryRequestVo.getMainphone())
                .secphone(deliveryRequestVo.getSecphone())
                .memo(deliveryRequestVo.getMemo())
                .isBasic(deliveryRequestVo.getIsBasic())
                .build();
        deliveryService.createDelivery(deliveryRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "등록완료",
                null);
    }

//    //@PutMapping 를 사용해야하나?//@PostMapping 를 사용해야하나?
//    @PutMapping("/delivery-update/{id}") //왜 빨간줄이냐?
//    public CommonResponseEntity<Void> updateDelivery(
//            @PathVariable Long id,
//            @RequestBody DeliveryRequestVo deliveryRequestVo
//            ){
//        Optional<DeliveryRequestDto> updatedelivery = deliveryService.updateDelivery(id,deliveryRequestVo);
//          //  DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()






    }

