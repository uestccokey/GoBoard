/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT */
package cn.ezandroid.game.board.go.analysis.eye;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.ezandroid.game.board.go.analysis.eye.information.EyeInformation;
import cn.ezandroid.game.board.go.analysis.eye.information.EyeType;
import cn.ezandroid.game.board.go.elements.eye.IGoEye;
import cn.ezandroid.game.board.go.elements.position.GoBoardPosition;
import cn.ezandroid.game.board.go.elements.position.GoBoardPositionSet;

/**
 * 大眼分析器
 * <p>
 * 用来对眼位空间在[4,7]之间的大眼分类
 *
 * @author Barry Becker
 */
class BigEyeAnalyzer {

    // 眼位空间
    private GoBoardPositionSet mSpaces;

    BigEyeAnalyzer(IGoEye eye) {
        mSpaces = eye.getMembers();
        int size = mSpaces.size();
        assert (size > 3 && size < 8);
    }

    /**
     * For some eyes (like big eyes) there are one or more key points that will make a single eye if
     * the opponent plays first (or first and second if 2 key points), or 2 eyes if you play first.
     * We refer to the paper "When One Eye is Sufficient: A Static Classification"
     * to classify the different eye types based solely on eye-point neighbors.
     * <p>
     * The pattern formed by the sorted list of neighbor counts uniquely determines the type.
     *
     * @return the eye type determined based on the properties and neighbors of the positions in the mSpaces list.
     */
    EyeInformation determineEyeInformation() {
        List<Integer> counts = new ArrayList<>(7);

        for (GoBoardPosition space : mSpaces) {
            counts.add(getNumEyeNobiNeighbors(space));
        }
        Collections.sort(counts);

        return getEyeInformation(counts);
    }

    private EyeInformation getEyeInformation(List<Integer> counts) {
        StringBuilder bldr = new StringBuilder("E");
        for (int num : counts) {
            bldr.append(num);
        }
        EyeType type = EyeType.valueOf("E" + counts.size());

        return type.getInformation(bldr.toString());
    }

    /**
     * @param space eye space to check
     * @return number of eye-space nobi neighbors.
     * these neighbors may either be blanks or dead stones of the opponent
     */
    private int getNumEyeNobiNeighbors(GoBoardPosition space) {
        int numNbrs = 0;
        for (GoBoardPosition eyeSpace : mSpaces) {

            if (space.isNeighbor(eyeSpace))
                numNbrs++;
        }
        return numNbrs;
    }
}
