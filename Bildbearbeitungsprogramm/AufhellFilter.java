import java.awt.Color;

public class AufhellFilter extends Filter
{

    public AufhellFilter(String name)
    {
        super(name);
    }

    public void anwenden(Farbbild bild){
        for(int y = 0; y < bild.getHeight(); ++y){
            for(int x = 0; x < bild.getWidth(); ++x){
                Color c = bild.gibPunktfarbe(x, y);
                bild.setzePunktfarbe(x, y, c.brighter());
            }
        }
    }
}