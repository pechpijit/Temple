package com.sdu.tample.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelQuiz {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quizTopic")
    @Expose
    private String quizTopic;
    @SerializedName("quizAnswer")
    @Expose
    private Integer quizAnswer;
    @SerializedName("quizStatus")
    @Expose
    private Integer quizStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuizTopic() {
        return quizTopic;
    }

    public void setQuizTopic(String quizTopic) {
        this.quizTopic = quizTopic;
    }

    public Integer getQuizAnswer() {
        return quizAnswer;
    }

    public void setQuizAnswer(Integer quizAnswer) {
        this.quizAnswer = quizAnswer;
    }

    public Integer getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(Integer quizStatus) {
        this.quizStatus = quizStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}