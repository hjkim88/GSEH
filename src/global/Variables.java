/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;

/**
 *
 * @author Hyunjin Kim
 */
public class Variables {
    
    public final static boolean debuggingMode = false;                                          // Debug mode configuration
    
    public final static double pccThreshold = 0.9;                                              // Pearson correlation coefficient threshold
    
    public final static String dataName1 = "Singh_t52";                                         // The name of gene expression data matrix of class1 
    public final static String dataName2 = "Singh_n50";                                         // The name of gene expression data matrix of class2
    
    public final static String filePath = System.getProperty("user.dir") + "/data/Singh/";      // The current file path
    
}
