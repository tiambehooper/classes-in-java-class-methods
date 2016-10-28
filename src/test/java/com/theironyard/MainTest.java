package com.theironyard;

import com.github.javaparser.ParseException;
import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.util.SourceCodeExtractor;
import net.doughughes.testifier.util.TestifierAnnotationReader;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Testifier(sourcePath = "./src/main/java/com/theironyard/Main.java", clazz = Main.class)
public class MainTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier(method = "main", args = {String[].class})
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
    @Testifier(method = "main", args = {String[].class})
    public void mainMethodShouldToggleLightSwitchStateTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        Main.main(new String[]{});

        /* assert */
        TestifierAnnotationReader reader = new TestifierAnnotationReader(this);
        String source = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());

        assertThat("The main() method should call a method named toggle() on a variable named lightSwitch.",
                source, RegexMatcher.matches("^.*?MethodCallExpr\\[toggle\\] NameExpr\\[lightSwitch\\] \\/MethodCallExpr.*?$"));


    }

    @Test
    @Testifier(method = "main", args = {String[].class})
    public void mainMethodShouldReadFromLightSwitchOnPropertyTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        Main.main(new String[]{});

        /* assert */
        TestifierAnnotationReader reader = new TestifierAnnotationReader(this);
        String source = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());

        assertThat("The main() method should concatenate the lightSwitch object's 'on' property to a string when printing it out.",
                source, RegexMatcher.matches("^.*?StringLiteralExpr\\[The light switch is on: \\] plus FieldAccessExpr\\[on\\] NameExpr\\[lightSwitch\\].*?$"));


    }


}