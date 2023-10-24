import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/saucedemo/cucumber/resources/features",
        glue = "saucedemo.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"}
)

public class runLogin {
}
