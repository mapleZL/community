package com.ejavashop.service.member;

import javax.annotation.Resource;

import com.phkj.entity.member.Member;
import com.phkj.service.member.IMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class MemberServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "memberService")
    IMemberService memberService;

    @Test
    public void testGetMemberById() {

        com.phkj.core.ServiceResult<Member> memberById = memberService.getMemberById(6);
        System.out.println(memberById.getResult());
    }

}
