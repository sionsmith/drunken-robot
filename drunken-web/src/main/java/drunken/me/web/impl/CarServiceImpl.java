package drunken.me.web.impl;

import drunken.me.web.exception.CarNotFoundException;
import drunken.me.web.model.Car;
import drunken.me.web.service.CarService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
    private AtomicInteger atomicInteger=new AtomicInteger(0);


    public CarServiceImpl()
    {
        init();
    }

    // In-memory list
    private List<Car> cars = new ArrayList<Car>();

    private void init()
    {
        addCar("BMW", "1 Series", new DateTime(formatter.parseDateTime("19-04-2005")));
        addCar("Renault", "Clio", new DateTime(formatter.parseDateTime("27-09-2010")));
        addCar("Volkswagen", "Golf", new DateTime(formatter.parseDateTime("01-03-2009")));
        addCar("Vauxhall", "Zafira", new DateTime(formatter.parseDateTime("21-01-2014")));
        addCar("Kia", "Ceed", new DateTime(formatter.parseDateTime("20-05-2011")));
        addCar("Toyota", "Aygo", new DateTime(formatter.parseDateTime("13-08-2012")));
        addCar("Ford", "Mondeo", new DateTime(formatter.parseDateTime("17-11-2011")));
    }

    @Override
    public Car getCar(long id) throws CarNotFoundException {
        logger.info("Retrieving id {}",id);
        for (Car car : cars) {
            if (car.getId()==id){
                return car;
            }
        }
        //cant find anything throw exception;
        throw new CarNotFoundException("Failed to find car using id: " + id);
    }

    @Override
    //used for in memory database population.
    public Car addCar(Car car) {
        //quickly validate car.
        if(car != null){
            if(StringUtils.hasText(car.getMake()) && StringUtils.hasText(car.getModel()) && car.getManufactureDate() != null){
                int idTodSet=atomicInteger.getAndIncrement();
                Car newCarToAdd = new Car(idTodSet, car.getMake(), car.getModel(), car.getManufactureDate());
                cars.add(newCarToAdd);
                return newCarToAdd;
            }
        }
        return null;
    }

    public Car addCar(String make, String model, DateTime manufactureDate) {
        Car car = new Car(-1, make, model, manufactureDate);
        return addCar(car);
    }

    @Override
    public long getCarCount() {
        return cars.size();
    }

    @Override
    public Car updateCar(Car modified) throws CarNotFoundException{
        //find car to update
        if(modified.getId() != null){
            Car toUpdate = getCar(modified.getId());
            toUpdate.setMake(modified.getMake());
            toUpdate.setManufactureDate(modified.getManufactureDate());
            toUpdate.setModel(modified.getModel());
        }
        //cannot find car to update.
        return null;
    }


    @Override
    public boolean deleteCar(long id) throws CarNotFoundException {
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
