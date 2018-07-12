package com.example.jam.pandaroo_convo.discussion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jam.pandaroo_convo.PostSurveyActivity;
import com.example.jam.pandaroo_convo.R;
import com.google.firebase.database.DataSnapshot;

import java.util.Random;

public class DiscussionActivity extends AppCompatActivity {

        // replace this with a real channelID from Scaledrone dashboard
        private Integer userID;
        private Integer focus_group_session;
        private EditText editText;
        private MessageAdapter messageAdapter;
        private ListView messagesView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
//      TODO: // pull these from the bundle
//           userID
//            focus_group_session
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editText = (EditText) findViewById(R.id.editText);

            messageAdapter = new MessageAdapter(this);
            messagesView = (ListView) findViewById(R.id.messages_view);
            messagesView.setAdapter(messageAdapter);

            MemberData data = new MemberData(getRandomName(), getRandomColor());

        }

        public void sendMessage(View view) {
            String message = editText.getText().toString();
            if (message.length() > 0) {
//                scaledrone.publish(roomName, message);
                editText.getText().clear();
            }
        }

        // TODO: put this on change of focus_group_messages
        public void onMessage(DataSnapshot snapshot) {

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
        startActivity(myIntent);
    }
}
