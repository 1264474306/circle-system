package com.iswust.serves;

import com.iswust.model.Theme;

import java.util.List;


public interface IThemeServes {
    List<Theme> themeGetAll();

    void themeAdd(Theme theme);

    Theme themeBrowse(Theme theme);

    Theme themeGet(Integer theme_id);

    Integer themeGetIdByPostId(Integer post_id);
}
