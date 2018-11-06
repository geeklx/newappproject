package com.haier.shopcommon.jeffery.view.expandablerecylerview.listeners;

/**
 * 非group的展开这贴监听
 * @author Jeffery Leng
 * @date   2018/6/28
 * @email  JefferyLeng@guider.cc
 */
public interface ExpandCollapseListener {

  /**
   * Called when a group is expanded
   *
   * @param positionStart the flat position of the first child in the {@link ExpandableGroup}
   * @param itemCount the total number of children in the {@link ExpandableGroup}
   */
  void onGroupExpanded(int positionStart, int itemCount);

  /**
   * Called when a group is collapsed
   *
   * @param positionStart the flat position of the first child in the {@link ExpandableGroup}
   * @param itemCount the total number of children in the {@link ExpandableGroup}
   */
  void onGroupCollapsed(int positionStart, int itemCount);
}
