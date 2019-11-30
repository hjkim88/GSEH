/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

/**
 *
 * @author Hyunjin Kim
 */
public class GeneScoreList implements Comparable<GeneScoreList> {
    
    private String gene;        // Gene name
    private double score;       // Gene score
    
    public GeneScoreList() {}
    
    @Override
    public int compareTo(GeneScoreList gsl) {
        if(this.score < gsl.score) {
            return 1;
        }
        else if(this.score == gsl.score) {
            return 0;
        }
        else {
            return -1;
        }
    }
    
    public void setGeneSymbol(String geneSymbol) {
        this.gene = geneSymbol;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    
    public String getGeneSymbol() {
        return gene;
    }
    
    public double getScore() {
        return score;
    }
    
}
