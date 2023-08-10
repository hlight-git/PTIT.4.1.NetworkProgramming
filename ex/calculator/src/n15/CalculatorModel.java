/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n15;

/**
 *
 * @author ADMIN
 */
public class CalculatorModel {
    private Double ans;
    private char operator;
    private boolean calculated;

    public CalculatorModel() {
    }

    public Double getAns() {
        return ans;
    }

    public char getOperator() {
        return operator;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setAns(Double ans) {
        this.ans = ans;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
    
    public void resetCalculator(Double ans){
        this.ans = ans;
        operator = ' ';
    }
}
