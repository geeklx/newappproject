package com.haier.cellarette.jiuzhidao_demo.musics.splash_login.utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

public class VerificationUtils {


    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    public static void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    public static boolean validateAccount(String account, TextInputLayout input_number) {
        if (TextUtils.isEmpty(account)) {
            showError(input_number, "电话号码不能为空。");
            return false;
        }

        if (!isMobile(account)) {
            showError(input_number, "电话号码不正确。");
            return false;
        }

        return true;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 验证验证码
     *
     * @param account
     * @return
     */
    public static boolean validateCode(String account, TextInputLayout input_code) {
        if (TextUtils.isEmpty(account)) {
            showError(input_code, "验证码不能为空。");
            return false;
        }

        if (account.length() < 6) {
            int length = account.length();
            showError(input_code, "验证码输入不正确,不小于6位。");
            return false;
        }
        return true;
    }

}
