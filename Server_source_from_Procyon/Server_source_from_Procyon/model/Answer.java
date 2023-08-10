// 
// Decompiled by Procyon v0.5.36
// 

package model;

import java.io.Serializable;

public class Answer implements Serializable
{
    static final long serialVersionUID = 2L;
    Student student;
    String reverseStringAnswer;
    String normalizationStringAnswer;
    int maxNumericAnswer;
    int uclnNumericAnswer;
    int bcnnNumericAnswer;
    boolean isAlreadyRegistration;
    boolean isReverseStringAnswerRight;
    boolean isMaxNumericAnswerRight;
    boolean isNormalizationStringAnswerRight;
    boolean isBSCNNNumericAnswerRight;
    boolean isUSCLNNumericAnswerRight;
    
    public Answer() {
        this.isAlreadyRegistration = false;
        this.isReverseStringAnswerRight = false;
        this.isMaxNumericAnswerRight = false;
        this.isNormalizationStringAnswerRight = false;
        this.isBSCNNNumericAnswerRight = false;
        this.isUSCLNNumericAnswerRight = false;
    }
    
    public boolean isIsAlreadyRegistration() {
        return this.isAlreadyRegistration;
    }
    
    public void setIsAlreadyRegistration(final boolean isAlreadyRegistration) {
        this.isAlreadyRegistration = isAlreadyRegistration;
    }
    
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(final Student student) {
        this.student = student;
    }
    
    public String getStringAnswer() {
        return this.reverseStringAnswer;
    }
    
    public void setStringAnswer(final String stringAnswer) {
        this.reverseStringAnswer = stringAnswer;
    }
    
    public int getNumericAnswer() {
        return this.maxNumericAnswer;
    }
    
    public void setNumericAnswer(final int numericAnswer) {
        this.maxNumericAnswer = numericAnswer;
    }
    
    public boolean isIsStringAnswerRight() {
        return this.isReverseStringAnswerRight;
    }
    
    public void setIsStringAnswerRight(final boolean isStringAnswerRight) {
        this.isReverseStringAnswerRight = isStringAnswerRight;
    }
    
    public boolean isIsNumericAnswerRight() {
        return this.isMaxNumericAnswerRight;
    }
    
    public void setIsNumericAnswerRight(final boolean isNumericAnswerRight) {
        this.isMaxNumericAnswerRight = isNumericAnswerRight;
    }
    
    public boolean isIsReverseStringAnswerRight() {
        return this.isReverseStringAnswerRight;
    }
    
    public void setIsReverseStringAnswerRight(final boolean isReverseStringAnswerRight) {
        this.isReverseStringAnswerRight = isReverseStringAnswerRight;
    }
    
    public boolean isIsMaxNumericAnswerRight() {
        return this.isMaxNumericAnswerRight;
    }
    
    public void setIsMaxNumericAnswerRight(final boolean isMaxNumericAnswerRight) {
        this.isMaxNumericAnswerRight = isMaxNumericAnswerRight;
    }
    
    public boolean isIsNormalizationStringAnswerRight() {
        return this.isNormalizationStringAnswerRight;
    }
    
    public void setIsNormalizationStringAnswerRight(final boolean isNormalizationStringAnswerRight) {
        this.isNormalizationStringAnswerRight = isNormalizationStringAnswerRight;
    }
    
    public boolean isIsBSCNNNumericAnswerRight() {
        return this.isBSCNNNumericAnswerRight;
    }
    
    public void setIsBSCNNNumericAnswerRight(final boolean isBSCNNNumericAnswerRight) {
        this.isBSCNNNumericAnswerRight = isBSCNNNumericAnswerRight;
    }
    
    public boolean isIsUSCLNNumericAnswerRight() {
        return this.isUSCLNNumericAnswerRight;
    }
    
    public void setIsUSCLNNumericAnswerRight(final boolean isUSCLNNumericAnswerRight) {
        this.isUSCLNNumericAnswerRight = isUSCLNNumericAnswerRight;
    }
    
    public String getReverserStringAnswer() {
        return this.reverseStringAnswer;
    }
    
    public void setReverserStringAnswer(final String reverserStringAnswer) {
        this.reverseStringAnswer = reverserStringAnswer;
    }
    
    public String getNormalizationStringAnswer() {
        return this.normalizationStringAnswer;
    }
    
    public void setNormalizationStringAnswer(final String normalizationStringAnswer) {
        this.normalizationStringAnswer = normalizationStringAnswer;
    }
    
    public int getMaxNumericAnswer() {
        return this.maxNumericAnswer;
    }
    
    public void setMaxNumericAnswer(final int maxNumericAnswer) {
        this.maxNumericAnswer = maxNumericAnswer;
    }
    
    public int getUclnNumericAnswer() {
        return this.uclnNumericAnswer;
    }
    
    public void setUclnNumericAnswer(final int uclnNumericAnswer) {
        this.uclnNumericAnswer = uclnNumericAnswer;
    }
    
    public int getBcnnNumericAnswer() {
        return this.bcnnNumericAnswer;
    }
    
    public void setBcnnNumericAnswer(final int bcnnNumericAnswer) {
        this.bcnnNumericAnswer = bcnnNumericAnswer;
    }
}
