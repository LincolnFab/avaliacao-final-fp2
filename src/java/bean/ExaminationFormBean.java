/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author linkf
 */
@ViewScoped
@Named("examinationFormBean")
public class ExaminationFormBean implements Serializable{
    private String comment;
    private String radioVal = "comum";

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRadioVal() {
        return radioVal;
    }

    public void setRadioVal(String radioVal) {
        this.radioVal = radioVal;
    }

}
