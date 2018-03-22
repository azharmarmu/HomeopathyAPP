package com.example.azharuddin.homeopathyapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.azharuddin.homeopathyapp.R;
import com.example.azharuddin.homeopathyapp.adapter.ChatListAdapter;
import com.example.azharuddin.homeopathyapp.firebase.DBsetup;
import com.example.azharuddin.homeopathyapp.model.ChatMessage;
import com.example.azharuddin.homeopathyapp.model.Status;
import com.example.azharuddin.homeopathyapp.model.UserType;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    private View rootView;
    private RecyclerView chatRecyclerView;
    private EmojiEditText emojiEditText;
    private ImageView emojiButton;
    private EmojiPopup emojiPopup;

    private ChatListAdapter listAdapter;
    private ArrayList<ChatMessage> chatMessages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));

        // initialize Views
        rootView = findViewById(R.id.chat_layout);
        chatRecyclerView = findViewById(R.id.recyclerView);
        emojiButton = findViewById(R.id.emojiButton);
        emojiEditText = findViewById(R.id.emojiEditText);

        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).build(emojiEditText);

        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emojiPopup.isShowing()) {
                    emojiPopup.toggle();
                    emojiEditText.setShowSoftInputOnFocus(false);
                } else {
                    emojiPopup.dismiss();
                }
            }
        });

        chatMessages = getChatHistory(); // if exists

        listAdapter = new ChatListAdapter(chatMessages, this);
        chatRecyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ChatScreenActivity.this);
        chatRecyclerView.setLayoutManager(layoutManager);
        chatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        chatRecyclerView.setAdapter(listAdapter);
        chatRecyclerView.scrollToPosition(chatMessages.size() - 1);
        chatRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                chatRecyclerView.scrollToPosition(chatMessages.size() - 1);
            }
        });
    }

    public void sendMessage(View view) {
        String message = emojiEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            long time = System.currentTimeMillis();
            chatMessages.add(new ChatMessage(message, UserType.SELF, Status.SENT, time));
            listAdapter.notifyDataSetChanged();
            emojiEditText.setText("");
        }
    }

    private final String TAG = "ChatScreen";

    // load chat history if exists
    public ArrayList<ChatMessage> getChatHistory() {
        new DBsetup().CHATS
                .collection("12345")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(
                            QuerySnapshot snapshot,
                            FirebaseFirestoreException e
                    ) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        if (snapshot != null) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            Log.d(TAG, "Current data: " + documents);
                        } else {
                            Log.d(TAG, "Current data: null");
                        }
                    }
                });
        return new ArrayList<>();
    }
}
