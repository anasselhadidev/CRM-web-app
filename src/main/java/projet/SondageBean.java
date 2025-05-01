package projet;

import java.util.Date;

public class SondageBean {
    private QuestionBean question;
    private Date date;

    public SondageBean(QuestionBean question, Date date) {
        this.question = question;
        this.date = date;
    }

    public QuestionBean getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

 
}

