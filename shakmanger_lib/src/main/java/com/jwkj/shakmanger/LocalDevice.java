package com.jwkj.shakmanger;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 设备对象
 * Created by hdl on 2017/4/11.
 */

public class LocalDevice implements Serializable, Comparable<LocalDevice> {
    /**
     * 设备id
     */
    private String id;
    /**
     * 设备的IP
     */
    private String IP;
    /**
     * 设备名字
     */
    private String name;
    /**
     * 设备的版本
     */
    private int version;
    /**
     * 设备是否有密码的标记
     */
    private int flag = 1;
    /**
     * 固件版本信息
     */
    private int rtspflag;
    /**
     * 设备类型
     */
    private int type = 0;
    /**
     * 设备子类型
     */
    private int subType;
    /**
     * 是否发现新id
     */
    private boolean isFoundNewId;
    /**
     * 新的设备id，isFoundNewId=true时才有值
     */
    private int contactNewId;
    /**
     * 本地p2p端口
     */
    private int localP2PPort;
    /**
     * 本地p2p的ip地址
     */
    private int localP2PRes;



    public LocalDevice() {
    }

    public String getId() {
        return "" + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIP() {
        return "" + IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return "" + ((rtspflag >> 4) & 0x1);
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getRtspflag() {
        return (rtspflag >> 2) & 1;
    }

    public void setRtspflag(int rtspflag) {
        this.rtspflag = rtspflag;
    }

    public int getSubType() {
        return subType;
    }

    public boolean isFoundNewId() {
        return isFoundNewId;
    }

    public void setFoundNewId(boolean foundNewId) {
        isFoundNewId = foundNewId;
    }

    public int getContactNewId() {
        return contactNewId;
    }

    public void setContactNewId(int contactNewId) {
        this.contactNewId = contactNewId;
    }

    public int getLocalP2PPort() {
        return localP2PPort;
    }

    public void setLocalP2PPort(int localP2PPort) {
        this.localP2PPort = localP2PPort;
    }

    public int getLocalP2PRes() {
        return localP2PRes;
    }

    public void setLocalP2PRes(int localP2PRes) {
        this.localP2PRes = localP2PRes;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "LocalDevice{" +
                "id='" + id + '\'' +
                ", IP='" + IP + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", flag=" + flag +
                ", rtspflag=" + rtspflag +
                ", type=" + type +
                ", subType=" + subType +
                ", isFoundNewId=" + isFoundNewId +
                ", contactNewId=" + contactNewId +
                ", localP2PPort=" + localP2PPort +
                ", localP2PRes=" + localP2PRes +
                '}';
    }

    @Override
    public int compareTo(@NonNull LocalDevice o) {
        return id.compareTo(o.getId());
    }
}
