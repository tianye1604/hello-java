package com.tianye.case1;

/**
 * 表示某个顾客租了一部影片
 */
public class Rental {

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        this._movie = movie;
        this._daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }


    /**
     * 提取函数 Extact Method：
     * 1、找出代码的逻辑泥团，并运用Extact Method
     * 2、找出这段逻辑泥团代码中的局部变量和参数
     * 3、任何不被修改的变量都可以被当成参数传入新的函数
     * 4、如果只有一个变量会被修改，可以把它当作返回值
     * @return
     */
    /**
     * Move Method
     * 1、发现这个函数使用了来自Rental类的信息，却没有使用来自Customer类的信息
     * 2、函数应该放在它所使用的数据的所属对象内，所以amountFor()应该移到Rental类去
     * @return
     */
    double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if(getDaysRented() > 2){
                    result += (getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(getDaysRented() > 3){
                    result += (getDaysRented()-3) * 1.5;
                }
                break;
        }
        return result;
    }

    /**
     * 提炼 常客积分计算
     * @return
     */
    int getFrequentRenterPoints() {
        if((getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }
}
