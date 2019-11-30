/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculation;

/**
 *
 * @author Hyunjin Kim
 */
public class MeanVector {
    
    private double[][] matrix;          // Gene expression matrix: matrix[geneNum][sampleNum]
    private double[] meanVector;        // The mean values of each sample in the matrix: meanVector[sampleNum]
    private int geneNum;                // The number of genes in the matrix
    private int sampleNum;              // The number of samples in the matrix
    
    public MeanVector(double[][] matrix) {
        initVariables(matrix);
        calMean();
    }
    
    private void initVariables(double[][] matrix) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("calculation.MeanVector() ... Begins.");
        }
        
        this.matrix = matrix;
        geneNum = matrix.length;
        sampleNum = matrix[0].length;
        meanVector = new double[sampleNum];
    }
    
    private void calMean() {
        double d;
        for(int i = 0; i < sampleNum; i++) {
            d = 0;
            for(int j = 0; j < geneNum; j++) {
                d = d + matrix[j][i];
            }
            meanVector[i] = d / ((double) geneNum);
        }
    }
    
    public double[] getMeanVector() {
        return meanVector;
    }
    
}
