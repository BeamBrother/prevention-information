package com.bl.ep.service.impl;

import com.bl.ep.bean.AccessInformation;
import com.bl.ep.mapper.AccessInformationMapper;
import com.bl.ep.service.AccessInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AccessInformationServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:37
 * @Version 1.0
 **/
@Service("accessInformationService")
public class AccessInformationServiceImpl implements AccessInformationService {
    @Resource
    private AccessInformationMapper accessInformationMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addSelective(AccessInformation accessInformation) {
        return accessInformationMapper.insertSelective(accessInformation);
    }

    @Override
    public AccessInformation findByPrimaryKey(String id) {
        return accessInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccessInformation> findByTimeAndId(Date start, Date end) {
        return accessInformationMapper.selectByTimeAndId(start, end);
    }

    @Override
    public List<AccessInformation> findAll() {
        return accessInformationMapper.selectAll();
    }

    @Override
    public List<AccessInformation> findAllByStudent(Long id) {
        return accessInformationMapper.selectAllByStudent(id);
    }

    @Override
    public List<AccessInformation> findAccessInfoByStudent(String no, String name) {
        return accessInformationMapper.selectBySnoAndSname(no, name);
    }
}
