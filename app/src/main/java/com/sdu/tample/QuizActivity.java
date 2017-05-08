package com.sdu.tample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelQuizAns;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    ArrayList<ModelQuizAns> posts;
    TextView txt_quiz,txt_sum,txt_title;
    int[] ans;
    int index = 0;
    FancyButton btn_ans1, btn_ans2, btn_ans3, btn_ans4, btn_finish;
    Toolbar toolbar;
    ProgressBar view_load;
    LinearLayout quiz,view_ans;
    ImageView toon1, toon2;
    int sumChoise = 0;
    int activeChoise = 0;

    @Override
    public void onBackPressed() {
        dialogExit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);
        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ข้อ " + (index + 1));

        ans = new int[5];
        quiz = (LinearLayout) findViewById(R.id.quiz);
        view_ans = (LinearLayout) findViewById(R.id.view_ans);
        txt_quiz = (TextView) findViewById(R.id.txt_quiz);
        txt_sum = (TextView) findViewById(R.id.txt_sum);
        txt_title = (TextView) findViewById(R.id.txt_title);
        btn_ans1 = (FancyButton) findViewById(R.id.btn_ans1);
        btn_ans2 = (FancyButton) findViewById(R.id.btn_ans2);
        btn_ans3 = (FancyButton) findViewById(R.id.btn_ans3);
        btn_ans4 = (FancyButton) findViewById(R.id.btn_ans4);
        btn_finish = (FancyButton) findViewById(R.id.btn_finish);
        view_load = (ProgressBar) findViewById(R.id.view_load);
        toon1 = (ImageView) findViewById(R.id.ic_toon1);
        toon2 = (ImageView) findViewById(R.id.ic_toon2);

        view_load.setVisibility(View.VISIBLE);
        quiz.setVisibility(View.INVISIBLE);

        btn_ans1.setOnClickListener(this);
        btn_ans2.setOnClickListener(this);
        btn_ans3.setOnClickListener(this);
        btn_ans4.setOnClickListener(this);
        btn_finish.setOnClickListener(this);

        new ConnectAPI().getQuizAll(mContext);
    }

    public void setQuiz(String string) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelQuizAns>>() {
        }.getType();
        Collection<ModelQuizAns> enums = gson.fromJson(string, collectionType);
        posts = new ArrayList<ModelQuizAns>(enums);
        nextQuiz();
        view_load.setVisibility(View.INVISIBLE);
        quiz.setVisibility(View.VISIBLE);
    }

    private void dialogExit() {
        new AlertDialog.Builder(mContext)
                .setTitle("ยืนยันการออกจากคำถาม")
                .setMessage("กด 'ตกลง' เพื่อยืนยันการออก")
                .setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
                .setPositiveButton("ยกเลิก", null)
                .show();
    }

    private void dialogAnsert(final int choise) {
        new AlertDialog.Builder(mContext)
                .setTitle("เพิ่มเติม")
                .setMessage(posts.get(index).getAns().get(choise-1).getAnswerDetail())
                .setNegativeButton("ตอบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setAnswer(choise);
                    }
                })
                .setPositiveButton("ไม่ตอบ", null)
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ans1:
                if (posts.get(index).getAns().get(0).getAnswerDetail().length() > 20) {
                    dialogAnsert(1);
                } else {
                    setAnswer(1);
                }
                break;
            case R.id.btn_ans2:
                if (posts.get(index).getAns().get(1).getAnswerDetail().length() > 20) {
                    dialogAnsert(2);
                } else {
                    setAnswer(2);
                }
                break;
            case R.id.btn_ans3:
                if (posts.get(index).getAns().get(2).getAnswerDetail().length() > 20) {
                    dialogAnsert(3);
                } else {
                    setAnswer(3);
                }
                break;
            case R.id.btn_ans4:
                if (posts.get(index).getAns().get(3).getAnswerDetail().length() > 20) {
                    dialogAnsert(4);
                } else {
                    setAnswer(4);
                }
                break;
            case R.id.btn_finish:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    private void nextQuiz() {
        if (index < 5) {
            txt_title.setText("ข้อ " + (index + 1));
            txt_quiz.setText(posts.get(index).getQuiz().getQuizTopic());
            if (posts.get(index).getAns().get(0).getAnswerDetail().length() > 20) {
                btn_ans1.setText(posts.get(index).getAns().get(0).getAnswerDetail().substring(0, 20)+"...\n(กดดูเพิ่มเติม)");
            } else {
                btn_ans1.setText(posts.get(index).getAns().get(0).getAnswerDetail());
            }
            if (posts.get(index).getAns().get(1).getAnswerDetail().length() > 20) {
                btn_ans2.setText(posts.get(index).getAns().get(1).getAnswerDetail().substring(0,20)+"...\n(กดดูเพิ่มเติม)");
            }else {
                btn_ans2.setText(posts.get(index).getAns().get(1).getAnswerDetail());
            }
            if (posts.get(index).getAns().get(2).getAnswerDetail().length() > 20) {
                btn_ans3.setText(posts.get(index).getAns().get(2).getAnswerDetail().substring(0,20)+"...\n(กดดูเพิ่มเติม)");
            }else {
                btn_ans3.setText(posts.get(index).getAns().get(2).getAnswerDetail());
            }
            if (posts.get(index).getAns().get(3).getAnswerDetail().length() > 20) {
                btn_ans4.setText(posts.get(index).getAns().get(3).getAnswerDetail().substring(0,20)+"...\n(กดดูเพิ่มเติม)");
            }else {
                btn_ans4.setText(posts.get(index).getAns().get(3).getAnswerDetail());
            }
        }
    }

    private void setAnswer(int data) {
        if (index < 5) {
            ans[index] = data;
            index++;
            nextQuiz();
        }
        if (index == 5) {
            for (int i = 0; i < ans.length; i++) {
                if (ans[i] == posts.get(i).getQuiz().getQuizAnswer()) {
                    sumChoise+=20;
                    activeChoise++;
                }
            }
            txt_sum.setText(sumChoise + " %");
            txt_title.setText("ถูก  " + activeChoise + " ข้อ");
            quiz.setVisibility(View.INVISIBLE);
            view_ans.setVisibility(View.VISIBLE);
            toon1.setVisibility(View.VISIBLE);
            toon2.setVisibility(View.VISIBLE);
            btn_finish.setVisibility(View.VISIBLE);
            txt_sum.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
