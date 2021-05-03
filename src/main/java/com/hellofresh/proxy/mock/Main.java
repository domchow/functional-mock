package com.hellofresh.proxy.mock;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        new WireMockServerRunner().run(args);
    }
}
