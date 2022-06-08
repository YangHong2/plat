/*
package com.dhlk.light.factoryconstruction.test;

public class SendByte {

     private byte[] getbyte(){
         byte[] cmdByte = new byte[24];




         return null;
    }

    private byte[] getbyteHead(byte[] cmdByte){
        cmdByte.add(PAGE_HEAD0);
        Cmd.Add(PAGE_HEAD1);
        //协议
        Cmd.Add(PROTOCOL_VERSION);

        //寻址类型
        Cmd.Add(id_T);
        //组ID
        Cmd.Add(Gid);
        //ID
        for (int i = 0; i < 7; i++)
        {
            Cmd.Add(id[i]);
        }

        Cmd.Add(dev);
        Cmd.Add(cmd);

        //时间戳
        Cmd.Add((byte)(Timestamp >> 40));
        Cmd.Add((byte)(Timestamp >> 32));
        Cmd.Add((byte)(Timestamp >> 24));
        Cmd.Add((byte)(Timestamp >> 16));
        Cmd.Add((byte)(Timestamp >> 8));
        Cmd.Add((byte)(Timestamp >> 0));

        //预留
        Cmd.Add(res0);
        Cmd.Add(res1);
        Cmd.Add(res2);
        Cmd.Add(res3);
        return cmdByte;
    }
}
*/
