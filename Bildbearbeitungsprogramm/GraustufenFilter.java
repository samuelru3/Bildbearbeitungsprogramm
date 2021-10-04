import java.awt.Color;

public class GraustufenFilter extends Filter
{

    public GraustufenFilter(String name)
    {
        super(name);
    }

    public void anwenden(Farbbild bild){
        for(int y = 0; y < bild.getHeight(); ++y){
            for(int x = 0; x < bild.getWidth(); ++x){
                Color c = bild.gibPunktfarbe(x, y);
                int helligkeit = c.getRed() + c.getBlue() + c.getGreen();
                int rgb= helligkeit / 3;
                
                bild.setzePunktfarbe(x, y, new Color(rgb, rgb, rgb));
                
                
                // if(helligkeit < 255){
                    // bild.setzePunktfarbe(x, y, Color.BLACK);
                // }
                // else if(helligkeit < 510){
                    // bild.setzePunktfarbe(x, y, Color.GRAY);
                // }
                // else{
                    // bild.setzePunktfarbe(x, y, Color.LIGHT_GRAY); 
                // }
            }
        }
    }
}