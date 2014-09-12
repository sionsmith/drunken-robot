package drunken.me.web.impl;

import drunken.me.web.exception.CarNotFoundException;
import drunken.me.web.model.Car;
import drunken.me.web.service.CarService;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by sionsmith on 9/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class CarServiceImplTest {

    @Autowired
    private CarService carService;

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");

    @org.junit.Test
    public void testAddCar() throws Exception {
        carService.getCar(1);
        System.out.println("test");

    }

    @Test
    public void testGetCar() throws Exception {
        assertNotNull(carService);
        Car bmw = carService.getCar(0);
        Car kia = carService.getCar(4);
        assertNotNull(bmw);
        assertNotNull(kia);

        //check they have the correct details.
        assertEquals("1 Series", bmw.getModel());
        assertEquals("Ceed", kia.getModel());
    }

    @Test
    public void testCarNotFoundEception() throws Exception {
       try{
           Car toFind = carService.getCar(10);
           fail("we should not have got here.");
       }catch (CarNotFoundException ex){}

    }

    @Test
    public void testAddAndDelete() throws Exception {
        assertEquals(7, carService.getCarCount());
        //add new car
        Car newCar = new Car(-1, "Fiat", "500", new DateTime(formatter.parseDateTime("04-02-2011")));
        Car addedCar = carService.addCar(newCar);
        assertTrue(addedCar.getId() > 0);
        assertEquals(8, carService.getCarCount());

        //delete added car
        carService.deleteCar(addedCar.getId());
        try{
            Car deletedCar = carService.getCar(addedCar.getId());
            fail("we should not have got here.");
        }catch (CarNotFoundException ex){}
    }

}
