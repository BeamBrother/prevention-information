package com.bl.ep.service.impl;

import com.bl.ep.bean.NucleicAcidDetect;
import com.bl.ep.mapper.NucleicAcidDetectMapper;
import com.bl.ep.service.NucleicAcidDetectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName NucleicAcidDetectServiceImpl
 * @Description 核酸检测信息 业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/17 15:35
 * @Version 1.0
 **/
@Service("nucleicAcidDetectService")
public class NucleicAcidDetectServiceImpl implements NucleicAcidDetectService {
    @Resource
    private NucleicAcidDetectMapper nucleicAcidDetectMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addSelective(NucleicAcidDetect nucleicAcidDetect) {
        return nucleicAcidDetectMapper.insertSelective(nucleicAcidDetect);
    }

    @Override
    public List<NucleicAcidDetect> findByUserNo(String no) {
        return nucleicAcidDetectMapper.selectByUserNo(no);
    }

    @Override
    public NucleicAcidDetect findByPrimaryKey(String id) {
        return nucleicAcidDetectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<NucleicAcidDetect> findAll() {
        return nucleicAcidDetectMapper.selectAll();
    }
}
