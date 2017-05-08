package com.sdu.tample.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelQuizAns {

    @SerializedName("quiz")
    @Expose
    private ModelQuiz quiz;
    @SerializedName("ans")
    @Expose
    private ArrayList<ModelAns> ans;

    public ModelQuiz getQuiz() {
        return quiz;
    }

    public void setQuiz(ModelQuiz quiz) {
        this.quiz = quiz;
    }

    public ArrayList<ModelAns> getAns() {
        return ans;
    }

    public void setAns(ArrayList<ModelAns> ans) {
        this.ans = ans;
    }
}