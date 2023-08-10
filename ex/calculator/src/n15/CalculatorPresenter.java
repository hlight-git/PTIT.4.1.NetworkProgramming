/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n15;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 *
 * @author ADMIN
 */
public class CalculatorPresenter {
    private final CalculatorView view;
    private final CalculatorModel calculator;
    private DecimalFormat format = new DecimalFormat("0.#");
    
    public CalculatorPresenter(CalculatorView view){
        this.view = view;
        calculator = new CalculatorModel();
        reset();
    }
    private String getAns(){
        return format.format(calculator.getAns());
    }
    public void setOperator(char operator){
        calculator.setCalculated(false);
        calculator.setOperator(operator);
        view.getLoperator().setText("" + operator);
    }
    
    private void resetField(JTextPane field){
        field.setText("");
    }
    private void resetView(){
        resetField(view.getWorkField());
        resetField(view.getAnsField());
    }
    
    public void reset(){
        calculator.resetCalculator(0.0);
        calculator.setCalculated(false);
        resetView();
    }
    
    private Double getNum(){
        String num = view.getWorkField().getText();
        resetField(view.getWorkField());
        return Double.parseDouble(num);
    }
    
    private void add(Double other){
        calculator.setAns(calculator.getAns() + other);
    }
    private void sub(Double other){
        calculator.setAns(calculator.getAns() - other);
    }
    private void mul(Double other){
        calculator.setAns(calculator.getAns() * other);
    }
    private void div(Double other){
        if (other.equals(0.0)){
            JOptionPane.showMessageDialog(view, "Cannot divide by 0.");
            return;
        }
        calculator.setAns(calculator.getAns() / other);
    }
    public void showResult(){
        calculate();
        view.getAnsField().setText(getAns());
        calculator.resetCalculator(calculator.getAns());
        setOperator(' ');
        calculator.setCalculated(true);
    }
    public void calculate(){
        Double num;
        try {
            num = getNum();
        } catch (NumberFormatException e){
            return;
        }
        if (calculator.isCalculated()){
            return;
        }
        switch(calculator.getOperator()){
            case '+': add(num);break;
            case '-': sub(num);break;
            case '*': mul(num);break;
            case '/': div(num);break;
            default: calculator.setAns(num);break;
        }
        view.getAnsField().setText(getAns());
    }
    
    public void addDigit(int digit){
        String cur;
        calculator.setCalculated(false);
        try{
            cur = format.format(getNum());
            if (cur.equals("0")){
                cur = "";
            }
        } catch (NumberFormatException e){
            cur = "";
        }
        this.view.getWorkField().setText(cur + digit);
    }
}