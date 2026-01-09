package org.kd.goodjob.appendix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannerTombstone {
    private final static List<String> l1 = Arrays.asList(" \n".repeat(12).split(""));

    private final static List<String> l2 = Arrays.asList(

     "                            +-----+                      "
    ,"                            |     |                      "
    ,"                       +----+     +----+                 "
    ,"                       |       +       |                 "
    ,"                       +----+     +----+                 "
    ,"                            |     |                      "
    ,"                            |     |                      "
    ,"                 ___________|     |__________            "
    ,"                /                            \\           "
    ,"               /                              \\          "
    ,"              /  ****  *****      *   *        \\         "
    ,"             /   *       *        *   *         \\        "
    ,"            /    *       *        *   *          \\       "
    ,"           /     ****    * *****  *   *  *****    \\      "
    ,"           |        *    * *   *  *   *  *   *    |      "
    ,"           |        *    * *****  *  *   *****    |      "
    ,"           |        *    * *      * *    *        |      "
    ,"           |     ****    * *****  **     *****    |      "
    ,"           |                                      |      "
    ,"           |   ********          *                |      "
    ,"           |          *          *                |      "
    ,"           |          *  *****   *****   *****    |      "
    ,"           |          *  *   *   *   *   *        |      "
    ,"           |          *  *   *   *   *   *****    |      "
    ,"           |          *  *   *   *   *       *    |      "
    ,"           |          *  *****   *****   *****    |      "
    ,"           |         *                            |      "
    ,"           |       *      1955 - 2011             |      "
    ,"           |     *                                |      "
    ,"           |                                      |      "
    ,"           |   ____   ____   ____   ____   ____   |      "
    ,"            \\                                      \\     "
    ,"             \\                                      \\    "
    ,"              \\                                      \\   "
    ,"               \\______________________________________\\  "
    ,"                                                         "
    ,"                                                         "
    ,"                                                         "            
    );

    public static List<String> lines = new ArrayList<>(50);

    static {
        lines.addAll(l1);
        lines.addAll(l2);
    }
}
