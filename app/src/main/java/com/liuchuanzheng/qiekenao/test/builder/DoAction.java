package com.liuchuanzheng.qiekenao.test.builder;

/**
 * @author 刘传政
 * @date 2018/8/23 0023 11:08
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class DoAction {

    public void a(){
        User user = User.newBuilder("xiaoming")
                .age(2)
                .color("red")
                .build();

    }
}
