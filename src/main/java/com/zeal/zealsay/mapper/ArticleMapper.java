package com.zeal.zealsay.mapper;

import com.zeal.zealsay.dto.response.ArticlePageResponse;
import com.zeal.zealsay.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.zealsay.service.cache.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author zhanglei
 * @since 2018-11-28
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction= MybatisRedisCache.class)
public interface ArticleMapper extends BaseMapper<Article> {

  List<ArticlePageResponse>getPage(Long userId,Long offset,Long size);

  Long getPageCount(Long userId,Long offset,Long size);
}
