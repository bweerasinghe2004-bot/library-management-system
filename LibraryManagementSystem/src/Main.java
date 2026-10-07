import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LibraryGUI gui = new LibraryGUI();

            gui.setVisible(true);

        });
    }
}