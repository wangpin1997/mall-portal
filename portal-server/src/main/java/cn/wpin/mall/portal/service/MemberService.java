package cn.wpin.mall.portal.service;


import cn.wpin.mall.client.user.MemberClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.portal.config.oauth2.MemberDetails;
import cn.wpin.mall.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 会员管理Service实现类
 *
 * @author wangpin
 */
@Service
public class MemberService {

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    public Member getByUsername(String username) {
        return memberClient.getByUsername(username);
    }

    public Member getById(Long id) {
        return memberClient.getById(id);
    }

    public CommonResult register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return CommonResult.failed("验证码错误");
        }
        return memberClient.register(username, password, telephone);
    }

    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    public CommonResult updatePassword(String telephone, String password, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return CommonResult.failed("验证码错误");
        }
        return memberClient.updatePassword(telephone, password);
    }

    public Member getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getMember();
    }

    public void updateIntegration(Long id, Integer integration) {
        memberClient.updateIntegration(id, integration);
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

}
