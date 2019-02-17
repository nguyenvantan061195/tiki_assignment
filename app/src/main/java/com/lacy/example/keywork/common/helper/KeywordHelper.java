package com.lacy.example.keywork.common.helper;

import android.text.TextUtils;

import com.lacy.example.keywork.common.Constants;
import com.lacy.example.keywork.common.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 17, February, 2019 10:29 AM
 */
public class KeywordHelper {
    /**
     * Requiremnet - If the keyword is more than one word, then display in two lines.
     *
     * @param keyword
     * @return optimize keyword
     */
    public static String keywordTokenizer(String keyword) {
        if (!StringUtil.isNullOrEmpty(keyword)
                && keyword.length() > 0
                && keyword.contains(" ")) {
            StringBuilder result = new StringBuilder(Constants.STR_BLANK);
            String[] keyString = keyword.split(" ");
            List<String> p1KeyString = new ArrayList<>(Arrays.asList(keyString).subList(0, keyString.length / 2));
            List<String> p2KeyString = new ArrayList<>(Arrays.asList(keyString).subList(keyString.length / 2, keyString.length));
            String p1Keyword = TextUtils.join(" ", p1KeyString);
            String p2Keyword = TextUtils.join(" ", p2KeyString);
            if (keyString.length > 2) {
                // kiem tra neu p2 >p1 thi chuyen tu cua p2 sang p1
                if (p1Keyword.length() / p2Keyword.length() >= 2) {
                    p2KeyString.add(p1KeyString.get(p1KeyString.size() - 1));
                    p1KeyString.remove(p1KeyString.size() - 1);
                } else if (p2Keyword.length() / p1Keyword.length() >= 2) {
                    p1KeyString.add(p2KeyString.get(0));
                    p2KeyString.remove(0);
                }
            }
            p1Keyword = TextUtils.join(" ", p1KeyString);
            p2Keyword = TextUtils.join(" ", p2KeyString);
            result.append(p1Keyword);
            result.append("\n");
            result.append(p2Keyword);
            return result.toString();
        } else return keyword;
    }
}
