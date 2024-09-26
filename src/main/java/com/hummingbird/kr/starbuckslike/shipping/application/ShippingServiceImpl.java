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
import com.hummingbird.kr.starbuckslike.shipping.dto.*;

import java.util.List;
import java.util.stream.Collectors;

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
        ShippingAddress shippingAddress = requestDTO.toEntity();
        shippingAddressRepository.save(shippingAddress);
        // 만약에 기본 배송지로 설정하면서 추가하는 경우 처리 필요함
        ShippingSetting shippingSetting = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).
                orElse(null);
 
        if (requestDTO.getWillDefault()){
            Long addressid = shippingAddressRepository
                    .findIdByAllArgument(requestDTO.getAddressNickname(),requestDTO.getName(),requestDTO.getAddress(), requestDTO.getPhone(), requestDTO.getMemberUID(), requestDTO.getMemo());
            if (shippingSetting == null) {
                shippingSetting  = new ShippingSetting(requestDTO.getMemberUID(), addressid);
            }
            else {
                shippingSetting.setDefaultShippingAddressID(addressid);
            }
        }
        shippingSettingRepository.save(shippingSetting);
    }

    @Override
    public void update(ShippingAddressUpdateRequestDTO requestDTO) {
        ShippingAddress oldEntity = shippingAddressRepository.findById(requestDTO.getShippingAddressID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        if (oldEntity.getMemberUID().equals(requestDTO.getMemberUID())) {
            shippingAddressRepository.save(requestDTO.toEntity(oldEntity));
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
                shippingSettingRepository.save(shippingSetting);
            }
            shippingAddressRepository.deleteByAddressID (requestDTO.getShippingAddressID());
            
        }
        else {
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
    }

    @Override
    public List<ShippingAddressDTO> shippingList(String memberUID) {
        List<ShippingAddress> shippingAddresses = shippingAddressRepository.findByMemberUID(memberUID);
        List<ShippingAddressDTO> shippingAddressDTOs = shippingAddresses.stream()
            .map(ShippingAddressDTO::new)
            .collect(Collectors.toList());
        return shippingAddressDTOs;
    }



    @Override
    public void setDefault(ShippingAddressSetDefaultRequestDTO requestDTO) {
        ShippingSetting shippingSetting = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        shippingAddressRepository.findById(requestDTO.getShippingAddressID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        shippingSetting.setDefaultShippingAddressID(requestDTO.getShippingAddressID());
        shippingSettingRepository.save(shippingSetting);
    }

    @Override
    public ShippingAddressGetDetailResponseDTO getDetail(ShippingAddressGetDetailRequestDTO requestDTO) {
        Long addressID = shippingSettingRepository.findByMemberUID(requestDTO.getMemberUID()).get().getDefaultShippingAddressID();
        if (addressID == null) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_INTEREST);
        }
        ShippingAddress shippingAddress = shippingAddressRepository.findById(addressID).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        return ShippingAddressGetDetailResponseDTO.fromEntity(shippingAddress) ;}

    @Override
    // todo :  defaultID라는 말이 오해가 될 수 있을 거 같아요. 함수명은 함수명 답게, getDefaultID로 해야죠.
    public ShippingDefaultIDResponseDTO getDefaultID(String memberUID){
        Long addressID = shippingSettingRepository.findByMemberUID(memberUID).get().getDefaultShippingAddressID();
        return new ShippingDefaultIDResponseDTO(addressID);
    }

    // todo : 만든 갯수를 주던지 추가 가능 여부를 돌려주던지 구현을 해 주면 됩니다.
    // todo : setting에 갯수 저장을 굳이 안 해도 될 거 같아요. Shipping Setteing 제거를 고민해 봅시다.

}
