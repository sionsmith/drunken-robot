package drunken.me.web.impl;

import drunken.me.web.model.Car;
import drunken.me.web.service.CarService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sionsmith on 11/09/2014.
 */
@Named("carService")
public class CarServiceImpl implements CarService {
    private static Logger logger= LoggerFactory.getLogger(CarServiceImpl.class);


    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public CarServiceImpl()
    {
        init();
    }

    // In-memory list
    private List<Car> cars = new ArrayList<Car>();

    private void init()
    {
        addCar("BMW", "1 series", new DateTime());
        addCar("Renault", "clio", new DateTime());
    }

    @Override
    public Car getCar(long id) {
        logger.info("Retrieving id {}",id);
        for (Car car : cars) {
            if (car.getId()==id)
            {
                return car;
            }
        }
        //cant find anything return null;
        return null;
    }

    @Override
    //used for in memory database population.
    public Car addCar(Car car) {
        int idTodSet=atomicInteger.getAndIncrement();
        car.setId(idTodSet);
        cars.add(car);
        return car;
    }

    public Car addCar(String make, String model, DateTime manufactureDate) {
        Car car = new Car(-1, make, model, manufactureDate);

        //add inserted date
        car.setInsertedDate(new DateTime());

        return addCar(car);
    }

    @Override
    public long getCarCount() {
        return cars.size();
    }

    @Override
    public Car updateCar(Car toUpdate) {
//        Car toUpdate = getCar(id);
//        if(toUpdate != null){
//
//        }

        //cannot find car to update.
        return null;
    }

    @Override
    public boolean deleteCar(long id) {
        Car toDelete = getCar(id);
        if(toDelete != null){
            //delete the car
            cars.remove(toDelete);
            return true;
        }
        //can not find car.
        return false;
    }
}
