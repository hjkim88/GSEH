/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculation;

/**
 *
 * @author Hyunjin Kim
 */
public class DifferenceScore {
    
    private int geneNum;        // The number of genes
    private double[][] om1;     // Original matrix1: om1[geneNum][sampleNum]
    private double[][] om2;     // Original matrix2: om2[geneNum][sampleNum]
    private double[][] pm1;     // Predicted matrix1: pm1[geneNum][sampleNum]
    private double[][] pm2;     // Predicted matrix2: pm2[geneNum][sampleNum]
    private double[] score;     // The rank score of the genes: score[geneNum]
    
    public DifferenceScore(double[][] om1, double[][] om2, double[][] pm1, double[][] pm2) {
        initVariables(om1, om2, pm1, pm2);
        calculation();
    }
    
    private void initVariables(double[][] om1, double[][] om2, double[][] pm1, double[][] pm2) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("calculation.DifferenceScore() ... Begins.");
        }
        
        this.om1 = om1;
        this.om2 = om2;
        this.pm1 = pm1;
        this.pm2 = pm2;
        geneNum = om1.length;
        score = new double[geneNum];
    }
    
    private void calculation() {
        int colNum1 = om1[0].length;
        int colNum2 = om2[0].length;
        double td, nd;
        
        for(int i = 0; i < geneNum; i++) {
            td = 0;
            nd = 0;
            for(int j = 0; j < colNum1; j++) {
                if(pm1[i][j] != (-99999)) {
                    td = td + Math.abs(om1[i][j] - pm1[i][j]);
                }
            }
            for(int j = 0; j < colNum2; j++) {
                if(pm2[i][j] != (-99999)) {
                    nd = nd + Math.abs(om2[i][j] - pm2[i][j]);
                }
            }
            score[i] = Math.abs((td / colNum1) - (nd / colNum2));
        }
    }
    
    public double[] getScore() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("calculation.DifferenceScore().getScore()");
        }
        
        return score;
    }
}
