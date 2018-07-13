package com.example.jam.pandaroo_convo.discussion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import static java.lang.Math.toIntExact;

import com.example.jam.pandaroo_convo.PostSurveyActivity;
import com.example.jam.pandaroo_convo.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DiscussionActivity extends AppCompatActivity {

        // replace this with a real channelID from Scaledrone dashboard
        private Integer userID;
        private Integer focus_group_session;
        private EditText editText;
        private MessageAdapter messageAdapter;
        private ListView messagesView;
        private Timer countdown;
        private int time = 60;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            userID = getIntent().getExtras().getInt("User_ID");
            focus_group_session = getIntent().getExtras().getInt("focus_group");
            System.out.println("discussion " + userID.toString() + " " + focus_group_session.toString());
            FirebaseInstanceId.getInstance().getToken();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.discussion_chat);
            TextView question = findViewById(R.id.question);
            question.setText(getIntent().getExtras().getString("focus_group_question"));
            editText = (EditText) findViewById(R.id.editText);

            messageAdapter = new MessageAdapter(this);
            messagesView = (ListView) findViewById(R.id.messages_view);

            messagesView.setAdapter(messageAdapter);

            MemberData data = new MemberData(getRandomName(), getRandomColor());
            DatabaseReference messages = FirebaseDatabase.getInstance("https://convo-1522b.firebaseio.com").getReference().child("FG_sessions").child(Integer.toString(this.focus_group_session)).child("messages");

            messages.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    System.out.println(dataSnapshot.getValue().toString());
                    String message = (String) dataSnapshot.child("message").getValue();
                    Long user = (Long) dataSnapshot.child("user").getValue();

                    MemberData mdata = new MemberData("User: " + Long.toString(user), "#2062ac");
                    System.out.println("making bubble with data: " + message + "||" + user.toString());

                    // Hack to check equality of int and long (otherwise we have to upgrade to sdk minimum of 26)
                    final Message messageBubble = new Message(message, mdata, !((user < userID) | (user > userID)));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            messageAdapter.add(messageBubble);
                            // scroll the ListView to the last added element
                            messagesView.setSelection(messagesView.getCount() - 1);
                        }
                    });
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            this.countdown = new Timer();
            this.countdown.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (time <=0) {
                        completeDiscussion(messagesView);
                        countdown.cancel();
                    }
                }
            }, 0, 1000);
        }

        public void sendMessage(View view) {
            String message = editText.getText().toString();
            if (message.length() > 0) {
                DatabaseReference messages = FirebaseDatabase.getInstance().getReference().child("FG_sessions").child(Integer.toString(this.focus_group_session)).child("messages");
                String message_key =messages.push().getKey();
                HashMap<String, Object> message_data= new HashMap<>();
                message_data.put("user", this.userID);
                message_data.put("message", message);
                messages.child(message_key).setValue(message_data);
                editText.getText().clear();
            }
        }
        private String getRandomName() {
            String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
            String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
            return (
                    adjs[(int) Math.floor(Math.random() * adjs.length)] +
                            "_" +
                            nouns[(int) Math.floor(Math.random() * nouns.length)]
            );
        }

        private String getRandomColor() {
            Random r = new Random();
            StringBuffer sb = new StringBuffer("#");
            while(sb.length() < 7){
                sb.append(Integer.toHexString(r.nextInt()));
            }
            return sb.toString().substring(0, 7);
        }

    public void completeDiscussion(View view) {
        Intent myIntent = new Intent(this, PostSurveyActivity.class);
        myIntent.putExtra("userID", this.userID);
        startActivity(myIntent);
    }
}
