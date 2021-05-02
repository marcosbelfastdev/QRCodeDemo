package sites.qrgen.attributes;

import org.base.erbium.EDriver;
import org.base.erbium.EElement;
import org.openqa.selenium.By;

public class HomePageAttributes {

    EDriver $driver;

    public HomePageAttributes(EDriver driver) {
        $driver = driver;
    }

    protected EElement editorTexto() {
        return $driver.find(By.id("content"));
    }


}
