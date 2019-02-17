package com.lacy.example.keywork.view;

import com.lacy.example.keywork.core.base.BaseVP;

import java.util.List;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 12:31 PM
 */
public interface MainView extends BaseVP.BaseView {
    void getAllKeywordSuccess(List<String> keywords);
}
