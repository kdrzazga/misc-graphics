package org.kd.common;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleLogger {

    public static void logBannerWithElapsedTime(String bannerPath) {
        //System.out.println(System.getProperty("user.dir"));
        List<String> banner = readTextfile(bannerPath);
        banner.forEach(line -> System.out.println(line));
        var frame = Gdx.graphics.getFrameId();
        System.out.println("Elapsed time: " + C64Helper.countElapsedTime() + " Frame: " + frame);
    }

    private static List<String> readTextfile(String resourcePath) {
        try (InputStream is = ConsoleLogger.class.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            String emptyEquivalentOfFile = "\n".repeat(50);
            return Arrays.asList(emptyEquivalentOfFile.split(""));
        }
    }
}
