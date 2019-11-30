/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gseh;

/**
 *
 * @author Hyunjin Kim
 */
public class MakePM {
    
    private double[][] om;              // Original matrix: om[geneNum][sampleNum]
    private double[][] pm;              // Predicted matrix: pm[geneNum][sampleNum]
    private int[][] neighborNum;        // The number of neighbors for each gene in each sample: neighborNum[geneNum][sampleNum]
    private int[][][] neighborInfo;     // The indexes of a sample which is a neighbor of each gene in each sample: neighborInfo[geneNum][sampleNum][sampleNum-1]
    private double[][][] pccInfo;       // The Pearson correlation coefficients between given object(i-th gene, j-th sample) and its neighbors: pccInfo[geneNum][sampleNum][sampleNum-1]
    private double[] meanVector;        // The mean values of each sample in the matrix: meanVector[sampleNum]
    private int geneNum;                // The number of genes
    private int sampleNum;              // The number of samples
    private double pccThreshold;        // Pearson correlation coefficient
    
    public MakePM(String omPath, double threshold) {
        initVariables(omPath, threshold);
    }
    
    private void initVariables(String omPath, double threshold) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("gscf.MakePM() ... Begins.");
        }
        
        om = new file.LoadTSV(omPath).getGE();
        pccThreshold = threshold;
        meanVector = new calculation.MeanVector(om).getMeanVector();
        geneNum = om.length;
        sampleNum = om[0].length;
        pm = new double[geneNum][sampleNum];
        neighborNum = new int[geneNum][sampleNum];
        neighborInfo = new int[geneNum][sampleNum][sampleNum-1];
        pccInfo = new double[geneNum][sampleNum][sampleNum-1];
        for(int i = 0; i < geneNum; i++) {
            for(int j = 0; j < sampleNum; j++) {
                neighborNum[i][j] = 0;
            }
        }
        constructPM();
    }
    
    private void constructPM() {
        double numerator;
        double denominator;
        double pcc;
        
        for(int i = 0; i < geneNum; i++) {
            for(int j = 0; j < (sampleNum-1); j++) {
                for(int k = (j+1); k < sampleNum; k++) {
                    pcc = new calculation.PearsonCorr(makeSample(j, i), makeSample(k, i)).getPCC();
                    if(pcc >= pccThreshold) {
                        neighborInfo[i][j][neighborNum[i][j]] = k;
                        neighborInfo[i][k][neighborNum[i][k]] = j;
                        pccInfo[i][j][neighborNum[i][j]] = pcc;
                        pccInfo[i][k][neighborNum[i][k]] = pcc;
                        neighborNum[i][j]++;
                        neighborNum[i][k]++;
                    }
                }
            }
            
            for(int j = 0; j < sampleNum; j++) {
                numerator = 0;
                denominator = 0;
                for(int k = 0; k < neighborNum[i][j]; k++) {
                    numerator = numerator + ((om[i][neighborInfo[i][j][k]] - meanVector[neighborInfo[i][j][k]]) * pccInfo[i][j][k]);
                    denominator = denominator + Math.abs(pccInfo[i][j][k]);
                }
                if(neighborNum[i][j] != 0) {
                    pm[i][j] = calMean(makeSample(j, i)) + (numerator / denominator);
                }
                else {
                    pm[i][j] = -99999;
                }
            }
        }
    }
    
    private double calMean(double[] sample) {
        double mean = 0;
        int sampleLen = sample.length;
        
        for(int i = 0; i < sampleLen; i++) {
            mean = mean + sample[i];
        }
        mean = mean / ((double) sampleLen);
        
        return mean;
    }
    
    private double[] makeSample(int columnNum, int exceptionNum) {
        double[] d = new double[geneNum-1];
        for(int i = 0; i < (geneNum-1); i++) {
            if(i < exceptionNum) {
                d[i] = om[i][columnNum];
            }
            else {
                d[i] = om[i+1][columnNum];
            }
        }
        
        return d;
    }
    
    public double[][] getOM() {
        return om;
    }
    
    public double[][] getPM() {
        return pm;
    }
    
}
