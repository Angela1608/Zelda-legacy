package zelda.scene;

import java.awt.Polygon;
import java.awt.Rectangle;
import zelda.enemy.BlueSoldier;
import zelda.enemy.WhiteSoldier;
import zelda.engine.Game;
import zelda.items.Guard;
import zelda.items.Heart;
import zelda.items.Rupee;
import zelda.karacter.Direction;

public class CastleScene extends ZeldaScene {

    private static final int HYRULE_SCENE_X = 252;
    private static final int HYRULE_SCENE_Y = 607;
    private static final int CASTLE_BASEMENT_X = 254;
    private static final int CASTLE_BASEMENT_Y = 1;
    private static final int LINK_START_X = 250;
    private static final int LINK_START_Y_HYRULE = 326;
    private static final int LINK_START_Y_CASTLE_BASEMENT = 119;
    private static final String IMAGE_CASTLE = "images/castle.png";
    private static final String SOUNDS_CASTLE = "sounds/castle.mp3";
    private final Rectangle exitUp = new Rectangle(500, 88, 27, 20);
    private final Rectangle exitDown = new Rectangle(500, 967, 27, 20);

    public CastleScene(Game game, String entrance) {
        super(game, IMAGE_CASTLE, "CastleScene");
        exits.add(exitUp);
        exits.add(exitDown);
        initializePolygons();
        initializeSolids();
        initializeGoObjects();
        handleMusic(game, entrance);
        handleSwitchScene(entrance);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
        if (exit == exitUp) {
            game.setScene(new CastleBasementScene(game, "CastleScene"));
        }
        if (exit == exitDown) {
            game.setScene(new HyruleScene(game, "CastleScene"));
        }
    }

    @Override
    public void handleSwitchScene(String entrance) {
        if (entrance.equals("HyruleScene")) {
            moveScene(HYRULE_SCENE_X, HYRULE_SCENE_Y);
            game.getLink().setXHardCore(LINK_START_X);
            game.getLink().setYHardCore(LINK_START_Y_HYRULE);
        }
        if (entrance.equals("CastleBasementScene")) {
            moveScene(CASTLE_BASEMENT_X, CASTLE_BASEMENT_Y);
            game.getLink().setXHardCore(LINK_START_X);
            game.getLink().setYHardCore(LINK_START_Y_CASTLE_BASEMENT);
        }
    }

    private void handleMusic(Game game, String entrance) {
        if (!game.getSong().equals(SOUNDS_CASTLE)) {
            game.stopMusic();
            game.playMusic(SOUNDS_CASTLE, true);
        }
    }

    private void initializePolygons() {
        createMiddenbovenmuur();
        createStukmuur();
        createStukmuur1();
        createStukmuur2();
        createRechtsonder();
        createRechts();
        createRechtsmidden();
        createLinksonder();
        createLinks();
        createZuil();
        createZuil1();
        createZuil2();
        createZuil3();
        createZuil4();
        createZuil5();
        createZuil6();
        createZuil7();
        createMidden();
        createMidden1();
        createPot();
        createDing();
        createPot1();
        createDing1();
        createDing2();
        createDing3();
    }

    private void initializeSolids() {
        solids.add(createMiddenbovenmuur());
        solids.add(createStukmuur());
        solids.add(createStukmuur1());
        solids.add(createStukmuur2());
        solids.add(createRechtsonder());
        solids.add(createRechts());
        solids.add(createRechtsmidden());
        solids.add(createLinksonder());
        solids.add(createLinks());
        solids.add(createZuil1());
        solids.add(createZuil2());
        solids.add(createZuil3());
        solids.add(createZuil4());
        solids.add(createZuil5());
        solids.add(createZuil6());
        solids.add(createZuil7());
        solids.add(createZuil());
        solids.add(createMidden());
        solids.add(createMidden1());
        solids.add(createPot());
        solids.add(createDing());
        solids.add(createPot1());
        solids.add(createDing1());
        solids.add(createDing2());
        solids.add(createDing3());
    }

