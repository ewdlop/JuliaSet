import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MandelbrotColor {
    public static void main(String[] args) throws Exception {
        int width = 7680, height = 4280, max = 3000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB((float)(0.0/6.0)+(float)(i/500.0), 1, i/(i+8f));
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = 2+((col-width)/2)*4.0/width;
                double c_im = ((height)/2-row )*4.0/height;
                double x = 0, y = 0;
                int iteration = 0;
                while (x*x+y*y < 9 && iteration < max) {
                    double x_new = x*x*x-3*x*y*y+c_re;
                    y = 3*x*x*y-y*y*y+c_im;
                    x = x_new;
                    iteration++;
                } 
                if (iteration < max) image.setRGB(col, row, colors[iteration]);
                else image.setRGB(col, row, black);
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot.png"));
    }
}
