package com.design.pattern.builder;

/**
 * @program: pattern
 * @description: builder设计模式
 * @author: Li Tong
 * @create: 2019-11-25 11:13
 **/
public class Producer {
    private int id;
    private String name;
    private int type;
    private float price;

    public Producer(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.price=builder.price;
        this.type=builder.type;
    }

    public static final class Builder{
        private int id;
        private String name;
        private int type;
        private float price;
        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder type(int type){
            this.type=type;
            return this;
        }
        public Builder price(float price){
            this.price=price;
            return this;
        }
        public Producer build(){
            return new Producer(this);
        }
    }
}
