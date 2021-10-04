import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Bildbetrachter ist die Hauptklasse der Bildbetrachter-Anwendung. Sie
 * erstellt die GUI der Anwendung, zeigt sie an und initialisiert alle
 * anderen Komponenten.
 * 
 * Erzeugen Sie ein Exemplar dieser Klasse, um die Anwendung zu starten.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 0.4
 */
public class Bildbetrachter
{
    // Datenfelder
    private JFrame fenster;
    private Bildflaeche bildflaeche;
    private Farbbild aktuellesBild;

    private JLabel statusLabel = new JLabel("Version 1.0");
    private ArrayList<Filter> filterliste;

    /**
     * Erzeuge einen Bildbetrachter und zeige seine GUI auf
     * dem Bildschirm an.
     */
    public Bildbetrachter()
    {
        filterlisteErzeugen();
        fensterErzeugen();
    }

    // ---- Implementierung der Menü-Funktionen ----
    /**
     * 'Datei oeffnen'-Funktion: Öffnet einen Dateiauswahldialog zur 
     * Auswahl einer Bilddatei und zeigt das selektierte Bild an.
     */
    private void dateiOeffnen()
    {
        Farbbild bild = BilddateiManager.gibBild();
        aktuellesBild = bild;
        bildflaeche.setzeBild(bild);
        fenster.pack();
    }

    /**
     * 'Beenden'-Funktion: Beendet die Anwendung.
     */
    private void beenden()
    {
        System.exit(0);
    }

    // ---- Swing-Anteil zum Erzeugen des Fensters mit allen Komponenten ----
    /**
     * Erzeuge das Swing-Fenster samt Inhalt.
     */
    private void fensterErzeugen()
    {
        fenster = new JFrame("Bildbetrachter");
        menuezeileErzeugen(fenster);

        // dateinameLabel

        Container contentPane = fenster.getContentPane();
        contentPane.setLayout(new BorderLayout());
        bildflaeche = new Bildflaeche();
        contentPane.add(bildflaeche, BorderLayout.CENTER);

        // statusLabel
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        // Aufbau abgeschlossen - Komponenten arrangieren lassen
        fenster.pack();
        fenster.setVisible(true);
    }

    /**
     * Die Menüzeile des Hauptfensters erzeugen.
     * @param fenster das Fenster, in das die Menüzeile eingefügt werden soll.
     */
    private void menuezeileErzeugen(JFrame fenster)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);

        // Das Datei-Menü erzeugen
        JMenu dateiMenue = new JMenu("Datei");
        menuezeile.add(dateiMenue);

        JMenuItem oeffnenEintrag = new JMenuItem("Öffnen...");
        oeffnenEintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        oeffnenEintrag.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { dateiOeffnen(); }
            });
        dateiMenue.add(oeffnenEintrag);

        JMenuItem beendenEintrag = new JMenuItem("Beenden");
        beendenEintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        beendenEintrag.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { beenden(); }
            });
        dateiMenue.add(beendenEintrag);

        JMenu filterMenue = new JMenu("Filter");
        menuezeile.add(filterMenue);

        // iterieren über filterliste
        for (Filter filter : filterliste) {
            JMenuItem eintrag = new JMenuItem(filter.gibName());
            eintrag.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        filterAnwenden(filter);
                    }
                });
            filterMenue.add(eintrag);
        }

        JMenu hilfeMenu = new JMenu("Hilfe");
        menuezeile.add(hilfeMenu);

        JMenuItem infoEintrag = new JMenuItem("Info");
        infoEintrag.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    zeigeInfo();
                }
            });
        hilfeMenu.add(infoEintrag);
    }

    public void filterAnwenden(Filter filter){
        if(aktuellesBild != null) {
            filter.anwenden(aktuellesBild);
            fenster.repaint();
            statusAnzeigen("angewendeter Filter: " + filter.gibName());
        }
        else {
            statusAnzeigen("Kein Bild geladen!");
        }
    }

    public void statusAnzeigen(String text)
    {
        statusLabel.setText(text);
    }

    public void zeigeInfo()
    {
        JOptionPane.showMessageDialog(null, "Bildbetrachter der T3A\n Version 0.4","Information" ,JOptionPane.INFORMATION_MESSAGE);
    }

    private void filterlisteErzeugen(){
        filterliste = new ArrayList();
        filterliste.add(new AufhellFilter("Heller"));
        filterliste.add(new AbdunkelFilter("Dunkler"));
        filterliste.add(new SchwellwertFilter("Schwellwert"));
        filterliste.add(new GraustufenFilter("Graustufen"));
        filterliste.add(new SaettigungFilter("Sättigung"));
    }
}
