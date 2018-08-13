package com.liuchuanzheng.qiekenao.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 10:05
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class MyApplication extends Application {
    public static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        initSomeThing();
    }

    private void initSomeThing() {
        application = this;
        initUtilCodeLibrary();
        initToastyLibrary();
    }

    private void initToastyLibrary() {
        //此处用默认的即可。不进行设置。
       /* Toasty.Config.getInstance()
                .setErrorColor(@ColorInt int errorColor) // optional
    .setInfoColor(@ColorInt int infoColor) // optional
    .setSuccessColor(@ColorInt int successColor) // optional
    .setWarningColor(@ColorInt int warningColor) // optional
    .setTextColor(@ColorInt int textColor) // optional
    .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
    .setToastTypeface(@NonNull Typeface typeface) // optional
                .setTextSize(int sizeInSp) // optional
    .apply(); // required*/
    }

    private void initUtilCodeLibrary() {
        // init it in the function of onCreate in ur Application
        Utils.init(this);
    }
}
