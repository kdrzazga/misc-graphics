package org.kd.goodjob.appendix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannerApple {
    private final static List<String> l1 = Arrays.asList("\n".repeat(25).split(""));

    private final static List<String> l2 = Arrays.asList(

              " MMMMMMMMMMMMMMMMMMMMMMMMMW0l':KMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMMMW0l':KMMMMMMMMMM"
            , " MMMMMMMMMMMMMMMMMMMMMMMWO:.  ,0MMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMWO:.  ,0MMMMMMMMMM"
            , " MMMMMMMMMMMMMMMMMMMMMMXl.    cNMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMXl.    cNMMMMMMMMMM"
            , " MMMMMMMMMMMMMMMMMMMMMNl     ;KMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMNl     ;KMMMMMMMMMMM"
            , " MMMMMMMMMMMMMMMMMMMMM0'   .oXMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMM0'   .oXMMMMMMMMMMMM"
            , " MMMMMMMMN0xlc::coONMM0:,cdkxl:;;coONMMMMM MMMMMMMMN0xlc::coONMM0:,cdkxl:;;coONMMMMM"
            , " MMMMMNOc'         .:cccc:'.        ,dXMMM MMMMMNOc'         .:cccc:'.        ,dXMMM"
            , " MMMWO;                              .xWMM MMMWO;                              .xWMM"
            , " MMWd.                              ;OWMMM MMWd.                              ;OWMMM"
            , " MWx.                              :XMMMMM MWx.                              :XMMMMM"
            , " MK;                              .OMMMMMM MK;                              .OMMMMMM"
            , " MO.                              ,KMMMMMM MO.                              ,KMMMMMM"
            , " MO.                              '0MMMMMM MO.                              '0MMMMMM"
            , " MK,                               lNMMMMM MK,                               lNMMMMM"
            , " MWc                                cKMMMM MWc                                cKMMMM"
            , " MMk.                                .lOWM MMk.                                .lOWM"
            , " MMNc                                  .xM MMNc                                  .xM"
            , " MMMK,                                 .OM MMMK,                                 .OM"
            , " MMMMO.                                oWM MMMMO.                                oWM"
            , " MMMMWO'                             .oNMM MMMMWO'                             .oNMM"
            , " MMMMMM0;                           ,kWMMM MMMMMM0;                           ,kWMMM"
            , " MMMMMMMNd'       .',;;;'.       .;xNMMMMM MMMMMMMNd'       .',;;;'.       .;xNMMMMM"
            , " MMMMMMMMMXo,...;oOXWMMMN0xc,'';lONMMMMMMM MMMMMMMMMXo,...;oOXWMMMN0xc,'';lONMMMMMMM"
    );

    public static List<String> lines = new ArrayList<>(50);

    static {
        lines.addAll(l1);
        lines.addAll(l2);
    }
}
