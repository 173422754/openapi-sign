package com;

import java.util.UUID;

/**
 * @Author zhongjingyun
 * @Date 2020/12/3 下午5:27
 **/
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 60 ; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }
    }



}
