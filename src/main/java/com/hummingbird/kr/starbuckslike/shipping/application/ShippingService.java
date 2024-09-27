package com.hummingbird.kr.starbuckslike.shipping.application;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hummingbird.kr.starbuckslike.shipping.dto.ShippingAddressDTO;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.*;
import com.hummingbird.kr.starbuckslike.shipping.dto.out.*;


public interface ShippingService {

    /*
     * 1. addShippingAddress
     * 2. updateShippingAddress
     * 3. deleteShippingAddress
     * 4. getShippingAddressList
     * 5. setDefaultShippingAddress
     * 6. getDefualtShippingAddress
     * 7. defaultid
     */

     /*
      * 1. addShippingAddress
      * @param addShipppingAddressRequestDTO
      */
    @Transactional
    void add(ShippingAddressAddRequestDTO requestDTO);

    /*
     * 2. updateShippingAddress
     * @param updateShippingAddressRequestDTO
     */
    @Transactional
    void update(ShippingAddressUpdateRequestDTO requestDTO);

    /*
     * 3. deleteShippingAddress
     * @param deleteShippingAddressRequestDTO
     */
    @Transactional
    void delete(ShippingAddressDeleteRequestDTO requestDTO);

    /*
     * 4. getShippingAddressList
     * @param String memberUID
     * @return DTO that contain List of ShippingAddress
     */

    List<ShippingAddressDTO> shippingList(String memberUID);

    /*
     * 5. setDefaultShippingAddress
     * @param setDefaultShippingAddressRequestDTO
     */
    @Transactional
    void setDefault(ShippingAddressSetDefaultRequestDTO requestDTO);

    /*
     * 6. getDefaultShippingAddress
     * @param String memberUID
     * @return DTO that contain default ShippingAddress
     todo 이거를 동적 API화 하는 것도 방법
     * */
    @Transactional
    ShippingAddressGetDetailResponseDTO getDetail(ShippingAddressGetDetailRequestDTO requestDTO);

    /*
     * 7. defaultid
     * @param String memberUID
     * @return DTO that contain default ShippingAddress's ID
     */

    ShippingDefaultIDResponseDTO getDefaultID(String memberUID);

    // todo: 다른 거 하나를 가져와 주는 API 필요 할 거 같아요
}