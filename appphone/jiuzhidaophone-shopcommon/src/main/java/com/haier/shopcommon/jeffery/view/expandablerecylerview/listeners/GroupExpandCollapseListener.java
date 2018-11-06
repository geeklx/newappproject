package com.haier.shopcommon.jeffery.view.expandablerecylerview.listeners;


import com.haier.shopcommon.jeffery.view.expandablerecylerview.models.ExpandableGroup;

/**
 * group的折叠展开监听接口
 * @author Jeffery Leng
 * @date   2018/6/28
 * @email  JefferyLeng@guider.cc
 */
public interface GroupExpandCollapseListener {

  /**
   * Called when a group is expanded
   * @param group the {@link ExpandableGroup} being expanded
   */
  void onGroupExpanded(ExpandableGroup group);

  /**
   * Called when a group is collapsed
   * @param group the {@link ExpandableGroup} being collapsed
   */
  void onGroupCollapsed(ExpandableGroup group);
}
