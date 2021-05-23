package com.iswust.serves.impl;


import com.iswust.dao.IThemeDao;
import com.iswust.model.Theme;
import com.iswust.serves.IThemeServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServes implements IThemeServes {
    @Autowired
    IThemeDao themeDao;

    @Override
    public List<Theme> themeGetAll() {
        return themeDao.themeGetAll();
    }

    @Override
    public void themeAdd(Theme theme) {
        theme.setBrowse_num(0);
        theme.setContent_num(0);
        themeDao.themeAdd(theme);
    }

    @Override
    public Theme themeBrowse(Theme theme) {
        themeDao.themeBrowse(theme.getId());
        return themeDao.themeGet(theme.getId());
    }

    @Override
    public Theme themeGet(Integer theme_id) {
        return themeDao.themeGet(theme_id);
    }

    @Override
    public Integer themeGetIdByPostId(Integer post_id) {
        return themeDao.themeGetIdByPostId(post_id);
    }
}
