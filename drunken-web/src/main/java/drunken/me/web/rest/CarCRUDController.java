package drunken.me.web.rest;

import drunken.me.web.model.Car;
import drunken.me.web.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by sionsmith on 11/09/2014.
 */
@RestController
@RequestMapping("/car")
public class CarCRUDController {

    private Logger logger = LoggerFactory.getLogger(CarCRUDController.class);

    private CarService carService;

    @Inject
    public CarCRUDController(@Named("carService") CarService carService)
    {
        this.carService=carService;
    }

    /**
     * Add's a car to the application
     */
    @RequestMapping(value = "/add",  method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Car addCar(@RequestBody Car car){

        Car addedCar = carService.addCar(car);

        if(addedCar != null){
            return addedCar;
        }else
            return null;
    }

    /**
     * Deletes a car if found in the application
     * @param carToUpdate
     * @return
     */
    @RequestMapping( value = "/update", method = RequestMethod.POST)
    public HttpStatus updateCar(@RequestBody Car carToUpdate){

        Car updatedCar = carService.updateCar(carToUpdate);

        //if car is returned then it is updated correctly.
        if(updatedCar != null)
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_REQUEST;
    }

    /**
     * Updates a car if found in the application
     * @param idOfCarToDelete
     * @return
     */
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.POST)
    public
    HttpStatus deleteCar(@PathVariable("id") Long idOfCarToDelete){
        logger.debug("Deleting car with id: " + idOfCarToDelete);
        return carService.deleteCar(idOfCarToDelete)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        
    }

    /**
     * Gets a car if found in the application. 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("id") Long id) {
        logger.debug("Looking for car with id: " + id);
        return carService.getCar(id);
    }

}
