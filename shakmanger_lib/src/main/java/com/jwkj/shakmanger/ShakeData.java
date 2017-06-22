package com.jwkj.shakmanger;


import com.hdl.udpsenderlib.UDPResult;

import java.nio.ByteBuffer;

/**
 * 搜索信息构造
 */
class ShakeData {
    private int cmd;// 指令类型
    private int error_code;// 错误码
    private int leftlength;// 结构体大小
    private int rightCount;// 结构体版本信息
    private int id;// 设备的id
    private int type;// 设备的类型
    private int flag;// 设备标记是否有密码---1有，0没有
    private int subType;// 设备子类型
    private boolean isFoundNewId;//是否发现新id
    private int contactNewId;//新的设备id，isFoundNewId=true时才有值
    private int localP2PPort;//本地p2p端口
    private int localP2PRes;//本地p2p的ip地址

    /**
     * 结果转换
     *
     * @param result
     * @return
     */
    public static LocalDevice getDevice(UDPResult result) {
        if (result == null) {
            return null;
        }
        LocalDevice data = new LocalDevice();
        ByteBuffer buffer = ByteBuffer.allocate(result.getResultData().length);
        buffer.put(result.getResultData());
        if (buffer.getInt(0) != Cmd.CMD_RECEIVE_DEVICE) {//cmd!=2表示不是搜索回应，此处忽略
            return null;
        }
        data.setIP(result.getIp());
        data.setId(String.valueOf(buffer.getInt(16)));
        data.setType(buffer.getInt(20));
        data.setFlag(buffer.getInt(24));
        data.setSubType(buffer.getInt(80));
        data.setFoundNewId(buffer.getInt(88) == 1 ? true : false);
        if (data.isFoundNewId()) {
            data.setContactNewId(buffer.getInt(92));//获取新的设备id
        }
        int anInt = buffer.getInt(84);
        short port = (short) anInt;
        short res = (short) (anInt >> 16);
        data.setLocalP2PPort(port);
        data.setLocalP2PRes(res);
        buffer.clear();
        return data;
    }


    /**
     * 局域网搜索设备的指令类
     */
    public static class Cmd {
        public static final int CMD_SHAKE_DEVICE = 1;//搜索的命令
        public static final int CMD_RECEIVE_DEVICE = 2;//接收设备的命令
        public static final int CMD_SHAKE_DEVICE_DEFAULT_PORT = 8899;//搜索设备的端口号
    }

    /**
     * 将ShakeData转换为字节数组输出
     *
     * @param data shakedata对象
     * @return 字节数组
     */
    public static byte[] getBytes(ShakeData data) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(data.getCmd());
        buffer.putInt(data.getError_code());
        buffer.putInt(data.getLeftlength());
        buffer.putInt(data.getId());
        buffer.putInt(data.getType());
        buffer.putInt(data.getFlag());
        buffer.putInt(data.getSubType());
        byte[] array = buffer.array();
        buffer.clear();// 清空缓冲区
        return array;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getLeftlength() {
        return leftlength;
    }

    public void setLeftlength(int leftlength) {
        this.leftlength = leftlength;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
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

    @Override
    public String toString() {
        return "ShakeData{" +
                "cmd=" + cmd +
                ", error_code=" + error_code +
                ", leftlength=" + leftlength +
                ", rightCount=" + rightCount +
                ", id=" + id +
                ", type=" + type +
                ", flag=" + flag +
                ", subType=" + subType +
                ", isFoundNewId=" + isFoundNewId +
                ", contactNewId=" + contactNewId +
                ", localP2PPort=" + localP2PPort +
                ", localP2PRes=" + localP2PRes +
                '}';
    }
}
