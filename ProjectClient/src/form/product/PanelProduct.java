
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.product;

import communication.Communication;
import constants.Operation;
import domain.Accessories;
import domain.Manufacturer;
import domain.Mobile;
import domain.Product;
import domain.Type;
import domain.domainEnum.ProductType;
import form.FormMain;
import form.FormMode;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import logic.Controler;
import session.Session;
import transfer.ClientRequest;

/**
 *
 * @author Rados
 */
public class PanelProduct extends javax.swing.JPanel {

    private JDialog ancestor;
    FormMode mode;
    private Product product;

    /**
     * Creates new form PanelProduct
     *
     * @param mode
     * @param ancestor
     */
    public PanelProduct(FormMode mode, JDialog ancestor) {
        initComponents();
        this.mode = mode;
        this.ancestor = ancestor;
        Controler.getInstance().getFormMain().setPanelProduct(this);
        requestManufacturers();
        requestTypes();
        adjustForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtPrice = new javax.swing.JTextField();
        pnlMobile = new javax.swing.JPanel();
        lblColor = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lblMemory = new javax.swing.JLabel();
        txtMemory = new javax.swing.JTextField();
        lblSpecification = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSpecification = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        lblPrice = new javax.swing.JLabel();
        txtProductCode = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        pnlAccessories = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblType = new javax.swing.JLabel();
        cmbTypes = new javax.swing.JComboBox();
        lblManufacturer = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        lblModel = new javax.swing.JLabel();
        cmbManufacturers = new javax.swing.JComboBox();
        lblProductCode = new javax.swing.JLabel();
        cboxMobile = new javax.swing.JCheckBox();
        cboxAccessories = new javax.swing.JCheckBox();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        lblColor.setText("Boja:");

        lblMemory.setText("Memorija:");

        lblSpecification.setText("Specifikacija:");

        txtSpecification.setColumns(20);
        txtSpecification.setRows(5);
        jScrollPane1.setViewportView(txtSpecification);

        javax.swing.GroupLayout pnlMobileLayout = new javax.swing.GroupLayout(pnlMobile);
        pnlMobile.setLayout(pnlMobileLayout);
        pnlMobileLayout.setHorizontalGroup(
            pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMobileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlMobileLayout.createSequentialGroup()
                        .addGroup(pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMemory)
                            .addComponent(lblColor))
                        .addGap(22, 22, 22)
                        .addGroup(pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMemory)
                            .addComponent(txtColor)))
                    .addComponent(lblSpecification))
                .addContainerGap())
        );
        pnlMobileLayout.setVerticalGroup(
            pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMobileLayout.createSequentialGroup()
                .addGroup(pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMobileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMemory)
                    .addComponent(txtMemory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSpecification)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSave.setText("Dodaj proizvod");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblPrice.setText("Cena:");

        btnCancel.setText("Odustani");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblDescription.setText("Opis:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        lblType.setText("Vrsta:");

        cmbTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlAccessoriesLayout = new javax.swing.GroupLayout(pnlAccessories);
        pnlAccessories.setLayout(pnlAccessoriesLayout);
        pnlAccessoriesLayout.setHorizontalGroup(
            pnlAccessoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccessoriesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccessoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlAccessoriesLayout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlAccessoriesLayout.createSequentialGroup()
                        .addComponent(lblType)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTypes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAccessoriesLayout.setVerticalGroup(
            pnlAccessoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccessoriesLayout.createSequentialGroup()
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAccessoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblType))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblManufacturer.setText("Proizvodjac:");

        lblModel.setText("Model:");

        cmbManufacturers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblProductCode.setText("Šifra proizvoda:");

        buttonGroup1.add(cboxMobile);
        cboxMobile.setText("Telefon");
        cboxMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxMobileActionPerformed(evt);
            }
        });

        buttonGroup1.add(cboxAccessories);
        cboxAccessories.setText("Oprema");
        cboxAccessories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxAccessoriesActionPerformed(evt);
            }
        });

        btnEdit.setText("Ažuriraj");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnUpdate.setText("Izmeni");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Obrisi");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAccessories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductCode)
                            .addComponent(lblModel)
                            .addComponent(lblManufacturer)
                            .addComponent(lblPrice))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbManufacturers, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtModel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductCode))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboxMobile)
                        .addGap(18, 18, 18)
                        .addComponent(cboxAccessories))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addContainerGap())
            .addComponent(pnlMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductCode)
                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModel)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblManufacturer)
                    .addComponent(cmbManufacturers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxMobile)
                    .addComponent(cboxAccessories))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccessories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        ClientRequest cr = new ClientRequest();
        cr.setOperation(Operation.ADD_PRODUCT);
        try {
            validation();
            String productCode = txtProductCode.getText().trim();
            String model = txtModel.getText();
            Manufacturer m = (Manufacturer) cmbManufacturers.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            if (cboxMobile.isSelected()) {
                pnlMobile.setVisible(true);
                String color = txtColor.getText();
                int memory = Integer.parseInt(txtMemory.getText());
                String specification = txtSpecification.getText();
                product = new Mobile(0, productCode, model, price, m, ProductType.mobile, memory, specification, color);
                cr.setParameter(product);
            }

            if (cboxAccessories.isSelected()) {
                pnlAccessories.setVisible(true);
                String description = txtDescription.getText();
                Type type = (Type) cmbTypes.getSelectedItem();
                product = new Accessories(0, productCode, model, price, m, ProductType.accessories, description, type);
                cr.setParameter(product);
            }
            Communication.getInstance().sendRequest(cr);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        switch (mode) {
            case EDIT:
                mode = FormMode.VIEW;
                adjustForm();
                break;
            case NEW:
            case VIEW:
                Controler.getInstance().setActiveWindow((Window) ancestor.getParent());
                Controler.getInstance().getFormMain().setPanelProduct(null);
                ancestor.dispose();
                break;
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.mode = FormMode.EDIT;
        adjustForm();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        product = (Product) Session.getInstance().getUseCaseParams().get("product");
        ClientRequest cr = new ClientRequest();
        cr.setOperation(Operation.UPDATE_PRODUCT);
        try {
            validation();
            String productCode = product.getProductCode();
            String model = txtModel.getText();
            Manufacturer m = (Manufacturer) cmbManufacturers.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());
            int id = product.getProductID();
            if (product.getProductType().equals(ProductType.mobile)) {
                pnlMobile.setVisible(true);
                String color = txtColor.getText();
                int memory = Integer.parseInt(txtMemory.getText());
                String specification = txtSpecification.getText();
                product = new Mobile(id, productCode, model, price, m, ProductType.mobile, memory, specification, color);
                Session.getInstance().getUseCaseParams().put("product", product);
                cr.setParameter(product);
            }
            if (product.getProductType().equals(ProductType.accessories)) {
                pnlAccessories.setVisible(true);
                String description = txtDescription.getText();
                Type type = (Type) cmbTypes.getSelectedItem();
                product = new Accessories(id, productCode, model, price, m, ProductType.accessories, description, type);
                Session.getInstance().getUseCaseParams().put("product", product);
                cr.setParameter(product);
            }

            Communication.getInstance().sendRequest(cr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da izmeni podatke o proizvodu." + ex.getMessage(), "Greška!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cboxMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxMobileActionPerformed
        resetFieldsMobile();
        pnlMobile.setVisible(true);
        pnlAccessories.setVisible(false);
    }//GEN-LAST:event_cboxMobileActionPerformed

    private void cboxAccessoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxAccessoriesActionPerformed
        resetFieldsAccessories();
        pnlAccessories.setVisible(true);
        pnlMobile.setVisible(false);
    }//GEN-LAST:event_cboxAccessoriesActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        product = (Product) Session.getInstance().getUseCaseParams().get("product");

        ClientRequest cr = new ClientRequest();
        cr.setOperation(Operation.DELETE_PRODUCT);
        cr.setParameter(product);
        Communication.getInstance().sendRequest(cr);
    }//GEN-LAST:event_btnDeleteActionPerformed

    public JDialog getAncestor() {
        return ancestor;
    }

    public void setAncestor(JDialog ancestor) {
        this.ancestor = ancestor;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cboxAccessories;
    private javax.swing.JCheckBox cboxMobile;
    private javax.swing.JComboBox cmbManufacturers;
    private javax.swing.JComboBox cmbTypes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblManufacturer;
    private javax.swing.JLabel lblMemory;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProductCode;
    private javax.swing.JLabel lblSpecification;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel pnlAccessories;
    private javax.swing.JPanel pnlMobile;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtMemory;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextArea txtSpecification;
    // End of variables declaration//GEN-END:variables

    public void resetForNewProduct() {
        resetTextField();
    }

    private void adjustForm() {
        switch (mode) {
            case NEW:
                resetForNewProduct();

                btnSave.setVisible(true);
                btnEdit.setVisible(false);
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                btnCancel.setVisible(true);
                pnlAccessories.setVisible(false);

                setTitle("Unos novog proizvoda");
                break;

            case EDIT:

                txtProductCode.setEnabled(false);
                txtModel.setEnabled(true);
                txtPrice.setEnabled(true);
                cmbManufacturers.setEnabled(true);
                cboxMobile.setEnabled(false);
                cboxAccessories.setEnabled(false);

                txtColor.setEnabled(true);
                txtMemory.setEnabled(true);
                txtSpecification.setEnabled(true);

                txtDescription.setEnabled(true);
                cmbTypes.setEnabled(true);

                btnSave.setVisible(false);
                btnEdit.setVisible(false);
                btnUpdate.setVisible(true);
                btnDelete.setVisible(false);
                btnCancel.setVisible(true);
                btnCancel.setText("Odustani");

                setTitle("Izmena proizvoda");
                break;
            case VIEW:

                txtProductCode.setEnabled(false);
                txtModel.setEnabled(false);
                txtPrice.setEnabled(false);
                cmbManufacturers.setEnabled(false);
                cboxAccessories.setEnabled(false);
                cboxMobile.setEnabled(false);

                txtColor.setEnabled(false);
                txtMemory.setEnabled(false);
                txtSpecification.setEnabled(false);

                txtDescription.setEnabled(false);
                cmbTypes.setEnabled(false);

                btnSave.setVisible(false);
                btnEdit.setVisible(true);
                btnUpdate.setVisible(false);
                btnDelete.setVisible(true);
                btnCancel.setVisible(true);
                btnCancel.setText("Izadji");

                setTitle("Pregled proizvoda");
                setProduct();
                break;
        }
    }

    private void setTitle(String title) {
        ancestor.setTitle(title);
    }

    private void requestManufacturers() {
        FormMain formMain = Controler.getInstance().getFormMain();
        if (formMain.getPanelSearchProduct() != null) {
            ArrayList<Manufacturer> list = formMain.getPanelSearchProduct().getManufacturersFromCMB();
            populateCMBManufacturers(list);
        } else {
            ClientRequest cr = new ClientRequest();
            Session.getInstance().getUseCaseParams().put("request manufacturer", "PanelProduct");
            cr.setOperation(Operation.GET_ALL_MANUFACTURERS);
            Communication.getInstance().sendRequest(cr);
        }
    }

    public void populateCMBManufacturers(ArrayList<Manufacturer> manufacturers) {
        cmbManufacturers.removeAllItems();
        for (Manufacturer m : manufacturers) {
            cmbManufacturers.addItem(m);
        }
    }

    private void requestTypes() {
        FormMain formMain = Controler.getInstance().getFormMain();
        if (formMain.getPanelSearchProduct() != null) {
            ArrayList<Type> list = formMain.getPanelSearchProduct().getTypesFromCMB();
            populateCMBTypes(list);
        } else {
            ClientRequest cr = new ClientRequest();
            Session.getInstance().getUseCaseParams().put("request type", "PanelProduct");
            cr.setOperation(Operation.GET_ALL_TYPES);
            Communication.getInstance().sendRequest(cr);
        }
    }

    public void populateCMBTypes(ArrayList<Type> types) {
        cmbTypes.removeAllItems();
        for (Type t : types) {
            cmbTypes.addItem(t);
        }
    }

    private void validation() throws Exception {
        if (txtProductCode.getText().isEmpty() || txtProductCode.getText().equals("")) {
            throw new Exception("Nije uneta šifra proizvoda!");
        } else if (txtProductCode.getText().length() != 8) {
            throw new Exception("Šifra proizvoda mora imati 8 znakova!");
        } else {
            String productCode = txtProductCode.getText();
            int counter = 0;
            for (int i = 0; i < productCode.length(); i++) {
                if (Character.isDigit(productCode.charAt(i))) {
                    counter++;
                }
            }
            if(counter == 0){
                throw new Exception("Šifra proizvoda mora sadržati barem jedan broj!");
            }
        }
        
        if (txtModel.getText().isEmpty() || txtModel.getText().equals("")) {
            throw new Exception("Nije unet model");
        }

        if (cmbManufacturers.getSelectedIndex() == -1) {
            throw new Exception("Niste odabrali proizvođača");
        }

        if (txtPrice.getText().isEmpty() || txtPrice.getText().equals("")) {
            throw new Exception("Niste uneli cenu");
        }

        if (Double.parseDouble(txtPrice.getText()) <= 0) {
            throw new Exception("Cena mora biti veca od 0");
        }

        if (cboxMobile.isSelected()) {
            if (txtColor.getText().isEmpty() || txtColor.getText().equals("")) {
                throw new Exception("Nije uneta boja telefona");
            }
            if (txtMemory.getText().isEmpty() || txtMemory.getText().equals("")) {
                throw new Exception("Niste uneli memoriju telefona");
            }

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 2; i <= 1024; i = i * 2) {
                list.add(i);
            }
            if (!list.contains(Integer.parseInt(txtMemory.getText()))) {
                throw new Exception("Nije uneta odgovarajuća memorija");
            }

            if (txtSpecification.getText().isEmpty() || txtSpecification.getText().equals("")) {
                throw new Exception("Nije uneta specifikacija telefona");
            }
        }

        if (cboxAccessories.isSelected()) {
            if (txtDescription.getText().isEmpty() || txtDescription.getText().equals("")) {
                throw new Exception("Nije unet opis opreme");
            }
            if (cmbTypes.getSelectedIndex() == -1) {
                throw new Exception("Niste odabrali vrstu opreme");
            }
        }

    }

    private void resetTextField() {
        txtProductCode.setText("");
        txtModel.setText("");
        txtPrice.setText("");
        cboxMobile.setSelected(true);
        resetFieldsMobile();
        resetFieldsAccessories();
    }

    private void setProduct() {
        Product p = (Product) Session.getInstance().getUseCaseParams().get("product");
        txtProductCode.setText(p.getProductCode());
        txtModel.setText(p.getModel());
        txtPrice.setText(p.getPrice() + "");
        cmbManufacturers.setSelectedItem(p.getManufacturer());
        if (p.getProductType().equals(ProductType.mobile)) {
            Mobile m = (Mobile) p;
            cboxMobile.setSelected(true);
            txtColor.setText(m.getColor());
            txtMemory.setText(m.getMemory() + "");
            txtSpecification.setText(m.getSpecification());
            pnlAccessories.setVisible(false);
        }
        if (p.getProductType().equals(ProductType.accessories)) {
            Accessories a = (Accessories) p;
            cboxAccessories.setSelected(true);
            txtDescription.setText(a.getDescription());
            cmbTypes.setSelectedItem(a.getType());
            pnlMobile.setVisible(false);
        }

    }

    public void resetFieldsMobile() {
        txtColor.setText("");
        txtMemory.setText("");
        txtSpecification.setText("");
    }

    private void resetFieldsAccessories() {
        txtDescription.setText("");
    }

    public void cancel() {
        switch (mode) {
            case EDIT:
                mode = FormMode.VIEW;
                adjustForm();
                break;

            case NEW:
            case VIEW:
                Controler.getInstance().setActiveWindow((Window) ancestor.getParent());
                Controler.getInstance().getFormMain().setPanelProduct(null);
                ancestor.dispose();
                break;
        }
    }

}
