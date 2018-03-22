package com.example.azharuddin.homeopathyapp.firebase;

import com.example.azharuddin.homeopathyapp.utils.Constants;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by azharuddin on 16/3/18.
 */

public class DBsetup {
    private final FirebaseFirestore DATABASE = FirebaseFirestore.getInstance();
    private final CollectionReference ENVIRONMENT = DATABASE.collection(Constants.ENV);
    public final DocumentReference CHATS = ENVIRONMENT.document(Constants.CHATS);

}
