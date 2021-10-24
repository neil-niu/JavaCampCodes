package io.kimmking.spring02;

import org.aspectj.lang.annotation.After;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Aop3WithDynamicProxy implements InvocationHandler {
    private Object proxied;
    private BeforeOp beforeop;
    private AfterOp afterOp;


    public Aop3WithDynamicProxy (Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (beforeop != null) {
            beforeop.before();
        }
        Object object = method.invoke(proxied, args);
        if (afterOp != null) {
            afterOp.after();
        }
        return object;
    }

    public void setBeforeop(BeforeOp beforeop) {
        this.beforeop = beforeop;
    }

    public void setAfterOp(AfterOp afterOp) {
        this.afterOp = afterOp;
    }
}
