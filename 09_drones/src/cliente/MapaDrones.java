package cliente;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Dron;
import service.CompositorDron;

public class MapaDrones extends JPanel {
    private final Map<String, Dron> posiciones = new ConcurrentHashMap<>();

    public void actualizar(Dron dato) {
        posiciones.put(dato.getNombre(), dato);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        posiciones.values().forEach(dato -> {
            int x = (int) dato.getX();
            int y = (int) dato.getY();
            g.setColor(Color.RED);
            g.fillOval(x, y, 10, 10);
            g.drawString(dato.getNombre(), x + 12, y);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Seguimiento de Drones");
        MapaDrones mapa = new MapaDrones();
        frame.add(mapa);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        var control = new CompositorDron();
        control.flujoGlobal()
        		.observeOn(Schedulers.computation())
                .subscribe(dato -> SwingUtilities.invokeLater(() -> mapa.actualizar(dato)));
    }
}

