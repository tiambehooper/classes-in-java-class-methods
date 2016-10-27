package com.theironyard;

import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Testifier(sourcePath = "./src/main/java/com/theironyard/LightSwitch.java", clazz = LightSwitch.class)
public class LightSwitchTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier
    public void lightSwitchDefaultsToOffTest() {
        /* arrange */
        LightSwitch lightSwitch = new LightSwitch();

        /* act */

        /* assert */
        assertThat("The light switch's on property should default to not being on.",
                lightSwitch.on, equalTo(false));
    }

    @Test
    @Testifier
    public void toggleInvertsOnPropertyTest() {
        /* arrange */
        LightSwitch lightSwitch = new LightSwitch();

        /* act */
        lightSwitch.toggle();
        boolean toggle1 = lightSwitch.on;
        lightSwitch.toggle();
        boolean toggle2 = lightSwitch.on;

        /* assert */
        assertThat("The light switch's toggle() method should toggle the light switch's on property",
                toggle1, equalTo(true));
        assertThat("The light switch's toggle() method should toggle the light switch's on property",
                toggle2, equalTo(false));
    }


}