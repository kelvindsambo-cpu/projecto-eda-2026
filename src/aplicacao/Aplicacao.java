package aplicacao;

import com.formdev.flatlaf.FlatLightLaf;
import gui.JanelaPrincipal;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Aplicacao {

    public static void main(String[] args) {

        FlatLightLaf.setup();
        activarEnterNosBotoes();

        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal principal = new JanelaPrincipal();
            principal.setLocationRelativeTo(null);
            principal.setVisible(true);
        });
    }

    /**
     * Por omissão o Swing só carrega num botão com foco através da tecla Espaço.
     * Aqui o Enter passa a fazer o mesmo em todos os botões da aplicação.
     */
    private static void activarEnterNosBotoes() {
        for (String chave : new String[] {"Button.focusInputMap", "ToggleButton.focusInputMap"}) {
            Object mapa = UIManager.get(chave);
            if (mapa instanceof InputMap inputMap) {
                inputMap.put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed");
                inputMap.put(KeyStroke.getKeyStroke("released ENTER"), "released");
            }
        }
    }
}
