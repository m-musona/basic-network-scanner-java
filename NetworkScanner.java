import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NetworkScanner {
    public NetworkScanner(String subnet)
    {
        // Scan the network and get the list of active hosts with device info
        activeHosts = scanNetwork(subnet, start, end, timeout);
    }
    // Function to scan IP addresses in a given range
    private List<String> scanNetwork(String subnet, int start, int end, int timeout) {
        List<String> activeHosts = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            String ipAddress = subnet + "." + i;
            System.out.println("Pinging " + ipAddress);

            if (isHostReachable(ipAddress, timeout)) {
                String hostName = getHostName(ipAddress);
                String macAddress = getMacAddress(ipAddress);
                boolean isLoopbackAddress = isLoopbackAddress(ipAddress);
                boolean isMulticastAddress = isMulticastAddress(ipAddress);
                boolean isSiteLocalAddress = isSiteLocalAddress(ipAddress);
                String networkInterfaceName = getNetworkInterfaceName(ipAddress);
                int MTU = getMTU(ipAddress);
                
                System.out.println("Host Is Reachable");
                activeHosts.add(
                    ipAddress + 
                    "\nHostname: " + hostName + 
                    "\nMAC: " + macAddress +
                    "\nLoopback Address: " + isLoopbackAddress +
                    "\nMulticast Address: " + isMulticastAddress +
                    "\nSite Local Address: " + isSiteLocalAddress +
                    "\nNetwork Interface Name: " + networkInterfaceName +
                    "\nMTU: " + MTU
                    );
            }
        }
        return activeHosts;
    }
    
    // Function to check if a host is reachable
    private boolean isHostReachable(String ipAddress, int timeout) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isReachable(timeout);
        } catch (IOException e) {
            return false;
        }
    }

    // Function to get the hostname of the IP address
    private String getHostName(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.getCanonicalHostName();
        } catch (UnknownHostException e) {
            return "Unknown Host";
        }
    }

    // Function to get the MAC address of the IP address
    private String getMacAddress(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            NetworkInterface network = NetworkInterface.getByInetAddress(address);

            if (network == null) {
                return "MAC Address Not Available";
            }

            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                return "MAC Address Not Available";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error fetching MAC Address";
        }
    }

    private boolean isLoopbackAddress(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isLoopbackAddress();
        } catch (UnknownHostException e) {
            return false;
        }
    }

    private boolean isMulticastAddress(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isMulticastAddress();
        } catch (UnknownHostException e) {
            return false;
        }
    }

    private boolean isSiteLocalAddress(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isSiteLocalAddress();
        } catch (UnknownHostException e) {
            return false;
        }
    }

    private String getNetworkInterfaceName(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            if (network != null) {
                return network.getName();
            }
        } catch (Exception e) {
            return "Error fetching Network Interface";
        }
        return "Interface Not Available";
    }

    private int getMTU(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            if (network != null) {
                return network.getMTU();
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }
    public List<String> getActivHosts() { return activeHosts; }
    public void setStartAddress(int newStart) { start = newStart; }
    public void setEndAddress(int newEnd) { end = newEnd; }
    public void setTimeOut(int newTimeOut) { timeout = newTimeOut; }

    private List<String> activeHosts;
    private int start = 1;    // Starting IP address (e.g., 192.168.1.1)
    private int end = 254;    // Ending IP address (e.g., 192.168.1.254)
    private int timeout = 500; // Timeout in milliseconds
}
