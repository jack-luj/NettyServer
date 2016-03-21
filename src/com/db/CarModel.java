package com.db;

import java.math.BigDecimal;

/**
 * Created by jack lu on 2016/3/21.
 */
public class CarModel {
    private int brand;
    private int series;
    private int modelYear;
    private String enginrType;
    private String disp;

    public CarModel(int brand, int series, int modelYear, String enginrType, String disp) {
        this.brand = brand;
        this.series = series;
        this.modelYear = modelYear;
        this.enginrType = enginrType;
        this.disp = disp;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getEnginrType() {
        return enginrType;
    }

    public void setEnginrType(String enginrType) {
        this.enginrType = enginrType;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }
}
