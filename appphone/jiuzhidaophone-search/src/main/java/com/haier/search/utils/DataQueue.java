package com.haier.search.utils;

import java.util.LinkedList;

/**
 * 商城搜索的本地历史数据对象的数据队列
 *  默认容量为10，达到最大容量之后，队头出队列，新增对象自动进入队尾
 * Created by JefferyLeng on 2018/6/12.
 */
public class DataQueue<T> {

    private int capacity = 10;

    private LinkedList<T> dataQueue = new LinkedList<T>();

    public void DataQueue(int capacity) {
        this.capacity = capacity;
    }

    public void DataQueue() {
    }

    public void offer(T t) {
        if (targetHit(t)) {
            return;
        }
        if (dataQueue.size() >= capacity) {
            dataQueue.poll();
        }
        dataQueue.offer(t);
    }

    public void queue(T t) {
        if (targetHit(t)) {
            return;
        }

        if (dataQueue.size() >= capacity) {
            dataQueue.removeLast();
        }
        dataQueue.addFirst(t);
    }

    public boolean targetHit(T t) {
        return dataQueue.contains(t);
    }

    public void clearData() {
        dataQueue.clear();
    }

    public void recoverData(LinkedList<T> linkedList) {
        dataQueue.clear();
        dataQueue.addAll(linkedList);
    }

    public LinkedList<T> getDataQueue() {
        return dataQueue;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getQueueSize() {
        return dataQueue.size();
    }
}
