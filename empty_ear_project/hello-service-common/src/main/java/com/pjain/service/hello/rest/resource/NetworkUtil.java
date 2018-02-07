package com.pjain.service.hello.rest.resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkUtil {

    public static String getAddress() throws UnknownHostException, SocketException {
        for (final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces
                .hasMoreElements();) {
            final NetworkInterface cur = interfaces.nextElement();

            if (cur.isLoopback()) {
                continue;
            }
            // System.out.println("interface " + cur.getName());
            for (final InterfaceAddress addr : cur.getInterfaceAddresses()) {
                final InetAddress inet_addr = addr.getAddress();
                if (!(inet_addr instanceof Inet4Address)) {
                    continue;
                }
                System.out.println(" address: " + inet_addr.getHostAddress() + "/" +
                        addr.getNetworkPrefixLength());
                        System.out.println(" broadcast address: " +
                        addr.getBroadcast().getHostAddress());
                return inet_addr.getHostAddress();
                
            } // for
        } // for
        return "localhost";
    }
    
    /*public static void main(String[] args) throws UnknownHostException, SocketException {
        System.out.println(getAddress());
    }*/

}
