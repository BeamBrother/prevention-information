package com.bl.ep.service.impl;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.mapper.HealthCodeMapper;
import com.bl.ep.service.HealthCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName HealthCodeServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:31
 * @Version 1.0
 **/
@Service("healthCodeService")
public class HealthCodeServiceImpl implements HealthCodeService {
    @Resource
    private HealthCodeMapper healthCodeMapper;

    @Override
    public HealthCode findByPrimaryKey(String id) {
        return healthCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public HealthCode findByStudent(Long id) {
        return healthCodeMapper.selectByStudent(id);
    }

    @Override
    public List<HealthCode> findAll() {
        return healthCodeMapper.selectAll();
    }

    @Override
    public HealthCode findByStudentAndNo(String sno) {
        return healthCodeMapper.selectByStudentAndNo(sno);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addHealthCode(HealthCode healthCode) {
        return healthCodeMapper.insertBySelective(healthCode);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByHealthCode(HealthCode healthCode) {
        return healthCodeMapper.updateByHealthCode(healthCode);
    }
}
