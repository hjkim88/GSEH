/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculation;

/**
 *
 * @author Hyunjin Kim
 */
public class PearsonCorr {
    
    private double pcc;         // Pearson Correlation Coefficient
    private double[] s1;        // The gene expression values of sample1: s1[geneNum]
    private double[] s2;        // The gene expression values of sample2: s2[geneNum]
    private double mean1;       // The mean value of sample1
    private double mean2;       // The mean value of sample2
    private int geneNum;        // The number of genes in s1 and in s2
    
    public PearsonCorr(double[] s1, double[] s2) {
        initVariables(s1, s2);
        calculation();
    }
    
    private void initVariables(double[] s1, double[] s2) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("calculation.PearsonCorr() ... Begins.");
        }
        
        pcc = 0;
        if(s1.length != s2.length) {
            System.out.println("Error: The length of S1 and the length of S2 should be the same");
            System.exit(0);
        }
        this.s1 = s1;
        this.s2 = s2;
        mean1 = calMean(s1);
        mean2 = calMean(s2);
        geneNum = s1.length;
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
    
    private void calculation() {
        double numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;
        
        for(int i = 0; i < geneNum; i++) {
            numerator = numerator + ((s1[i] - mean1) * (s2[i] - mean2));
            denominator1 = denominator1 + Math.pow((s1[i] - mean1), 2);
            denominator2 = denominator2 + Math.pow((s2[i] - mean2), 2);
        }
        
        pcc = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }
    
    public double getPCC() {
        return pcc;
    }
    
}
