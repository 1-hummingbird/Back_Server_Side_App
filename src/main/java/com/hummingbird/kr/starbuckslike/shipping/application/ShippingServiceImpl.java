package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hummingbird.kr.starbuckslike.shipping.infrastructure.*;
import com.hummingbird.kr.starbuckslike.shipping.domain.*;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.*;
import com.hummingbird.kr.starbuckslike.shipping.dto.out.*;

@Slf4j
@Service
public class ShippingServiceImpl implements ShippingService {
    
    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingSettingRepository shippingSettingRepository;

    @Autowired
    public ShippingServiceImpl(ShippingAddressRepository shippingAddressRepository, ShippingSettingRepository shippingSettingRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
        this.shippingSettingRepository = shippingSettingRepository;
    }


    @Override
    public void add(ShippingAddressAddRequestDTO requestDTO) {
        final int Maximum_Shipping_Address = 20;
        ShippingSetting shippingSetting = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).
                orElse(null);
        if (shippingSetting == null) {
            shippingSetting  = new ShippingSetting(requestDTO.getMemberUID(), 0);
        }
        else if (shippingSetting.getCountShipping() >= Maximum_Shipping_Address) {
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        shippingAddressRepository.save(requestDTO.toEntity());
        shippingSetting.countShippingPlus();
        shippingSettingRepository.save(shippingSetting);
    }

    @Override
    public void update(ShippingAddressUpdateRequestDTO requestDTO) {
        ShippingAddress oldThing = shippingAddressRepository.findById(requestDTO.getShippingAddressID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        if (oldThing.getMemberUID().equals(requestDTO.getMemberUID())){
            ShippingAddress newThing = oldThing.toBuilder()
                .addressNickname(requestDTO.getAddressNickname() != null ? requestDTO.getAddressNickname() : oldThing.getAddressNickname())
                .name(requestDTO.getName() != null ? requestDTO.getName() : oldThing.getName())
                .address(requestDTO.getAddress() != null ? requestDTO.getAddress() : oldThing.getAddress())
                .Phone(requestDTO.getPhone() != null ? requestDTO.getPhone() : oldThing.getPhone())
                .memo(requestDTO.getMemo() != null ? requestDTO.getMemo() : oldThing.getMemo())
                .build();
            shippingAddressRepository.save(newThing);
        } else {
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
    }

    @Override
    public void delete(ShippingAddressDeleteRequestDTO requestDTO) {
        ShippingAddress existOne = shippingAddressRepository.findById(requestDTO.getShippingAddressID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        if (existOne.getMemberUID().equals(requestDTO.getMemberUID())){
            ShippingSetting shippingSetting = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).
                    orElse(null);
            if (shippingSetting.getDefaultShippingAddressID().equals(existOne.getId())){
                shippingSetting.setDefaultShippingAddressID(null);
            }
            shippingAddressRepository.deleteByAddressID (requestDTO.getShippingAddressID());
            shippingSetting.countShippingMinus();
            shippingSettingRepository.save(shippingSetting);}
        else {
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
    }

    @Override
    public ShippingAddressListResponseDTO list(String memberUID) {
        return new ShippingAddressListResponseDTO(shippingAddressRepository.findByMemberUID(memberUID));
    }

    @Override
    public void setDefault(ShippingAddressSetDefaultRequestDTO requestDTO) {
        ShippingSetting shippingSetting = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        shippingAddressRepository.findById(requestDTO.getShippingAddressID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        shippingSetting.setDefaultShippingAddressID(requestDTO.getShippingAddressID());
        shippingSettingRepository.save(shippingSetting);
    }

    @Override
    public ShippingAddressGetDefaultResponseDTO getDefault(String memberUID) {
        Long addressID = shippingSettingRepository.findByMemberUID(memberUID).get().getDefaultShippingAddressID();
        if (addressID == null) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_INTEREST);
        }
        ShippingAddress shippingAddress = shippingAddressRepository.findById(addressID).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        return ShippingAddressGetDefaultResponseDTO.fromEntity(shippingAddress) ;}

}
