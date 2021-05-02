package core.utils;

import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageHandling {

    public static BufferedImage getBufferedImage(WebElement element) throws IOException {
        BufferedImage bufferedImage = null;
        String src = element.getAttribute("src");
        try {
            bufferedImage = ImageIO.read(new URL(src));
        } catch (Exception ignore) {

        }
        return bufferedImage;
    }


}
