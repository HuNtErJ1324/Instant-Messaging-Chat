/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author peanu
 */
public class Login extends javax.swing.JDialog {

    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    /**
     * Creates new form Login
     * @param parent
     */
    public Login(java.awt.Frame parent, boolean model, Socket socket) {
        super(parent, model);
        this.socket = socket;
        initComponents();
        try {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            System.out.println("login constructor error");
        }
        this.getRootPane().setDefaultButton(submitJButton);
        this.setLocationRelativeTo(null);
    }
    
    public String getUsername() {
        return usernameJTextField.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        newUserJRadioButton = new javax.swing.JRadioButton();
        returningUserJRadioButton = new javax.swing.JRadioButton();
        usernameJTextField = new javax.swing.JTextField();
        passwordJPasswordField = new javax.swing.JPasswordField();
        submitJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonGroup.add(newUserJRadioButton);
        newUserJRadioButton.setSelected(true);
        newUserJRadioButton.setText("New User");

        buttonGroup.add(returningUserJRadioButton);
        returningUserJRadioButton.setText("Returning User");

        usernameJTextField.setToolTipText("Username");

        passwordJPasswordField.setToolTipText("Password");

        submitJButton.setText("Submit");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newUserJRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(returningUserJRadioButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(submitJButton)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returningUserJRadioButton)
                    .addComponent(newUserJRadioButton))
                .addGap(18, 18, 18)
                .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitJButton)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed

        try {
            //returning user
            if (returningUserJRadioButton.isSelected()) {
                out.writeInt(1);
                out.flush();
                out.writeUTF(usernameJTextField.getText());
                out.flush();
                String password = new String(passwordJPasswordField.getPassword());
                out.writeUTF(password);
                out.flush();
                String check = in.readUTF();
                if (check.startsWith("incorrect")) {
                    //TODO incorrect password message
                    this.dispose();
                }
            } //new user
            else {
                out.writeInt(2);
                out.flush();
                User user;
                out.writeUTF(usernameJTextField.getText());
                out.flush();
                String password = new String(passwordJPasswordField.getPassword());
                out.writeUTF(password);
                out.flush();
                this.dispose();
            }
        } catch (IOException e) {
            System.out.println("Client side login error");
        }
    }//GEN-LAST:event_submitJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton newUserJRadioButton;
    private javax.swing.JPasswordField passwordJPasswordField;
    private javax.swing.JRadioButton returningUserJRadioButton;
    private javax.swing.JButton submitJButton;
    private javax.swing.JTextField usernameJTextField;
    // End of variables declaration//GEN-END:variables
}
