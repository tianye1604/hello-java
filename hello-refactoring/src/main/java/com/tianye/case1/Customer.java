package com.tianye.case1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客，拥有数据和相应的访问函数
 */
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for" + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental)rentals.nextElement();

            //determine amounts for each line
            thisAmount = amountFor(each);

            //add frequent renter points
            frequentRenterPoints++ ;

            //add bonus for a two day new release rental
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) {
                frequentRenterPoints++ ;
            }

            //show figures for this rental
            result += "\t" + each.getMovie().getitle() + "t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " +String.valueOf(frequentRenterPoints) +" frequent renter points";
        return result;
    }

    /**
     * 提取函数步骤：
     * 1、找出代码的逻辑泥团，并运用Extact Method
     * 2、找出这段逻辑泥团代码中的局部变量和参数
     * 3、任何不被修改的变量都可以被当成参数传入新的函数
     * 4、如果只有一个变量会被修改，可以把它当作返回值
     * @param aRental
     * @return
     */
    private double amountFor(Rental aRental) {
        double result = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if(aRental.getDaysRented() > 2){
                    result += (aRental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(aRental.getDaysRented() > 3){
                    result += (aRental.getDaysRented()-3) * 1.5;
                }
                break;
        }
        return result;
    }
}