    private void initializeGoObjects() {
        gameObjects.add(new Rupee(game, 131, 96));
        gameObjects.add(new Rupee(game, 148, 96));
        gameObjects.add(new Rupee(game, 196, 577));
        gameObjects.add(new Rupee(game, 1092, 857));
        gameObjects.add(new Rupee(game, 1036, 425));
        gameObjects.add(new Heart(game, 812, 57));
        gameObjects.add(new Heart(game, 52, 577));
        gameObjects.add(game.getLink());
        gameObjects.add(new Guard(game, 118, 971, Direction.UP));
        gameObjects.add(new Guard(game, 885, 968, Direction.UP));
        gameObjects.add(new Guard(game, 504, 564, Direction.DOWN));
        gameObjects.add(new BlueSoldier(game, 331, 762, Direction.RIGHT, 50));
        gameObjects.add(new BlueSoldier(game, 689, 762, Direction.LEFT, 50));
        gameObjects.add(new WhiteSoldier(game, 1038, 681, Direction.LEFT));
        gameObjects.add(new BlueSoldier(game, 883, 144, Direction.UP, 200));
        gameObjects.add(new BlueSoldier(game, 116, 144, Direction.UP, 200));
    }

    private Polygon createMiddenbovenmuur() {
        int[] xpos = {498, 352, 298, 217, 215, 147, 146, 145, 159, 160, 190, 193, 318, 320, 704, 704, 830, 831, 863,
                865, 877, 880, 672, 496};
        int[] ypos = {561, 563, 621, 621, 567, 561, 477, 447, 445, 175, 174, 152, 152, 167, 167, 152, 153, 174, 174,
                446, 445, 476, 528, 538};
        return new Polygon(xpos, ypos, ypos.length);
    }

    private Polygon createStukmuur() {
        int[] axpos = {185, 185, 160, 160, 146, 146};
        int[] aypos = {568, 627, 628, 591, 589, 564};
        return new Polygon(axpos, aypos, aypos.length);
    }

    private Polygon createStukmuur1() {
        int[] bxpos = {160, 184, 185, 215, 217, 334, 335, 321, 320, 193, 191, 159};
        int[] bypos = {666, 665, 717, 715, 665, 663, 697, 695, 757, 757, 743, 743};
        return new Polygon(bxpos, bypos, bypos.length);
    }

    private Polygon createStukmuur2() {
        int[] cxpos = {530, 530, 665, 728, 727, 690, 690, 702, 704, 832, 832, 865, 865, 877, 880, 537,};
        int[] cypos = {537, 557, 559, 622, 661, 662, 694, 694, 756, 756, 735, 734, 592, 591, 476, 508,};
        return new Polygon(cxpos, cypos, cypos.length);
    }

    private Polygon createRechtsonder() {
        int[] dxpos = {532, 534, 592, 591, 670, 702, 704, 832, 832, 864, 865, 927, 927, 951, 953, 999, 1001, 1024,
                1027, 1037, 1037, 997, 996, 957, 956, 921, 921, 871, 868, 843, 837, 837, 881, 839};
        int[] dypos = {997, 959, 958, 847, 846, 814, 791, 791, 813, 813, 853, 854, 813, 814, 781, 782, 813, 813, 822,
                822, 846, 844, 787, 786, 879, 882, 878, 877, 881, 881, 882, 981, 980, 997};
        return new Polygon(dxpos, dypos, dypos.length);
    }

    private Polygon createRechts() {
        int[] expos = {915, 915, 998, 1000, 1112, 1110, 1074, 1073, 1085, 1086, 1075, 1074, 1108, 1109, 1045, 1047,
                983, 982, 985, 978, 804, 805, 806, 874, 873, 833, 832, 704, 704, 576, 574, 562, 561, 544, 543, 530,
                529, 533, 1007, 1210, 1210};
        int[] eypos = {996, 980, 981, 879, 876, 851, 850, 821, 821, 656, 655, 627, 624, 569, 565, 425, 422, 56, 50,
                45, 48, 72, 76, 75, 102, 103, 118, 118, 103, 102, 110, 112, 96, 96, 110, 112, 95, 31, 32, 399, 993};
        return new Polygon(expos, eypos, eypos.length);
    }

    private Polygon createRechtsmidden() {
        int[] fxpos = {910, 910, 953, 953, 955, 1004, 1004, 958, 956, 995, 1034, 1038, 1040, 1026, 1023, 1000, 998,
                953, 949, 928, 927, 913, 913, 926, 926, 914};
        int[] fypos = {102, 77, 77, 465, 469, 470, 570, 569, 691, 692, 626, 631, 653, 655, 661, 663, 694, 695, 662,
                661, 591, 591, 447, 445, 103, 103};
        return new Polygon(fxpos, fypos, fypos.length);
    }

    private Polygon createLinksonder() {
        int[] gxpos = {494, 496, 430, 432, 433, 352, 321, 321, 192, 191, 159, 159, 145, 146, 154, 177, 182, 182,
                182, 144};
        int[] gypos = {997, 958, 956, 950, 845, 844, 812, 791, 792, 814, 816, 852, 854, 876, 878, 879, 882, 952,
                982, 983};
        return new Polygon(gxpos, gypos, gypos.length);
    }

