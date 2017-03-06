/**
 * In this exercise you will add a method to the provided LightSwitch class and
 * then use that class in the main method, as instructed below.
 * <p>
 * You should first follow the instructions in the LightSwitch class.
 */
public class Main {

    public static void main(String[] args) {

        // todo: create a new instance of the LightSwitch and store it in a variable named lightSwitch.
        LightSwitch lightSwitch = new LightSwitch();




        // todo: print the state of the light switch. This should read "The light switch is on: true" or "The light switch is on: false" depending on the state of the lightSwitch.
        System.out.println("The light switch is on: " + lightSwitch.on );




        // todo: toggle the state of the light switch
       lightSwitch.toggle();






        // todo: print the state of the light switch again. This should read "The light switch is on: true" or "The light switch is on: false" depending on the state of the lightSwitch.
        System.out.println("The light switch is on: " + lightSwitch.on);
    }
}
