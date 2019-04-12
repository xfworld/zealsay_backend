package com.zeal.zealsay.helper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeal.zealsay.common.entity.PageInfo;
import com.zeal.zealsay.converter.UserConvertMapper;
import com.zeal.zealsay.dto.request.UserAddRequest;
import com.zeal.zealsay.dto.request.UserUpdateRequest;
import com.zeal.zealsay.dto.response.UserResponse;
import com.zeal.zealsay.entity.User;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户帮助类.
 *
 * @author zhanglei
 * @date 2018/11/15  6:53 PM
 */
@Component
public class UserHelper {

  @Autowired
  UserConvertMapper userConvertMapper;

  /**
   * 更新之前通过请求参数转换成user.
   *
   * @author zhanglei
   * @date 2018/11/15  7:46 PM
   */
  public User initBeforeUpdate(UserUpdateRequest userUpdateRequest) {
    return userConvertMapper.toUser(userUpdateRequest);
  }

  /**
   * 转换成返回列表.
   *
   * @author zhanglei
   * @date 2018/11/15  9:25 PM
   */
  public PageInfo<UserResponse> toPageInfo(Page<User> userPage) {
    PageInfo<User> userPageInfo = new PageInfo();
    List<UserResponse> userResponses = userPage.getRecords()
        .stream()
        .map(s -> userConvertMapper.toUserResponse(s))
        .collect(Collectors.toList());
    return PageInfo.<UserResponse>builder()
        .records(userResponses)
        .currentPage(userPage.getCurrent())
        .pageSize(userPage.getSize())
        .total(userPage.getTotal())
        .build();
  }

  /**
   * 添加之前通过请求参数转换成user.
   *
   * @author zhanglei
   * @date 2018/11/15  7:46 PM
   */
  public User initBeforeAdd(UserAddRequest userAddRequest) {
    return userConvertMapper.toUser(userAddRequest);
  }

  /**
   * 构造模糊查询参数.
   *
   * @author  zhanglei
   * @date 2019-03-08  11:55
   */
  public QueryWrapper<User> buildVagueQuery(@NonNull User user) {
    QueryWrapper<User> queryWrapper = new QueryWrapper();
    if (StringUtils.isNotBlank(user.getAddress())) {
      queryWrapper.like("address",user.getAddress());
    }
    if (StringUtils.isNotBlank(user.getArea())) {
      queryWrapper.like("area",user.getArea());
    }
    if (StringUtils.isNotBlank(user.getUsername())) {
      queryWrapper.like("username",user.getUsername());
    }
    if (StringUtils.isNotBlank(user.getName())) {
      queryWrapper.like("name",user.getName());
    }
    if (StringUtils.isNotBlank(user.getLabel())) {
      queryWrapper.like("label",user.getLabel());
    }
    if (StringUtils.isNotBlank(user.getPhoneNumber())) {
      queryWrapper.like("phone_number",user.getPhoneNumber());
    }
    if (StringUtils.isNotBlank(user.getEmail())) {
      queryWrapper.like("email",user.getEmail());
    }
    if (StringUtils.isNotBlank(user.getAddress())) {
      queryWrapper.like("address",user.getAddress());
    }
    if (StringUtils.isNotBlank(user.getProvince())) {
      queryWrapper.like("province",user.getProvince());
    }
    if (StringUtils.isNotBlank(user.getCity())) {
      queryWrapper.like("city",user.getCity());
    }
    if (StringUtils.isNotBlank(user.getAddress())) {
      queryWrapper.like("address",user.getAddress());
    }
    if (!Objects.isNull(user.getAge())) {
      queryWrapper.like("age",user.getAge());
    }
    if (!Objects.isNull(user.getSex())) {
      queryWrapper.like("sex",user.getSex());
    }
    return queryWrapper;
  }
}
