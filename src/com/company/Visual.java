package com.company;
import java.awt.Color;
//import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
//import java.awt.event.InputMethodEvent;
//import java.awt.event.InputMethodListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import javafx.application.Application;
//import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Visual extends javax.swing.JFrame 
{
    MyTable t;
    Object[][] DataSearch;
    DataBase db;
    Integer timeoutPing;
    public Visual() 
    {
        initComponents();
        t = new MyTable(Table);
        db = new DataBase(t);
        //ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("\\img\\icon.png"));
        //this.setIconImage(img.getImage());
        
        ListSelectionModel listModel = Table.getSelectionModel();
        listModel.addListSelectionListener((ListSelectionEvent e) -> {
            //Table.clearSelection();
            int i = Table.getSelectedRow();
            //if(i >= 0) NameComputer.setText(Table.getModel().getValueAt(i, 1).toString() + (Table.getModel().getValueAt(i, 2).toString() == "1" ? " (ip)" : ""));
            if(i >= 0) 
            {
                NameComputer.setText(Table.getModel().getValueAt(i, 1).toString() + (Table.getModel().getValueAt(i, 2).toString().equals("1") ? "" : "." + SelectDomen.getSelectedItem().toString()));
                StatusComputer.setText("-");
            }
        });
        Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LoadDataActionPerformed(null);
        ///////////////////////////////////////////
        SearchText.setForeground(Color.GRAY);
        SearchText.setText("Search");
        SearchText.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                if(SearchText.getText().equals("Search"))
                {
                    SearchText.setText("");
                    SearchText.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) 
            {
                if(SearchText.getText().isEmpty())
                {
                    SearchText.setForeground(Color.GRAY);
                    SearchText.setText("Search");
                }
            }
        });
        SearchText.getDocument().addDocumentListener(new DocumentListener() {
            private void update()
            {
                if(SearchText.getText().equals("") || SearchText.getText().equals("Search")) 
                {
                    t.init(new Object[] {"id", "name", "ip"}, t.Data);
                    t.table.getColumn("ip").setPreferredWidth(0);
                    t.table.getColumn("ip").setMinWidth(0);
                    t.table.getColumn("ip").setMaxWidth(0);
                    return;
                }
                else SearchText.setForeground(Color.BLACK);
                String str = SearchText.getText();
                int q = 0;
                int k = 0;
                for(int i = 0; i < t.Data.length; i++)
                    if(t.Data[i][1].toString().toLowerCase().contains(str.toLowerCase())) q++;
                DataSearch = new String[q][];
                for(int i = 0; i < t.Data.length; i++)
                    if(t.Data[i][1].toString().toLowerCase().contains(str.toLowerCase()))
                    {
                        DataSearch[k] = t.Data[i].clone();
                        k++;
                    }
                t.update(DataSearch);
                t.table.getColumn("ip").setPreferredWidth(0);
                t.table.getColumn("ip").setMinWidth(0);
                t.table.getColumn("ip").setMaxWidth(0);
            }
            @Override
            public void insertUpdate(DocumentEvent e) 
            {
                update();
            }
            @Override
            public void removeUpdate(DocumentEvent e) 
            {
                update();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        ///////////////////////////////////////////
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NameComputer = new javax.swing.JLabel();
        StatusComputer = new javax.swing.JLabel();
        On = new javax.swing.JButton();
        SearchText = new javax.swing.JTextField();
        SelectCommand = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        SelectDomen = new javax.swing.JComboBox<>();
        labelUserName = new java.awt.Label();
        timeoutField = new java.awt.TextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        LoadData = new javax.swing.JMenuItem();
        Poling = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Блокировка устройств");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Table);

        jLabel1.setText("Имя компьютера:");

        jLabel2.setText("Статус:");

        jLabel3.setText("Блокировка");

        NameComputer.setText("-");

        StatusComputer.setText("-");

        On.setText("Выполнить");
        On.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OnMouseClicked(evt);
            }
        });
        On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnActionPerformed(evt);
            }
        });

        SearchText.setToolTipText("");

        SelectCommand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Обновить", "Отключить", "Включить" }));

        jLabel4.setText("Домен:");

        SelectDomen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ROI.local", "MSKROI.local", "REG.ROI.local" }));
        SelectDomen.setToolTipText("");
        SelectDomen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectDomenActionPerformed(evt);
            }
        });

        labelUserName.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        labelUserName.setText("label");
        labelUserName.setVisible(false);

        timeoutField.setText("2500");
        timeoutField.setVisible(false);

        Menu.setText("File");

        LoadData.setText("Загрузить");
        LoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadDataActionPerformed(evt);
            }
        });
        Menu.add(LoadData);

        Poling.setLabel("Опросить всех");
        Poling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PolingActionPerformed(evt);
            }
        });
        Menu.add(Poling);

        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SelectDomen, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(NameComputer, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(StatusComputer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(On, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(SelectCommand, 0, 99, Short.MAX_VALUE)
                                .addComponent(labelUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(timeoutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SearchText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(SelectDomen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NameComputer)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(SelectCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(On)))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(StatusComputer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeoutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelUserName.getAccessibleContext().setAccessibleName("label1");
        labelUserName.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OnMouseClicked
        timeoutPing = Integer.parseInt(timeoutField.getText());
        String strPing="";
        String[] appname;
        String command="";
        OpenApps oneApp = new OpenApps();
        switch(SelectCommand.getSelectedItem().toString())
        {   
            case "Обновить":
                //LockUs.trace("Обновить");
                command = "2";
                //System.exit(0);
                break;
            case "Отключить":
                //LockUs.trace("Отключить");
                command = "0";
                break;
            case "Включить":
                //LockUs.trace("Включить");
                command = "1";
                break;
        }
        if (NameComputer.getText() != "-") 
        {
            try 
            {
                //InetAddress inet = InetAddress.getByName("10.0.1.134");
                InetAddress inet = InetAddress.getByName(NameComputer.getText());
//                inet.isReachable(null,255,40);
//                inet.isReachable(null,255,40);
                strPing = (inet.isReachable(null,255,timeoutPing) ? " reacheble":" not reacheble");
//for Misha                System.out.println(strPing);
                if (strPing.equals(" not reacheble")) 
                {
                    StatusComputer.setText("Нет связи");
                    db.upLoadEvent(NameComputer.getText(),SelectCommand.getSelectedItem().toString(), StatusComputer.getText(), "Нет пинга \t Run from "+ LockUs.userName +"\\"+ LockUs.computerName);
                    return ;
                }
            } 
            catch (UnknownHostException ex) {
                
                StatusComputer.setText("Хост не доступен");
                db.upLoadEvent(NameComputer.getText(), SelectCommand.getSelectedItem().toString(), StatusComputer.getText(), ex.toString()+ " \t Run from "+ LockUs.userName +"\\"+ LockUs.computerName);
//Дляотладки                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
                return ;
            } 
            catch (Exception ex) {
                System.out.println("Неизвестная ошиибка");
                //System.out.println(ex);
                StatusComputer.setText("Не известная ошиибка");
                db.upLoadEvent(NameComputer.getText(), SelectCommand.getSelectedItem().toString(), StatusComputer.getText(), ex.toString()+" \t Run from "+ LockUs.userName +"\\"+ LockUs.computerName);
//Дляотладки                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
                return ;

            } 
            String lineOut = oneApp.Run(new String[] {LockUs.lunacyPath+"\\psexec.exe", "-accepteula", "\\\\"+ NameComputer.getText(),"-c","-f", LockUs.lunacyPath+"\\hackreg.exe",command,"ROI\\security2"});
            // Если нужны права systems(ответ = -1)
            if(lineOut.equals("-1") || lineOut.equals(""))
            {
              db.upLoadEvent(NameComputer.getText(), SelectCommand.getSelectedItem().toString(), "Установка прав", "lineOut: " + lineOut+" \t Run from "+ LockUs.userName +"\\"+ LockUs.computerName);
              lineOut = oneApp.Run(new String[] {LockUs.lunacyPath+"\\psexec.exe", "-accepteula", "\\\\"+ NameComputer.getText(),"-c","-f","-s", LockUs.lunacyPath+"\\hackreg.exe",command,"ROI\\security2"});
              lineOut = oneApp.Run(new String[] {LockUs.lunacyPath+"\\psexec.exe", "-accepteula", "\\\\"+ NameComputer.getText(),"-c","-f", LockUs.lunacyPath+"\\hackreg.exe",command,"ROI\\security2"});
            }
            
            switch(lineOut)
            {   
                case "0":
                    StatusComputer.setText("Разблокирован");
                break;
                
                case "1":
                    StatusComputer.setText("Заблокирован");
                break;
                
                default:
                    StatusComputer.setText("Ошибка");
                    //System.out.println("line(err): " + lineOut);
                break;
            }
            db.upLoadEvent(NameComputer.getText(), SelectCommand.getSelectedItem().toString(), StatusComputer.getText(), "lineOut: " + lineOut+" \t Run from "+ LockUs.userName +"\\"+ LockUs.computerName);
            
        }
    }//GEN-LAST:event_OnMouseClicked
    public void setUserNameLabel(String str)
    {
        labelUserName.setText(str);

    }

    /**
     *
     * @param b true or false
     */
    public void setVisibleUserNameLabel(boolean b)
    {
        labelUserName.setVisible(b);

    }
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       jLabel2.grabFocus();
    }//GEN-LAST:event_formMouseClicked

    private void LoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadDataActionPerformed
        db.load(SelectDomen.getSelectedItem().toString());
    }//GEN-LAST:event_LoadDataActionPerformed

    private void OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OnActionPerformed

    private void SelectDomenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectDomenActionPerformed
        // TODO add your handling code here:
        db.load(SelectDomen.getSelectedItem().toString());
    }//GEN-LAST:event_SelectDomenActionPerformed

    private void PolingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PolingActionPerformed
        System.out.println("Опрос");
        
        
    }//GEN-LAST:event_PolingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LoadData;
    private javax.swing.JMenu Menu;
    private javax.swing.JLabel NameComputer;
    private javax.swing.JButton On;
    private javax.swing.JMenuItem Poling;
    private javax.swing.JTextField SearchText;
    private javax.swing.JComboBox<String> SelectCommand;
    private javax.swing.JComboBox<String> SelectDomen;
    private javax.swing.JLabel StatusComputer;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label labelUserName;
    private java.awt.TextField timeoutField;
    // End of variables declaration//GEN-END:variables
}