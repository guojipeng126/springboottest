package com.gjp.jdkproxy;

public class Producer implements IProducer{
    @Override
    public void saleProduct(float money) {
        System.out.println("销售产品，赚钱：" + money);
    }
}
