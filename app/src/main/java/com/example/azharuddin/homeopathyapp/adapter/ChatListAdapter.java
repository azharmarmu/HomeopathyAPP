package com.example.azharuddin.homeopathyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.azharuddin.homeopathyapp.R;
import com.example.azharuddin.homeopathyapp.model.ChatMessage;
import com.example.azharuddin.homeopathyapp.model.Status;
import com.example.azharuddin.homeopathyapp.model.UserType;
import com.example.azharuddin.homeopathyapp.utils.AndroidUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by azharuddin on 13/3/18.
 */

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatMessage> chatMessages;
    private Context context;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("h:mm a", Locale.US);

    public ChatListAdapter(ArrayList<ChatMessage> chatMessages, Context context) {
        this.chatMessages = chatMessages;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                viewHolder = new MessageSenderViewHolder(
                        inflater.inflate(R.layout.chat_cell_sender_message,
                                parent,
                                false));
                break;
            case 1:
                viewHolder = new MessageReceiverViewHolder(
                        inflater.inflate(R.layout.chat_cell_receiver_message,
                                parent,
                                false));
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = chatMessages.get(position);
        if (UserType.SELF.ordinal() == message.getUserType().ordinal()) {
            senderTextMessage((MessageSenderViewHolder) holder, message);
        } else {
            receiverTextMessage((MessageReceiverViewHolder) holder, message);
        }
    }

    private void senderTextMessage(MessageSenderViewHolder holder, ChatMessage message) {
        String time = AndroidUtilities.getDate(message.getMessageTime());
        holder.tvMessage.setText(message.getMessageText());
        holder.tvTime.setText(String.valueOf(time));
        if (Status.SENT.ordinal() == message.getMessageStatus().ordinal())
            holder.ivStatus.setImageDrawable(context.getDrawable(R.drawable.ic_done_sent));
    }

    private void receiverTextMessage(MessageReceiverViewHolder holder, ChatMessage message) {
        String time = AndroidUtilities.getDate(message.getMessageTime());
        holder.tvMessage.setText(message.getMessageText());
        holder.tvTime.setText(String.valueOf(time));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    private class MessageSenderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvMessage;
        ImageView ivStatus;

        private MessageSenderViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            ivStatus = itemView.findViewById(R.id.ivStatus);
        }
    }

    private class MessageReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvMessage;

        private MessageReceiverViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }

    }

    public int getItemViewType(int position) {
        ChatMessage message = chatMessages.get(position);
        return message.getUserType().ordinal();
    }
}
