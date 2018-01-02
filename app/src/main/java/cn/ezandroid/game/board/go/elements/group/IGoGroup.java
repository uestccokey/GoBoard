/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT */
package cn.ezandroid.game.board.go.elements.group;

import cn.ezandroid.game.board.go.elements.IGoSet;
import cn.ezandroid.game.board.go.elements.position.GoBoardPosition;
import cn.ezandroid.game.board.go.elements.position.GoBoardPositionSet;
import cn.ezandroid.game.board.go.elements.string.GoStringSet;
import cn.ezandroid.game.board.go.elements.string.IGoString;
import cn.ezandroid.game.common.geometry.Box;

/**
 * Makes some unit tests much simpler if we create the tests to use this interface instead
 * of the full-blown GoGroup class.
 *
 * @author Barry Becker
 */
public interface IGoGroup extends IGoSet {

    void addMember(IGoString string);

    @Override
    GoStringSet getMembers();

    @Override
    boolean isOwnedByPlayer1();

    void addChangeListener(GroupChangeListener listener);

    int getNumStones();

    boolean containsStone(GoBoardPosition stone);

    void remove(IGoString string);

    GoBoardPositionSet getStones();

    void updateTerritory(float health);

    Box findBoundingBox();

    String toHtml();
}