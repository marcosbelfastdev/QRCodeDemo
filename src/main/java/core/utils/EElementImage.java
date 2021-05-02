package core.utils;

import org.base.erbium.EElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class EElementImage {

    public static BufferedImage getBufferedImage(EElement element) throws IOException {
        String src = element.getAttribute("src");
        return ImageIO.read(new URL(src));
    }


}
