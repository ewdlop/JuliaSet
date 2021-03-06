import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class JuliaSetColor {
    public static void main(String[] args) throws Exception {
        int width = 1920, height = 1080, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB((float)(-i/500.0)+(float)(5.0/8.0), 1, i/(i+2f));
        }

        double c_re = -0.4;
        double c_im = 0.6;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double xold = (col-width/2)*(4.0/width);
                double yold = (height/2-row )*(4.0/height);
                double x = xold, y = yold;
                int iteration = 0;
                while (x*x+y*y < 4 && iteration < max) {
                    double x_new = x*x*x-3*x*y*y + c_re;
                    y =3*x*x*y-y*y*y + c_im;
                    x = x_new;
                    iteration++;
                } 
                if (iteration < max) image.setRGB(col, row, colors[iteration]);
                else image.setRGB(col, row, black);
            }
        }

        ImageIO.write(image, "png", new File("julia.png"));
    }
}
