package com.hellofresh.proxy.mock;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import com.hellofresh.proxy.mock.transformers.PathTransformer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    private static final List<Class<?>> EXTENSION_CLASSES = Arrays.asList(PathTransformer.class);

    public static void main(String[] args) {
        args = addExtensionsIfExist(args);
        printArgs(args);
        new WireMockServerRunner().run(args);
    }

    private static String[] addExtensionsIfExist(String[] args) {
        return getExtensionClasses()
                .map(extensions -> addExtensions(args, extensions))
                .orElse(args);
    }

    private static String[] addExtensions(String[] args, String extensions) {
        String argsWithExtensions[] = new String[args.length + 2];
        for (int i = 0; i < args.length; i++) {
            argsWithExtensions[i] = args[i];
        }
        argsWithExtensions[argsWithExtensions.length - 2] = "--extensions";
        argsWithExtensions[argsWithExtensions.length - 1] = extensions;
        return argsWithExtensions;
    }

    private static Optional<String> getExtensionClasses() {
        return EXTENSION_CLASSES.stream()
                .map(Class::getName)
                .reduce((val1, val2) -> val1 + "," + val2);
    }

    private static void printArgs(String[] args) {
        System.out.println("args");
        Stream.of(args).forEach(System.out::println);
    }
}
