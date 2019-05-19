package com.zeal.zealsay.converter;

import com.zeal.zealsay.dto.request.ArticleLabelAddRequest;
import com.zeal.zealsay.dto.request.ArticleLabelUpdateRequest;
import com.zeal.zealsay.dto.response.ArticleLabelResponse;
import com.zeal.zealsay.entity.ArticleLabel;
import org.mapstruct.Mapper;

/**
* ArticleLabel 转换器.
*
* @author  zeal
* @date 2019/5/19 23:06
*/
@Mapper(componentModel = "spring")
public interface ArticleLabelConvertMapper {

  ArticleLabelResponse toArticleLabelResponse(ArticleLabel articleLabel);

  ArticleLabel toArticleLabel(ArticleLabelAddRequest articleLabelAddRequest);

  ArticleLabel toArticleLabel(ArticleLabelUpdateRequest articleLabelUpdateRequest);

}
