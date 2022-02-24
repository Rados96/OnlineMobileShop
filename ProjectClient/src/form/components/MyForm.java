/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.components;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author samar
 */
public class MyForm extends JDialog implements IMyForm{

    JPanel myPanel;

    public MyForm(JPanel myPanel,String title, boolean modal) {
        this.myPanel = myPanel;
        setModal(modal);
        setTitle(title);
    }
    
    
    @Override
    public JPanel getMyPanel() {
        return myPanel;
    }

   
    
}
