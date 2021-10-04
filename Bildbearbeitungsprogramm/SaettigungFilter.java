import java.awt.Color;
import java.util.ArrayList;
public class SaettigungFilter extends Filter
{

    public SaettigungFilter(String name)
    {
        super(name);
    }

    public void anwenden(Farbbild bild){

        

        for(int y = 0; y < bild.getHeight(); ++y){
            for(int x = 0; x < bild.getWidth(); ++x){
                Color c = bild.gibPunktfarbe(x, y);
                int[] rgb = {c.getRed(), c.getBlue(), c.getGreen()};
                int pos = 0;
                int max = 0;
                for(int i = 0; i < rgb.length; i++) {
                    if(rgb[i] > max) {
                        max = rgb[i];
                        pos = i;
                    }
                }
                rgb[pos]=255;
                bild.setzePunktfarbe(x, y, new Color(rgb[0], rgb[1], rgb[2]));
            }
        }
    }
}