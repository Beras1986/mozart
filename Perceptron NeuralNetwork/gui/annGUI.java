package gui;

import java.io.IOException;
import java.text.DecimalFormat;
import netcore.*;
/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * August 2019
 */
public class annGUI extends javax.swing.JFrame {
    
    private NeuralNetwork ann;
    /**
     * Creates new form annGUI
     */
    public annGUI() {
        initComponents();
    }
    
    private void resetOutcomesArea(){
        
        neuralNetworkOutcomesArea.setText("");
    }
    
    private void resetAnnFields(){
     
      numEpochs.setText("");
      numInputNeurons.setText("");
      numHiddenNeuronsPerLayer.setText("");
      targetError.setText("");
      learningRate.setText("");
      mae.setText("");
      mse.setText("");
      buySellSignal.setText("");
      neuralNetworkOutcomesArea.setText("");
      inputString.setText("");
      outputDataset.setText("");
      oneHiddenLayer.setSelected(true);
      twoHiddenLayers.setSelected(false);
    }
    
    private void doOneHiddenLayer(){
        
        oneHiddenLayer.setSelected(true);
        twoHiddenLayers.setSelected(false);
    }
    
    private void doTwoHiddenLayers(){
        
        oneHiddenLayer.setSelected(false);
        twoHiddenLayers.setSelected(true);
    }
    
    private boolean doInitNet(){
        
        this.ann = new NeuralNetwork();
        boolean result = false;
        int hiddenLayerCount;
        
        if(oneHiddenLayer.isSelected()&!twoHiddenLayers.isSelected()){
            
            hiddenLayerCount = 1;
        }else{
            
            hiddenLayerCount = 2;
        }
        
        try{
            
            int epochs = new Integer(numEpochs.getText());
            int inputNeuronsCount = new Integer(numInputNeurons.getText());
            int hiddenNeuronsPerLayerCount = new Integer(numHiddenNeuronsPerLayer.getText());
            double learningCoefficient = new Double(learningRate.getText());
            double acceptedError = new Double(targetError.getText());
            this.ann = this.ann.neuralNetworkInit(inputNeuronsCount,hiddenLayerCount,hiddenNeuronsPerLayerCount);       			
            this.ann.setMaxEpochs(epochs);
            this.ann.setAcceptedError(acceptedError);
            this.ann.setLearningCoefficient(learningCoefficient);
            result = true;
            neuralNetworkOutcomesArea.setText("Neural network has been initialised");
        }
        catch(NumberFormatException anExcption){
            
            neuralNetworkOutcomesArea.setText("Error: Incorrect number format entered");
            return result;
        }
        catch(Exception anException){
            
            neuralNetworkOutcomesArea.setText("Error: Unexpected exception thrown");
            return result;
        }
        return result;
    }
        
    private boolean doTrainNet(){
        
        boolean result = false;
        
        try{
            
            DecimalFormat df = new DecimalFormat("#.######");
            
            String input = inputString.getText();
            String output = outputDataset.getText();
            
            DataSet inputData = new DataSet("data", input);
            DataSet outputData = new DataSet("data", output);
            
            double[][] inputTable = inputData.csvTo2dTable(inputData);
            double[][] outputTable = outputData.csvTo2dTable(outputData);

            double[][] scaledInputTable = inputData.scalTable(inputTable);
            double[][] scaledOutputTable = outputData.scalTable(outputTable);
            
            this.ann.setTrainingDataset(scaledInputTable);
            this.ann.setOutputDataset(scaledOutputTable);
            
            NeuralNetwork trainedNet = new NeuralNetwork();    
            trainedNet = this.ann.trainAnn(this.ann);
            this.ann = trainedNet;
            neuralNetworkOutcomesArea.setText("Neural network has been trained");
            mse.setText(df.format(this.ann.getMse()));
            mae.setText(df.format(this.ann.getMae()));
            
            result = true;
        }
        catch(IOException anException){
            
            neuralNetworkOutcomesArea.setText("Error: No such file in data directory");
            return result;
        }
        catch(NullPointerException anException){
            
            anException.printStackTrace();
            neuralNetworkOutcomesArea.setText("Error: Null pointer exception");
            return result;
        }
        return result;
        
    }
    