    private Polygon createLinks() {
        int[] hxpos = {497, 495, 494, 481, 479, 464, 462, 450, 448, 319, 319, 193, 190, 177, 177, 111, 110, 97, 96,
                110, 111, 96, 97, 111, 112, 70, 69, 41, 41, 116, 17, 18, 496};
        int[] hypos = {100, 100, 110, 110, 102, 100, 111, 111, 99, 98, 115, 117, 108, 104, 91, 90, 101, 102, 445, 446,
                590, 591, 853, 853, 875, 876, 567, 568, 980, 981, 994, 42, 31};
        return new Polygon(hxpos, hypos, hypos.length);
    }

    private Polygon createZuil() {
        int[] ixpos = {545, 558, 546};
        int[] iypos = {928, 928, 936};
        return new Polygon(ixpos, iypos, iypos.length);
    }

    private Polygon createZuil1() {
        int[] jxpos = {545, 547, 557, 557,};
        int[] jypos = {896, 903, 904, 897,};
        return new Polygon(jxpos, jypos, jypos.length);
    }

    private Polygon createZuil2() {
        int[] kxpos = {547, 547, 556, 557,};
        int[] kypos = {864, 871, 871, 865,};
        return new Polygon(kxpos, kypos, kypos.length);
    }

    private Polygon createZuil3() {
        int[] lxpos = {547, 556, 556, 546,};
        int[] lypos = {833, 833, 838, 840,};
        return new Polygon(lxpos, lypos, lypos.length);
    }

    private Polygon createZuil4() {
        int[] mxpos = {466, 476, 477, 466, 466,};
        int[] mypos = {833, 832, 840, 840, 832,};
        return new Polygon(mxpos, mypos, mypos.length);
    }

    private Polygon createZuil5() {
        int[] nxpos = {466, 476, 476, 468, 467,};
        int[] nypos = {864, 865, 869, 870, 865,};
        return new Polygon(nxpos, nypos, nypos.length);
    }

    private Polygon createZuil6() {
        int[] oxpos = {466, 476, 477, 466,};
        int[] oypos = {896, 896, 902, 904,};
        return new Polygon(oxpos, oypos, oypos.length);
    }

    private Polygon createZuil7() {
        int[] pxpos = {466, 477, 477, 467,};
        int[] pypos = {928, 929, 935, 935,};
        return new Polygon(pxpos, pypos, pypos.length);
    }

    private Polygon createMidden() {
        int[] qxpos = {655, 625, 623, 560, 556, 531, 529, 649, 651, 657, 656,};
        int[] qypos = {693, 693, 623, 622, 652, 652, 597, 597, 665, 670, 693,};
        return new Polygon(qxpos, qypos, qypos.length);
    }

    private Polygon createMidden1() {
        int[] rxpos = {368, 369, 399, 401, 463, 466, 492, 496, 494, 372, 371, 366,};
        int[] rypos = {672, 694, 693, 623, 622, 649, 649, 599, 595, 595, 666, 670,};
        return new Polygon(rxpos, rypos, rypos.length);
    }

    private Polygon createPot() {
        int[] sxpos = {434, 461, 462, 451, 452, 428, 428, 418, 417, 430, 433,};
        int[] sypos = {679, 680, 735, 733, 705, 705, 733, 733, 706, 704, 676,};
        return new Polygon(sxpos, sypos, sypos.length);
    }

    private Polygon createDing() {
        int[] txpos = {385, 394, 393, 384,};
        int[] typos = {717, 718, 735, 735,};
        return new Polygon(txpos, typos, typos.length);
    }

    private Polygon createPot1() {
        int[] uxpos = {562, 562, 570, 572, 591, 594, 595, 605, 605, 596, 589, 590,};
        int[] uypos = {679, 732, 731, 701, 701, 705, 733, 733, 710, 708, 700, 675,};
        return new Polygon(uxpos, uypos, uypos.length);
    }

    private Polygon createDing1() {
        int[] vxpos = {628, 628, 636, 637,};
        int[] vypos = {719, 733, 733, 721,};
        return new Polygon(vxpos, vypos, vypos.length);
    }

    private Polygon createDing2() {
        int[] wxpos = {481, 494, 494, 482,};
        int[] wypos = {781, 781, 801, 803,};
        return new Polygon(wxpos, wypos, wypos.length);
    }

    private Polygon createDing3() {
        int[] xxpos = {531, 542, 542, 531,};
        int[] xypos = {783, 783, 803, 803,};
        return new Polygon(xxpos, xypos, xypos.length);
    }
}
