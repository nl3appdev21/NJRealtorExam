package com.nl3designs.njrealtorexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {

    private Button btn;

    private TextView tv_cardQuestion;
    private TextView tv_cardAnswer;
    private TextView tv_answerDetail;

    private String[] flashCards = {
        "You are working with a customer and have found the perfect property. You are getting ready to prepare an offer when they ask, \\\"Do we need a lawyer?\\\" How should you answer?",
        "You work for Broker A. Agent Bill, who also works for Broker A, asks you for help in closing a sale he is working on, and offers you 10 percent of his commission for the assistance. After the settlement, who will pay your commission to you?\",",
        "A franchise office running an advertisement in the local newspaper must make sure the ad includes the following statement?",
        "Which type of ownership can only be held by a husband and wife? "
    };

    private String[] cardAnswers = {
        "It is up to the customer.",
        "Broker A",
        "Each office is independently owned and operated.",
        "Tenancy by the Entireties "
    };

    private String[] answerDetails = {
        "Whether to retain an attorney is always the customer or client's choice. You can not tell them absolutely yes or no on whether to retain an attorney. You also cannot act as an attorney because you are not licensed to do so. Never say no, or \\\"yes, you must\\\". Explain that while some people are comfortable not using a lawyer, buying a home is a complicated process and, should any problems arise, an attorney could prove to be of significant value. This must be a decision they make themselves, with you answering their questions carefully and honestly",
        "You can never receive any money from anyone except your employing broker. Even if a client or customer wants to give you a monetary gift to show you their gratitude, it must be given to the broker who will process it through the proper channels and then pay it to you.",
        "Franchise offices must let the public know that each office is under separate ownership and may differ from other offices under the same franchise name.",
        "A married couple do not have to choose tenants by entireties, but they are the only property owners that can assume this form of ownership. Like joint tenants, there is the right of survivorship. In tenancy by the entireties, when one party dies the remaining party gets full ownership. In joint tenancy, when one dies, the remaining partner(s) absorb the ownership. It can not be passed by inheritance, as in tenancy in common."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        Log.d("skip","flash");  //  ??

        //  ?? new new ??
        //  private tv_cardQuestion=findViewById(R.id.cardQuestion);

        for (int i = 0; i < flashCards.length; i++){

        }

        //  ??  new new ??

        btn = findViewById(R.id.btn_menu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

        btn = findViewById(R.id.btn_takeTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

    }

    private void gotoMenu() {
        finish();
    }

    private void startTest() {

        Intent intent = new Intent(FlashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}