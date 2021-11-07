package com.neil.db.model;

import java.util.Date;

/**
     * @author   neil niu
     * @date 2021/11/06 15:06
     */
    public class Order {
        /**
         *
         */
        private String productName;


    /**
         *
         */
        private int productNumber;
        /**
         *
         */
        private double productPrice;

        private int paymentMethod;

        private double paymentAmount;

        private int orderPoint;

        private double discountAmount;

        private Date orderTime;

        private Date paymentTime;



        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int  getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(int  paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public double  getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(double paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public double  getOrderPoint() {
            return orderPoint;
        }

        public void setOrderPoint(int orderPoint) {
            this.orderPoint = orderPoint;
        }

        public double  getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
        }

        public Date  getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Date orderTime) {
            this.orderTime = orderTime;
        }

        public Date  getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Date paymentTime) {
            this.paymentTime = paymentTime;
        }


        public int  getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public double  getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }


        @Override
        public String toString() {
            return "Order{" +
                    "productName='" + productName + '\'' +
                    ", productNumber=" + productNumber +
                    ", productPrice=" + productPrice +
                    ", paymentMethod=" + paymentMethod +
                    ", paymentAmount=" + paymentAmount +
                    ", orderPoint=" + orderPoint +
                    ", discountAmount=" + discountAmount +
                    ", orderTime=" + orderTime +
                    ", paymentTime=" + paymentTime +
                    '}';
        }
    }

