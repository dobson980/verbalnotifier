package com.dobson980.verbalnotifier;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class AudioPlayer {

    private Queue<String> clipQueue = new LinkedList<>();
    private Clip currentClip = null;

    private void playNextClip() {
        if (clipQueue.isEmpty()) {
            return;
        }

        String resourcePath = clipQueue.poll();
        try (InputStream audioSrc = getClass().getResourceAsStream(resourcePath)) {
            if (audioSrc == null) {
                System.out.println("Resource not found: " + resourcePath);
                return;
            }
            try (InputStream bufferedIn = new BufferedInputStream(audioSrc);
                 AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn)) {

                currentClip = AudioSystem.getClip();
                currentClip.open(audioStream);
                currentClip.start();
                currentClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                        playNextClip(); // Play the next clip in the queue
                    }
                });
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void queueVoiceNotification(String folderName, String fileName) {
        String resourcePath = "/audio/" + folderName + "/" + fileName;
        clipQueue.offer(resourcePath);

        if (currentClip == null || !currentClip.isRunning()) {
            playNextClip();
        }
    }
}
