package aplicacao;

import com.formdev.flatlaf.FlatLightLaf;
import gui.JanelaPrincipal;
import javax.swing.SwingUtilities;

public class Aplicacao {

    public static void main(String[] args) {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal principal = new JanelaPrincipal();
            principal.setLocationRelativeTo(null);
            principal.setVisible(true);
        });
    }
}