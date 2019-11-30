/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Hyunjin Kim
 */
public class LoadTSV {
    
    private String[] sampleName;        // The names of the samples in data: sampleName[sampleNum]
    private String[] sampleClass;       // The class labels of the samples in data: samleClass[sampleNum]
    private String[] geneSymbol;        // The gene symbols in data: geneSymbol[geneNum]
    private double[][] ge;              // The gene expression matrix in data: ge[geneNum][sampleNum]
    
    public LoadTSV(String gePath) {
        initVariables();
        read(gePath);
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.LoadTSV() ... Begins.");
        }
    }
    
    private void read(String gePath) {
        try {
            File f = new File(gePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            int sampleNum = br.readLine().split("\t").length-1;
            int geneNum = 0;
            
            br.readLine();
            while(br.readLine() != null) {
                geneNum++;
            }
            
            sampleName = new String[sampleNum];
            sampleClass = new String[sampleNum];
            geneSymbol = new String[geneNum];
            ge = new double[geneNum][sampleNum];
            
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String[] line;
            
            line = br.readLine().split("\t");
            for(int i = 0; i < sampleNum; i++) {
                sampleName[i] = line[i+1];
            }
            
            line = br.readLine().split("\t");
            for(int i = 0; i < sampleNum; i++) {
                sampleClass[i] = line[i+1];
            }
            
            for(int i = 0; i < geneNum; i++) {
                line = br.readLine().split("\t");
                geneSymbol[i] = line[0];
                for(int j = 0; j < sampleNum; j++) {
                    ge[i][j] = Double.parseDouble(line[j+1]);
                }
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("file.LoadTSV().read().IOException");
            }   
            ioe.printStackTrace();
        }
    }
    
    public String[] getSampleName() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.LoadTSV().getSampleName()");
        }
        
        return sampleName;
    }
    
    public String[] getSampleClass() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.LoadTSV().getSampleClass()");
        }
        
        return sampleClass;
    }
    
    public String[] getGeneSymbol() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.LoadTSV().getGeneSymbol()");
        }
        
        return geneSymbol;
    }
    
    public double[][] getGE() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("file.LoadTSV().getGE()");
        }
        
        return ge;
    }
    
}
