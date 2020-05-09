package com.monk.myreader.service.impl;

import com.monk.myreader.bean.Banner;
import com.monk.myreader.bean.BannerExample;
import com.monk.myreader.mapper.BannerMapper;
import com.monk.myreader.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public Banner selectById(Long id) {
        BannerExample bannerExample = new BannerExample();
        bannerExample.createCriteria().andIdEqualTo(id);//todo
        List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);

        return bannerList.size()==0?null:bannerList.get(0);
    }

    @Override
    public List<Banner> selectBannerByCategoryId(Long categoryId) {

        BannerExample bannerExample = new BannerExample();
        bannerExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
        return bannerList;
    }

    @Override
    public int count() {
        BannerExample bannerExample = new BannerExample();
        return bannerMapper.countByExample(bannerExample);
    }

    @Override
    public int update(Long id, Banner banner) {

        BannerExample bannerExample = new BannerExample();
        bannerExample.createCriteria().andIdEqualTo(id);
        return bannerMapper.updateByExample(banner,bannerExample);
    }

}
