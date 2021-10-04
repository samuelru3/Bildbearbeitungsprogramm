import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

/**
 * BilddateiManager ist eine kleine Hilfsklasse mit statischen Methoden
 * zum Laden und Speichern von Bildern.
 * 
 * Zu lesende Dateien k�nnen im JPG- oder im PNG-Format vorliegen.
 * Das Format von Dateien, die von dieser Klasse geschrieben werden,
 * wird durch die Konstante BILDFORMAT festgelegt. 
 * 
 * @author Michael K�lling und David J Barnes 
 * @version 1.0
 */
public class BilddateiManager
{
    // Eine Konstante, die das Format f�r geschriebene Dateien festgelegt.
    // Zul�ssige Formate sind "jpg" und "png".
    private static final String BILDFORMAT = "jpg";
    private static JFileChooser dateiauswahldialog = new JFileChooser(System.getProperty("user.dir"));
    
    /**
     * �ffne einen Dateiauswahldialog und lasse den Benutzer eine Bilddatei
     * aus dem Dateisystem ausw�hlen. Lade dann dieses Bild und liefere es als
     * ein Farbbild zur�ck. Diese Methode kann JPG- und PNG-Formate lesen.
     * Bei einem Problem (Datei existiert nicht, hat das falsche Format oder
     * es gibt einen anderen Lesefehler) liefert diese Methode null.
     * 
     * @return       Das Bild-Objekt oder null, falls keine g�ltige Bilddatei selektiert wurde.
     */
    public static Farbbild gibBild()
    {
        int ergebnis = dateiauswahldialog.showOpenDialog(null);

        if(ergebnis != JFileChooser.APPROVE_OPTION) {
            return null;  // abgebrochen
        }
        File selektierteDatei = dateiauswahldialog.getSelectedFile();
        return ladeBild(selektierteDatei);
    }

    
    /**
     * Lies eine Bilddatei ein und liefere sie als ein Bild zur�ck.
     * Diese Methode kann Dateien im JPG- und im PNG-Format lesen.
     * Bei Problemen (etwa, wenn die Datei nicht existiert oder ein nicht
     * lesbares Format hat oder es einen sonstigen Lesefehler gibt)
     * liefert diese Methode null.
     * 
     * @param bilddatei  Die zu ladende Bilddatei.
     * @return           Das Bild-Objekt oder null, falls die Datei nicht
     *                      lesbar ist.
     */
    public static Farbbild ladeBild(File bilddatei)
    {
        try {
            BufferedImage bild = ImageIO.read(bilddatei);
            if(bild == null || (bild.getWidth(null) < 0)) {
                // Bild konnte nicht geladen werden - vermutlich falsches Format
                return null;
            }
            return new Farbbild(bild);
        }
        catch(IOException exc) {
            return null;
        }
    }

    /**
     * Schreibe das gegebene Bild in eine Bilddatei im JPG-Format.
     * Bei etwaigen Problemen beendet sich diese Methode stillschweigend.
     * 
     * @param bild  Das zu speichernde Bild.
     * @param datei Die Datei, in die gespeichert werden soll.
     */
    public static void speichereBild(Farbbild bild, File datei)
    {
        try {
            ImageIO.write(bild, BILDFORMAT, datei);
        }
        catch(IOException exc) {
            return;
        }
    }
}
