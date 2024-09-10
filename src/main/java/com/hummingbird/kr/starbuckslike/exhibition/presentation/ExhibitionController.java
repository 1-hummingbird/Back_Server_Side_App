package com.hummingbird.kr.starbuckslike.exhibition.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.exhibition.application.ExhibitionService;
import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionListResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.vo.ExhibitionDetailResponseVo;
import com.hummingbird.kr.starbuckslike.exhibition.vo.ExhibitionListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    // 상품 디테일 상품 상품명 가격 등 조회
    @Operation(summary = "기획전 조회", description = "기획전 시작~끝 날짜 유효해야 조회됨")
    @GetMapping("/list")
    public CommonResponseEntity<List<ExhibitionListResponseVo>> findAllExhibitionNamesV1(){

        List<ExhibitionListResponseVo> res = exhibitionService.findAllExhibitionNames()
                .stream()
                .map(ExhibitionListResponseDto::toVo)
                .toList();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                res
        );
    }

    @Operation(summary = "기획전 디테일 조회", description = "기획전 에디터 데이터(html) 조회")
    @GetMapping("/{id}")
    public CommonResponseEntity<ExhibitionDetailResponseVo> findExhibitionDetailV1(
            @PathVariable("id") Long id
    ){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                exhibitionService.findExhibitionDetail(id).toVo()
        );
    }
}
