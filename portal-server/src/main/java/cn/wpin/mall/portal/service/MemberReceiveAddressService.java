package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.user.MemberReceiveAddressClient;
import cn.wpin.mall.user.entity.MemberReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户地址管理Service实现类
 *
 * @author wangpin
 */
@Service
public class MemberReceiveAddressService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberReceiveAddressClient memberReceiveAddressClient;


    public int add(MemberReceiveAddress address) {
        address.setMemberId(memberService.getCurrentMember().getId());
        return memberReceiveAddressClient.add(address);
    }

    public int delete(Long id) {
        return memberReceiveAddressClient.delete(id, memberService.getCurrentMember());
    }

    public int update(Long id, MemberReceiveAddress address) {
        address.setMemberId(memberService.getCurrentMember().getId());
        return memberReceiveAddressClient.update(id, address);
    }

    public List<MemberReceiveAddress> list() {
        return memberReceiveAddressClient.list(memberService.getCurrentMember());
    }

    public MemberReceiveAddress getItem(Long id) {
        return memberReceiveAddressClient.getItem(id, memberService.getCurrentMember());
    }
}
