package BadDriving;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.*;


public class Car {
    final private GpioController gpio;
    final protected GpioPinDigitalOutput thrustPin26;
    final protected GpioPinDigitalOutput thrustPin27;
    final protected GpioPinDigitalOutput thrustPin28;
    final protected GpioPinDigitalOutput thrustPin29;


    public Car() {
        try {
            System.out.println("initializing Car object");

            gpio = GpioFactory.getInstance();
            System.out.println("gpio instance at 24 in car" + gpio);
            thrustPin26 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "thrustPin26", PinState.LOW);
            thrustPin27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "thrustPin27", PinState.LOW);
            thrustPin28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "thrustPin28", PinState.LOW);
            thrustPin29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "thrustPin29", PinState.LOW);
            System.out.println("pin 26, " + thrustPin26 + " pin 27, " + thrustPin27 + " pin 28, " + thrustPin28+ " pin 29, " + thrustPin29);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Car was not initialized");
        }
    }


    public void steeringButtonDepressed() throws InterruptedException {
        Reciever r = new Reciever();
        try {
            switch (r.direction) {
                case "forward":
                    forward();
                    System.out.println("direction in case forward " + r.direction);
                    break;

                case "stop":
                    stop();
                    System.out.println("direction in case stop " + r.direction);
                    break;

                case "left":
                    left();
                    System.out.println("direction in case left " + r.direction);
                    break;

                case "right":
                    right();
                    System.out.println("direction in case right " + r.direction);
                    break;

                default:
                    stop();
                    System.out.println("direction in case stop " + r.direction);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Directional methods are not working");
        }
    }

    public void steeringButtonReleased() throws InterruptedException {
        Reciever r = new Reciever();
        switch (r.direction){
            case "forward release":
                forwardRelease();
                break;

            case "left release":
                leftRelease();
                break;

            case "right release":
                rightRelease();
                break;

            default: stop();
                break;
        }
    }

//  Everything below this line is within steeringButtonDepressed

    public void forward() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.high();
        wheelTwo.high();
        wheelThree.high();
        wheelFour.high();
    }

    public void stop() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.high();
        wheelOne.toggle();
        wheelTwo.high();
        wheelTwo.toggle();
        wheelThree.high();
        wheelThree.toggle();
        wheelFour.high();
        wheelFour.toggle();
    }

    public void left() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.high();
        wheelOne.toggle();
        wheelTwo.high();
        wheelTwo.toggle();
        wheelThree.high();
        wheelFour.high();
    }

    public void right() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.high();
        wheelTwo.high();
        wheelThree.high();
        wheelThree.toggle();
        wheelFour.high();
        wheelFour.toggle();
    }
//  Everything above this line is within steeringButtonDepressed

//  Everything below this line is within steeringButtonReleased

    public void forwardRelease() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.low();
        wheelTwo.low();
        wheelThree.low();
        wheelFour.low();
    }

    public void rightRelease() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.low();
        wheelTwo.low();
        wheelThree.low();
        wheelFour.low();
    }

    public void leftRelease() throws InterruptedException {

        GpioPinDigitalOutput wheelOne = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
        GpioPinDigitalOutput wheelTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        GpioPinDigitalOutput wheelThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
        GpioPinDigitalOutput wheelFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);

        wheelOne.high();
        wheelOne.toggle();
        wheelTwo.high();
        wheelTwo.toggle();
        wheelThree.high();
        wheelFour.high();
    }

    //  Everything above this line is within steeringButtonReleased


}
