/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 * @author Hyunjin Kim
 */
public class MakeGS {
    
    private String[] geneList;      // The list of all the genes: geneList[geneNum]
    private double[] geneScore;     // The ranking score of the gene list: geneScore[geneNum]
    private int geneNum;            // The number of genes
    
    public MakeGS(String[] geneList, double[] geneScore, String filePath) {
        initVariables(geneList, geneScore);
        make(filePath);
    }
    
    public MakeGS(global.GeneScoreList[] gsl, String filePath) {
        initVariables(gsl);
        make(filePath);
    }
    
    private void initVariables(String[] geneList, double[] geneScore) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.MakeGS() ... Begins.");
        }
        
        this.geneList = geneList;
        this.geneScore = geneScore;
        geneNum = geneList.length;
    }
    
    private void initVariables(global.GeneScoreList[] gsl) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.MakeGS() ... Begins.");
        }
        
        geneNum = gsl.length;
        geneList = new String[geneNum];
        geneScore = new double[geneNum];
        
        for(int i = 0; i < geneNum; i++) {
            geneList[i] = gsl[i].getGeneSymbol();
            geneScore[i] = gsl[i].getScore();
        }
    }
    
    private void make(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile= new PrintWriter(bw);
            
            for(int i = 0; i < geneNum; i++) {
                outFile.println(geneList[i] + "\t" + geneScore[i]);
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("file.MakeGS().make().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    public void start() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.MakeGS().start()");
        }
    }
    
}
