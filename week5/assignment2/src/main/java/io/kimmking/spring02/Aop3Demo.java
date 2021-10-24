package io.kimmking.spring02;

import io.kimmking.aop.ISchool;

import java.lang.reflect.Proxy;

public class Aop3Demo {

    public static void callSchool(ISchool iSchool) {
        iSchool.ding();
    }

    public static void main(String[] args) {

        ISchool school = new SchoolWithoutSpring();
        callSchool(school);

        BeforeOp beforeOp = new BeforeOp() {
            @Override
            public void before() {
                System.out.println("    ====>begin ding... ");
            }
        };

        AfterOp afterOp = new AfterOp() {
            @Override
            public void after() {
                System.out.println("    ====>after ding... ");
            }
        };

        Aop3WithDynamicProxy aop3WithDynamicProxy = new Aop3WithDynamicProxy(school);
        aop3WithDynamicProxy.setAfterOp(afterOp);
        aop3WithDynamicProxy.setBeforeop(beforeOp);

        ISchool proxy = (ISchool) Proxy.newProxyInstance(
                ISchool.class.getClassLoader(),
                new Class[] {ISchool.class},
                aop3WithDynamicProxy
        );
        callSchool(proxy);
    }
}
