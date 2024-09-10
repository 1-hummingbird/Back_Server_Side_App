package com.hummingbird.kr.starbuckslike.exhibition.application;

import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionDetailResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionListResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.search.ExhibitionSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements  ExhibitionService{
    private final ExhibitionSearch exhibitionSearch;
    @Override
    public List<ExhibitionListResponseDto> findAllExhibitionNames() {
        return exhibitionSearch.findAllExhibitionNames();
    }

    @Override
    public ExhibitionDetailResponseDto findExhibitionDetail(Long id) {
        return exhibitionSearch.findExhibitionDetail(id);
    }
}
