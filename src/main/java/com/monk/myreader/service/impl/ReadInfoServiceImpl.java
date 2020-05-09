package com.monk.myreader.service.impl;

import com.monk.myreader.bean.ReadInfo;
import com.monk.myreader.bean.ReadInfoExample;
import com.monk.myreader.mapper.ReadInfoMapper;
import com.monk.myreader.service.ReadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/4/16 1:10
 */
@Service
public class ReadInfoServiceImpl implements ReadInfoService{

    @Autowired
    ReadInfoMapper readInfoMapper;

    @Override
    public ReadInfo selectById(Long id) {
        ReadInfoExample example = new ReadInfoExample();
        example.createCriteria().andIdEqualTo(id);
        List<ReadInfo> readInfoList = readInfoMapper.selectByExample(example);
        return readInfoList.size()>0?readInfoList.get(0):null;
    }

    @Override
    public List<ReadInfo> selectByUserId(Long id) {
        ReadInfoExample example = new ReadInfoExample();
        example.createCriteria().andUserIdEqualTo(id);
        return readInfoMapper.selectByExample(example);
    }

    @Override
    public List<ReadInfo> selectByBookId(Long id) {
        ReadInfoExample example = new ReadInfoExample();
        example.createCriteria().andBookIdEqualTo(id);
        return readInfoMapper.selectByExample(example);
    }

    @Override
    public int insert(Long bookId, Long userId) {
        ReadInfo readInfo = new ReadInfo();
        readInfo.setBookId(bookId);
        readInfo.setUserId(userId);
        readInfo.setDuration(0L);
        readInfo.setIsDownload(false);
        readInfo.setStart(0L);
        return readInfoMapper.insert(readInfo);
    }

    @Override
    public int update(Long id, Long start, Long diffDuration) {
        ReadInfoExample example = new ReadInfoExample();
        example.createCriteria().andIdEqualTo(id);

        ReadInfo readInfo = selectById(id);
        readInfo.setStart(start);
        readInfo.setDuration(readInfo.getDuration()+diffDuration);
        return readInfoMapper.updateByExample(readInfo, example);
    }

    @Override
    public int download(Long id) {
        ReadInfoExample example = new ReadInfoExample();
        example.createCriteria().andIdEqualTo(id);

        ReadInfo readInfo = selectById(id);
        readInfo.setIsDownload(true);
        return readInfoMapper.updateByExample(readInfo, example);
    }

    @Override
    public int count() {
        ReadInfoExample example = new ReadInfoExample();
        return readInfoMapper.countByExample(example);
    }


}
