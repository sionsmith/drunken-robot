package drunken.me.web.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import drunken.me.web.util.CustomDateSerializer;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by sionsmith on 11/09/2014.
 */
public class Car {

    private long id;

    private String model;

    private String make;

    @JsonSerialize(using = CustomDateSerializer.class)
    private DateTime manufactureDate;

    private DateTime insertedDate;

    public Car() {
    }

    public Car(long id, String model, String make, DateTime manufactureDate) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.manufactureDate = manufactureDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public DateTime getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(DateTime manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public DateTime getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(DateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (insertedDate != null ? !insertedDate.equals(car.insertedDate) : car.insertedDate != null) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        if (manufactureDate != null ? !manufactureDate.equals(car.manufactureDate) : car.manufactureDate != null)
            return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
        result = 31 * result + (insertedDate != null ? insertedDate.hashCode() : 0);
        return result;
    }
}
