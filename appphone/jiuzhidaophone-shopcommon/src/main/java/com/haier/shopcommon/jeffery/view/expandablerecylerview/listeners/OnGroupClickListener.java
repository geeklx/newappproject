package com.haier.shopcommon.jeffery.view.expandablerecylerview.listeners;

/**
 * group点击的listener
 * @author Jeffery Leng
 * @date   2018/6/28
 * @email  JefferyLeng@guider.cc
 */
public interface OnGroupClickListener {

  /**
   * @param flatPos the flat position (raw index within the list of visible items in the
   * RecyclerView of a GroupViewHolder)
   * @return false if click expanded group, true if click collapsed group
   */
  boolean onGroupClick(int flatPos);
}