package org.ehmsoft;

import org.ehmsoft.ui.AgentUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(AgentUI::new);
    }
}