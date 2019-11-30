/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gseh;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 *
 * @author Hyunjin Kim
 */
public class Main {

    private double[][] om1;         // Original matrix1: om1[geneNum][sampleNum]
    private double[][] om2;         // Original matrix2: om2[geneNum][sampleNum]
    
    private double[][] pm1;         // Predicted matrix1: pm1[geneNum][sampleNum]
    private double[][] pm2;         // Predicted matrix2: pm2[geneNum][sampleNum]
    
    private String[] geneList;      // The list of all the genes: geneList[geneNum]
    private double[] geneScore;     // The ranking score of the gene list: geneScore[geneNum]
    private int geneNum;            // The number of genes
    
    public Main() {
        initVariables();
        buildPM();
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("gscf.Main() ... Begins.");
        }
        
        geneList = new file.LoadTSV(global.Variables.filePath + global.Variables.dataName1 + ".tsv").getGeneSymbol();
        geneNum = geneList.length;
    }
    
    /*
     * buildPM(): Construct predicted matrix1 and predicted matrix2 by using original matrix1 and original matrix2.
     */
    private void buildPM() {
        gseh.MakePM mpm = new gseh.MakePM(global.Variables.filePath + global.Variables.dataName1 + ".tsv", global.Variables.pccThreshold);
        om1 = mpm.getOM();
        pm1 = mpm.getPM();
        mpm = new gseh.MakePM(global.Variables.filePath + global.Variables.dataName2 + ".tsv", global.Variables.pccThreshold);
        om2 = mpm.getOM();
        pm2 = mpm.getPM();
    }
    
    /*
     * calculateScore(): Calculate rank score of the genes
     */
    private void calculateScore() {
        geneScore = new calculation.DifferenceScore(om1, om2, pm1, pm2).getScore();
        
        global.GeneScoreList gsl[] = new global.GeneScoreList[geneNum];
        for(int j = 0; j < geneNum; j++) {
            gsl[j] = new global.GeneScoreList();
            gsl[j].setGeneSymbol(geneList[j]);
            gsl[j].setScore(geneScore[j]);
        }
        Arrays.sort(gsl);
        new file.MakeGS(gsl, global.Variables.filePath + global.Variables.dataName1 + "-"
                + global.Variables.dataName2 + "_GeneScore.tsv").start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Start Time: " + new Timestamp(System.currentTimeMillis()));
        new gseh.Main().calculateScore();
        System.out.println("End Time: " + new Timestamp(System.currentTimeMillis()));
    }
}
