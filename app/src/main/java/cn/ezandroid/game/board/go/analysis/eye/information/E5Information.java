/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT */
package cn.ezandroid.game.board.go.analysis.eye.information;

import cn.ezandroid.game.board.go.GoBoard;
import cn.ezandroid.game.board.go.elements.eye.IGoEye;

/**
 * 包含4种不同类型的5空间眼位
 *
 * @author Barry Becker
 */
public class E5Information extends AbstractEyeSubtypeInformation {

    public enum Eye5Type {
        E11222, E11123, E11114, E12223
    }

    private Eye5Type mE5Type;

    E5Information(String subTypeDesc) {
        mE5Type = Eye5Type.valueOf(subTypeDesc);
        switch (mE5Type) {
            case E11222:
                initialize(true, 5);
                break;
            case E11123:
                initialize(false, 5, new float[]{3.04f, 2.04f}, new float[]{1.02f});
                break;
            case E11114:
                initialize(false, 5, new float[]{4.04f});
                break;
            case E12223:
                initialize(false, 5);
                break;
        }
    }

    @Override
    public EyeStatus determineStatus(IGoEye eye, GoBoard board) {
        EyeNeighborMap nbrMap = new EyeNeighborMap(eye);
        switch (mE5Type) {
            case E11222:
                return handleAliveOrAliveInAtari(eye, board);
            case E11123:
                return handleVitalPointCases(nbrMap, eye, 2);
            case E11114:
                return handleVitalPointCases(nbrMap, eye, 1);
            case E12223:
                return EyeStatus.UNSETTLED;
        }
        return EyeStatus.NAKADE; // never reached
    }

    @Override
    public String getTypeName() {
        return mE5Type.toString();
    }
}