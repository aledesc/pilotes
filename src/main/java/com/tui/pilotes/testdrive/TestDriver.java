package com.tui.pilotes.testdrive;

import reactor.core.publisher.Flux;

public class TestDriver {

    private static Integer count= 0;

    public static void prnStr(String str){
        System.out.println("\nSignal: " + (count++).toString() + "\n------------------");
        System.out.println("-- "+str);
    }
    public static void main(String[] args) {
        Flux<String> fStr= Flux.just("Hello", "World");

        fStr.subscribe(TestDriver::prnStr);
        fStr.subscribe(System.out::println);

    }
}
