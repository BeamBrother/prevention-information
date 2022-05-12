package com.bl.ep.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bl.ep.bean.AccessInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AccessInformationMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/20 16:27
 * @Version 1.0
 **/
@SpringBootTest
public class AccessInformationMapperTest {
    @Resource
    private AccessInformationMapper accessInformationMapper;

    //不可行
//    @Test
//    void pageTest(){
//        Page<AccessInformation> page = new Page<>(2,5,14,true);
//        IPage<AccessInformation> page1 = accessInformationMapper.selectPageList(page);
//        Assertions.assertNotNull(page1);
//        System.out.println(page1);
//        System.out.println(page1.getPages());
//        List<AccessInformation> list = page1.getRecords();
//        list.forEach(System.out::println);
//        System.out.println(page1.getSize());
//        System.out.println(page1.getCurrent());
//        System.out.println(page1.getTotal());
//
//    }

//    @Test
//    void page(){
//        Long count = accessInformationMapper.selectCount();
//        Integer page = 1;
//        Integer size = 5;
//        Integer i = (page - 1) * size;
//        List<AccessInformation> accessInformations = accessInformationMapper.selectPageList(i, size);
//        Assertions.assertNotNull(accessInformations);
//        accessInformations.forEach(System.out::println);
//    }
}
