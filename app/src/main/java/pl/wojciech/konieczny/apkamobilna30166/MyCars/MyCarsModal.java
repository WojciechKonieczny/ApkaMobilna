package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars_table")
public class MyCarsModal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mark;
    private String model;
    private String fuelType;
    private String year;
    private String engine;

    public MyCarsModal(String mark, String model, String fuelType, String year, String engine) {
        this.mark = mark;
        this.model = model;
        this.fuelType = fuelType;
        this.year = year;
        this.engine = engine;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}