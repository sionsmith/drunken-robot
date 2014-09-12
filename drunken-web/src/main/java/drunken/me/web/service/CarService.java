package drunken.me.web.service;

import drunken.me.web.exception.CarNotFoundException;
import drunken.me.web.model.Car;

/**
 * Created by sionsmith on 11/09/2014.
 */
public interface CarService {

    public Car getCar(long id) throws CarNotFoundException;

    public Car addCar(Car car);

    public long getCarCount();

    public Car updateCar(Car toUpdate) throws CarNotFoundException;

    public boolean deleteCar(long id) throws CarNotFoundException;
}
