package com.zeal.zealsay.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.zeal.zealsay.common.entity.Result;
import com.zeal.zealsay.common.entity.SecuityUser;
import com.zeal.zealsay.common.entity.UserInfo;
import com.zeal.zealsay.security.core.TokenManager;
import com.zeal.zealsay.service.LoginLogService;
import com.zeal.zealsay.service.auth.UserDetailServiceImpl;
import com.zeal.zealsay.util.JwtTokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zeal.zealsay.common.constant.enums.ResultCode.OK;

/**
 * 登录成功并返回token逻辑.
 *
 * @author  zhanglei
 * @date 2018/10/24  4:58 PM
 */
@Slf4j
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Autowired
  LoginLogService loginLogService;

  @Autowired
  TokenManager tokenManager;

  @Autowired
  UserDetailServiceImpl userDetailService;

  @SneakyThrows
  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      Authentication authentication) throws IOException {
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    final SecuityUser secuityUser = (SecuityUser) authentication.getPrincipal();
    final UserInfo userInfo = userDetailService.toUserInfo(secuityUser.getUserId());
    // 移除之前的token（包含member信息、token排行信息）
    tokenManager.delToken(userInfo);
    String token = tokenManager.saveToken(userInfo);

//    final String token = jwtTokenUtil.generateToken(secuityUser);
    log.info("----------------用户'{}'登录成功，开始执行初始化-----------------------",secuityUser.getUsername());
    httpServletResponse.getWriter()
        .write(objectMapper
            .writeValueAsString(Result.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .data(ImmutableMap.of("token", token))
                .build()));
    //记录登录信息
    loginLogService.saveLog(httpServletRequest,secuityUser);
  }
}
