package com.liuchuanzheng.qiekenao.test.builder;

/**
 * @author 刘传政
 * @date 2018/8/23 0023 10:57
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class User {
    private final String name;
    private int age;
    private String color;

    private User(Builder builder) {
        name = builder.name;
        age = builder.age;
        color = builder.color;
    }

    public static Builder newBuilder(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String name;
        private int age;
        private String color;

        private Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