    private boolean doTestNet(){
        
        boolean result = false;
        
        try{
            
            DecimalFormat df = new DecimalFormat("#.######");
            
            String input = inputString.getText();
            String output = outputDataset.getText();
            
            DataSet inputTestData = new DataSet("data", input);
            DataSet outputTestData = new DataSet("data", output);
            
            double[][] inputTestTable = inputTestData.csvTo2dTable(inputTestData);
            double[][] outputTestTable = outputTestData.csvTo2dTable(outputTestData);

            double[][] scaledInputTestTable = inputTestData.scalTable(inputTestTable);
            double[][] scaledOutputTestTable = outputTestData.scalTable(outputTestTable);
            
            this.ann.setTrainingDataset(scaledInputTestTable);
            this.ann.setOutputDataset(scaledOutputTestTable);
            
            this.ann.trainAnn(this.ann);
            neuralNetworkOutcomesArea.setText("Neural network has been tested");
            mse.setText(df.format(this.ann.getMse()));
            mae.setText(df.format(this.ann.getMae()));
            
            result = true;
        }
        catch(IOException anException){
            
            neuralNetworkOutcomesArea.setText("Error: No such file in data directory");
            return result;
        }
        catch(NullPointerException anException){
            
            anException.printStackTrace();
            neuralNetworkOutcomesArea.setText("Error: Null pointer exception");
            return result;
        }
        return result;
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        numInputNeurons = new javax.swing.JTextField();
        numHiddenNeuronsPerLayer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        oneHiddenLayer = new javax.swing.JRadioButton();
        twoHiddenLayers = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numEpochs = new javax.swing.JTextField();
        learningRate = new javax.swing.JTextField();
        targetError = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        buySellSignal = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        mse = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        mae = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        trainNet = new javax.swing.JButton();
        initializeNet = new javax.swing.JButton();
        neuralNetworkOutcomesArea = new javax.swing.JTextField();
        inputString = new javax.swing.JTextField();
        outputDataset = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        resetFields = new javax.swing.JButton();
        testNet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numInputNeurons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numInputNeuronsActionPerformed(evt);
            }
        });

        numHiddenNeuronsPerLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numHiddenNeuronsPerLayerActionPerformed(evt);
            }
        });

        jLabel1.setText("Number of input neurons (integers only)");

        jLabel2.setText("Number of hidden layers");

        jLabel3.setText("Neurons per hidden layer (integers only)");

        oneHiddenLayer.setSelected(true);
        oneHiddenLayer.setText("1 Hidden Layer");
        oneHiddenLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneHiddenLayerActionPerformed(evt);
            }
        });

        twoHiddenLayers.setText("2 Hidden Layers");
        twoHiddenLayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoHiddenLayersActionPerformed(evt);
            }
        });

        jLabel4.setText("Number of epochs (integers only)");

        jLabel5.setText("Learning rate (floating point only)");

        jLabel6.setText("Target error (floating point only)");

        numEpochs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numEpochsActionPerformed(evt);
            }
        });

        learningRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learningRateActionPerformed(evt);
            }
        });

        targetError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetErrorActionPerformed(evt);
            }
        });

        buySellSignal.setColumns(20);
        buySellSignal.setRows(5);
        jScrollPane1.setViewportView(buySellSignal);

        mse.setColumns(20);
        mse.setRows(5);
        jScrollPane2.setViewportView(mse);

        mae.setColumns(20);
        mae.setRows(5);
        jScrollPane3.setViewportView(mae);

        jLabel7.setText("Mean squared error");

        jLabel8.setText("Mean absolute error");

        jLabel9.setText("Buy / Sell signal");

        trainNet.setText("Train Neural Net");
        trainNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainNetActionPerformed(evt);
            }
        });

        initializeNet.setText("Initialise Neural Net");
        initializeNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initializeNetActionPerformed(evt);
            }
        });

        inputString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputStringActionPerformed(evt);
            }
        });

        jLabel10.setText("Input dataset (one data class per input layer)");

        jLabel11.setText("Output dataset (data class to be forecasted)");

        resetFields.setText("Reset Fields");
        resetFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFieldsActionPerformed(evt);
            }
        });

        testNet.setText("Test Neural Net");
        testNet.setActionCommand("Test Neural Net");
        testNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testNetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(neuralNetworkOutcomesArea, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(numInputNeurons, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numHiddenNeuronsPerLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numEpochs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(targetError, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(learningRate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(oneHiddenLayer)
                                    .addComponent(jLabel2)
                                    .addComponent(twoHiddenLayers)
                                    .addComponent(initializeNet, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(resetFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(109, 109, 109)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputString, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(outputDataset, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trainNet, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(testNet, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(oneHiddenLayer)
                        .addComponent(numInputNeurons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(twoHiddenLayers)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addComponent(numHiddenNeuronsPerLayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputDataset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numEpochs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(initializeNet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trainNet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(targetError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetFields, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testNet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(learningRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(neuralNetworkOutcomesArea, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        testNet.getAccessibleContext().setAccessibleName("Test Neural Net");

        jTabbedPane1.addTab("Neural Network", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numInputNeuronsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numInputNeuronsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numInputNeuronsActionPerformed

    private void numHiddenNeuronsPerLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numHiddenNeuronsPerLayerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numHiddenNeuronsPerLayerActionPerformed

    private void numEpochsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numEpochsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numEpochsActionPerformed

    private void learningRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learningRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_learningRateActionPerformed

    private void targetErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetErrorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetErrorActionPerformed

    private void trainNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainNetActionPerformed
        resetOutcomesArea();
        doTrainNet();        
    }//GEN-LAST:event_trainNetActionPerformed

    private void initializeNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initializeNetActionPerformed
        resetOutcomesArea();
        doInitNet();
    }//GEN-LAST:event_initializeNetActionPerformed

    private void inputStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputStringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputStringActionPerformed

    private void oneHiddenLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneHiddenLayerActionPerformed
        doOneHiddenLayer();
    }//GEN-LAST:event_oneHiddenLayerActionPerformed

    private void twoHiddenLayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoHiddenLayersActionPerformed
        doTwoHiddenLayers();
    }//GEN-LAST:event_twoHiddenLayersActionPerformed

    private void resetFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFieldsActionPerformed
        resetAnnFields();
    }//GEN-LAST:event_resetFieldsActionPerformed

    private void testNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testNetActionPerformed
        resetOutcomesArea();
        doTestNet();
    }//GEN-LAST:event_testNetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(annGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(annGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(annGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(annGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new annGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea buySellSignal;
    private javax.swing.JButton initializeNet;
    private javax.swing.JTextField inputString;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField learningRate;
    private javax.swing.JTextArea mae;
    private javax.swing.JTextArea mse;
    private javax.swing.JTextField neuralNetworkOutcomesArea;
    private javax.swing.JTextField numEpochs;
    private javax.swing.JTextField numHiddenNeuronsPerLayer;
    private javax.swing.JTextField numInputNeurons;
    private javax.swing.JRadioButton oneHiddenLayer;
    private javax.swing.JTextField outputDataset;
    private javax.swing.JButton resetFields;
    private javax.swing.JTextField targetError;
    private javax.swing.JButton testNet;
    private javax.swing.JButton trainNet;
    private javax.swing.JRadioButton twoHiddenLayers;
    // End of variables declaration//GEN-END:variables
}
