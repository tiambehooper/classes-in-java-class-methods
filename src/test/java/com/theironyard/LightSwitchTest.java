package com.theironyard;

import net.doughughes.testifier.exception.CannotAccessFieldException;
import net.doughughes.testifier.exception.CannotAccessMethodException;
import net.doughughes.testifier.exception.CannotFindFieldException;
import net.doughughes.testifier.exception.CannotFindMethodException;
import net.doughughes.testifier.test.TestifierTest;
import net.doughughes.testifier.util.Invoker;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

public class LightSwitchTest extends TestifierTest {

    @Test
    public void lightSwitchDefaultsToOffTest() {
        /* arrange */
        LightSwitch lightSwitch = new LightSwitch();

        try {
            /* act */
            boolean result = (boolean) Invoker.readProperty(lightSwitch, "on");

            /* assert */
            assertThat("The light switch's on property should default to not being on.",
                    result, equalTo(false));
        } catch (CannotFindFieldException | CannotAccessFieldException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void toggleInvertsOnPropertyTest() {
        /* arrange */
        LightSwitch lightSwitch = new LightSwitch();


        try {
            /* act */
            Invoker.invoke(lightSwitch, "toggle");
            boolean toggle1 = (boolean) Invoker.readProperty(lightSwitch, "on");
            Invoker.invoke(lightSwitch, "toggle");
            boolean toggle2 = (boolean) Invoker.readProperty(lightSwitch, "on");

            /* assert */
            assertThat("The light switch's toggle() method should toggle the light switch's on property",
                    toggle1, equalTo(true));
            assertThat("The light switch's toggle() method should toggle the light switch's on property",
                    toggle2, equalTo(false));
        } catch (CannotFindMethodException | CannotAccessMethodException | CannotFindFieldException | CannotAccessFieldException e) {
            fail(e.getMessage());
        }

    }


}