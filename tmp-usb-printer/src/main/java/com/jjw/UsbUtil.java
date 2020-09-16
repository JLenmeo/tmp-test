package com.jjw;

import javax.usb.*;
import java.util.List;

public class UsbUtil {

    private short idVendor;
    private short idProduct;

    public UsbUtil(short idVendor, short idProduct) {
        this.idVendor = idVendor;
        this.idProduct = idProduct;
    }

    public UsbPipe useUsb() throws Exception{
        UsbInterface iface = linkDevice();
        if(iface == null){
            return null;
        }

        UsbEndpoint receivedUsbEndpoint,sendUsbEndpoint;
        sendUsbEndpoint = (UsbEndpoint)iface.getUsbEndpoints().get(0);
        if (!sendUsbEndpoint.getUsbEndpointDescriptor().toString().contains("OUT")){
            receivedUsbEndpoint = sendUsbEndpoint;
            sendUsbEndpoint = (UsbEndpoint)iface.getUsbEndpoints().get(1);
        } else {
            receivedUsbEndpoint = (UsbEndpoint)iface.getUsbEndpoints().get(1);
        }

        //打开发送通道
        UsbPipe sendUsbPipe =  sendUsbEndpoint.getUsbPipe();
        if(sendUsbPipe.isOpen()){
            sendUsbPipe.close();
        }
        sendUsbPipe.open();

        //打开接收通道
        final UsbPipe receivedUsbPipe =  receivedUsbEndpoint.getUsbPipe();
        if (!receivedUsbPipe.isOpen()){
            receivedUsbPipe.open();
        }

        //启一个线程读数据
        new Thread(new Runnable(){

            @Override
            public void run() {

                try {
                    receivedMassge(receivedUsbPipe);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    receivedUsbPipe.abortAllSubmissions();
                    try {
                        receivedUsbPipe.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

        }).start();

        return sendUsbPipe;

    }

    public void receivedMassge(UsbPipe usbPipe) throws Exception{
        byte[] b = new byte[64];
        int length = 0;
        while (true){
            length = usbPipe.syncSubmit(b);//阻塞
            System.out.println("接收长度：" + length);
            for (int i = 0; i < length; i++) {
                System.out.print(Byte.toUnsignedInt(b[i])+" ");
            }
        }
    }

    public static void sendMassge(UsbPipe usbPipe,byte[] buff)  throws Exception{
        usbPipe.syncSubmit(buff);//阻塞
        //usbPipe.asyncSubmit(buff);//非阻塞
    }

    /**
     * 连接设备
     * @return
     * @throws Exception
     */
    public UsbInterface linkDevice() throws Exception{

        //去搜索特定的设备！需要用到设备的VID PID
        UsbDevice device = findDevice(UsbHostManager.getUsbServices().getRootUsbHub());

        if (device == null) {
            System.out.println("设备未找到！");
            return null;
        }

        UsbConfiguration configuration = device.getActiveUsbConfiguration();
        UsbInterface iface = null;
        if (configuration.getUsbInterfaces().size() > 0) {
            iface = configuration.getUsbInterface((byte) 0);
        } else {
            System.out.println("接口获取失败！");
            return null;
        }

        //claim连接必须打开claim。这里如果设备是连接状态我们把他关闭
        if (iface.isClaimed()){
            iface.release();

        }

        //再开启！
        iface.claim(new UsbInterfacePolicy()
        {
            @Override
            public boolean forceClaim(UsbInterface usbInterface)
            {
                return true;
            }
        });

        return iface;
    }

    /**
     * 搜索特定设备
     * @param hub
     * @return
     */
    public UsbDevice findDevice(UsbHub hub){
        UsbDevice device = null;

        List list = (List) hub.getAttachedUsbDevices();
        for (int i = 0;i < list.size();i++){
            device = (UsbDevice)list.get(i);
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            System.out.println(i + "___" + desc.idVendor() + "___" + desc.idProduct());

            if (desc.idVendor() == idVendor && desc.idProduct() == idProduct){
                return device;
            }

            if (device.isUsbHub()){
                device = findDevice((UsbHub) device);
                if (device != null){
                    return device;
                }
            }
        }

        return null;

    }

}
