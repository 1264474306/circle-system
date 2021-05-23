package com.iswust.dao;

import com.iswust.model.Theme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IThemeDao {
    List<Theme> themeGetAll();

    void themeAdd(Theme theme);

    void themeContentNum(Integer theme_id);

    void themeUnContentNum(Integer theme_id);

    void themeBrowse(Integer id);

    Theme themeGet(Integer theme_id);

    Integer themeGetIdByPostId(Integer post_id);
}
