package org.kd.goodjob;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Demo;

public class GoodJobMrJobs extends Demo {


    public static void main(String[] args) {
        GoodJobMrJobs.analyzeArguments(args);
        var config = Demo.createConfig("Good job Mr. Jobs");
        config.width = 1152;//90% 1280;
        config.height = 922; //90% of 1024
        //config.fullscreen = false;

        new LwjglApplication(new GoodJobAnimationMgr(), config);
    }
}
