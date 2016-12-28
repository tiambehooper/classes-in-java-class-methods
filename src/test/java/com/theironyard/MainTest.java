package com.theironyard;

import com.github.javaparser.ParseException;
import net.doughughes.testifier.exception.CannotFindMethodException;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.test.TestifierTest;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MainTest extends TestifierTest {

    @Test
    public void mainMethodShouldPrintSwitchStateIsOffThenOnTest() {
        /* arrange */

        /* act */
        Main.main(new String[]{});

        /* assert */
        OutputStreamInterceptor out = (OutputStreamInterceptor) System.out;
        assertThat("The main() method should print the state of the light switch two times.",
                out.getPrinted().size(), equalTo(2));
        assertThat("The main() method should print that the light switch is off first.",
                out.getPrinted().get(0), equalTo("The light switch is on: false"));
        assertThat("The main() method should print that the light switch is on second.",
                out.getPrinted().get(1), equalTo("The light switch is on: true"));
    }

    @Test
    public void mainMethodShouldToggleLightSwitchStateTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        String source = null;
        try {
            source = codeWatcher.getMainSourceCodeService().getDescriptionOfMethod("main", String[].class);
        } catch (CannotFindMethodException e) {
            fail(e.getMessage());
        }


        /* assert */
        assertThat("The main() method should call a method named toggle() on a variable named lightSwitch.",
                source, RegexMatcher.matches("^.*?MethodCallExpr NameExpr\\[lightSwitch\\] MethodName\\[toggle\\] MethodArguments \\/MethodArguments \\/MethodCallExpr.*?$"));

    }

    @Test
    public void mainMethodShouldReadFromLightSwitchOnPropertyTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        String source = null;
        try {
            source = codeWatcher.getMainSourceCodeService().getDescriptionOfMethod("main", String[].class);
        } catch (CannotFindMethodException e) {
            fail(e.getMessage());
        }

        /* assert */
        assertThat("The main() method should concatenate the lightSwitch object's 'on' property to a string when printing it out.",
                source, RegexMatcher.matches("^.*?StringLiteralExpr\\[The light switch is on: \\] plus NameExpr\\[lightSwitch\\] FieldAccessExpr\\[on\\].*?$"));


    }


}