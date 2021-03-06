package com.example.app_music.ui.Notification;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.app_music.R;
import com.example.app_music.domain.Song;
import com.example.app_music.ui.Services.NotificationActionService;
import com.example.app_music.ui.home.playmusic.PlaySongActivity;

public class CreateNotification {
    public static final String CHANNEL_ID="channel1";
    public static final String ACTION_PLAY ="actionplay";
    public static final String ACTION_PRE="actionpre";
    public static final String ACTION_NEXT="actionnext";

    public static Notification notification;

    public static void createNotification(Context context, Song song,int playbutton,int pos,int size){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context,"tag");

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.backgroud_noti);

            PendingIntent pendingIntentPre;
            int drw_pre;
            if(pos == 0){
                pendingIntentPre=null;
                drw_pre=0;
            }else {
                Intent intentPre= new Intent(context, NotificationActionService.class).setAction(ACTION_PRE);
                pendingIntentPre = PendingIntent.getBroadcast(context,0,intentPre,PendingIntent.FLAG_UPDATE_CURRENT);
                drw_pre = R.drawable.ic_baseline_skip_previous_24;
            }

            Intent intentPlay= new Intent(context, NotificationActionService.class).setAction(ACTION_PLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context,0,intentPlay,PendingIntent.FLAG_UPDATE_CURRENT);

            PendingIntent pendingIntentNext;
            int drw_next;
            if(pos == 0){
                pendingIntentNext=null;
                drw_next=0;
            }else {
                Intent intentNext= new Intent(context, NotificationActionService.class).setAction(ACTION_NEXT);
                pendingIntentNext = PendingIntent.getBroadcast(context,0,intentNext,PendingIntent.FLAG_UPDATE_CURRENT);
                drw_next = R.drawable.ic_baseline_skip_next_24;
            }

            Intent resultIntent = new Intent(context, PlaySongActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(context,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);;

            //create notification
            notification = new NotificationCompat.Builder(context,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                    .setContentTitle(song.getSong_name())
                    .setContentText(song.getSingers())
                    .setLargeIcon(icon)
                    .setShowWhen(false)
                    .addAction(drw_pre,"Pre",pendingIntentPre)
                    .addAction(playbutton,"Play",pendingIntentPlay)
                    .addAction(drw_next,"Next",pendingIntentNext)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(resultPendingIntent)
                    .build();
            notificationManagerCompat.notify(1,notification);
        }
    }
}
