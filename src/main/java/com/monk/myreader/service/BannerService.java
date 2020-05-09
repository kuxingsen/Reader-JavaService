package com.monk.myreader.service;

import com.monk.myreader.bean.Banner;

import java.util.List;


public interface BannerService{
    Banner selectById(Long id);
    int update(Long id, Banner banner);

    List<Banner> selectBannerByCategoryId(Long categoryId);

    int count();
}
