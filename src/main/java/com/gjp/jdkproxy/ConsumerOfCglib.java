package com.gjp.jdkproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ConsumerOfCglib {

    public static void main(String[] args) {
        final Producer producer = new Producer();
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor(){
            /**
             * 作用：执行被代理对象的任何借口方法都会经过该方法
             * @param proxy：代理对象的引用
             * @param method：当前执行的方法
             * @param args：当前执行方法所需的参数
             * @return：和被代理对象方法有相同的返回值
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //增强的代码
                Object returnValue = null;
                //1.获取方法执行的参数
                Float money = (Float) args[0];
                //2.判断方式是不是销售
                if("saleProduct".equals(method.getName())){
                    returnValue = method.invoke(producer,money*0.8f);
                }
                return returnValue;
            }
        });

        System.out.println("before");
        cglibProducer.saleProduct(10000f);
        System.out.println("after");
    }
}
