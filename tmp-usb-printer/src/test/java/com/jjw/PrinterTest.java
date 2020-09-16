package com.jjw;

import org.junit.Test;

import javax.usb.UsbPipe;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PrinterTest {

    @Test
    public void up358() throws Exception{

        InputStream is = PrinterTest.class.getClassLoader().getResourceAsStream("instruction.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;
        while((length = is.read(buffer)) != -1){
            baos.write(buffer,0,length);
        }

        String base64Str = baos.toString("UTF-8");

        byte[] instructionByte = Base64Util.decodeForByte(base64Str);

        System.out.println("16进制指令：" + HexUtil.byte2Hex(instructionByte));

        UsbUtil usbUtil = new UsbUtil((short)0x6868,(short)0x0200);
        UsbPipe sendUsbPipe = usbUtil.useUsb();

        UsbUtil.sendMassge(sendUsbPipe,instructionByte);

    }

}
