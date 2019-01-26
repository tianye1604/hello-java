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
            thisAmount = each.getCharge();

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

}