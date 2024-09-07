# Network Scanner

This project is a simple network scanner implemented in Java. It scans a specified subnet and retrieves information about active hosts, such as their hostname, MAC address, network interface, and other device details.

## Features
- Pings IP addresses within a given subnet range to check their availability.
- Retrieves hostname, MAC address, and network interface details of reachable hosts.
- Detects if an IP address belongs to loopback, multicast, or site-local addresses.
- Provides information on the host’s Maximum Transmission Unit (MTU) and network interface name.

## Prerequisites
- JDK 8 or higher installed.
- Basic knowledge of subnets and IP addressing.

## How to Use

1. **Compile the code:**
   ```bash
   javac Main.java NetworkScanner.java

2. **Run the program:**
    ```bash
    java Main

3. **Enter the subnet:** 
    When prompted, enter the subnet you'd like to scan, for example, 192.168.1.

4. **Output:** 
    The program will scan all IP addresses in the range and display details of reachable hosts.

## Example
    `Enter Subnet (Example 192.168.8):
    192.168.1

    Pinging 192.168.1.1
    Host is reachable
    192.168.1.1
    Hostname: router.local
    MAC: 00-14-22-01-23-45
    Loopback Address: false
    Multicast Address: false
    Site Local Address: true
    Network Interface Name: eth0
    MTU: 1500`

## Configuration
You can adjust the following parameters in the NetworkScanner class:
- start: Starting IP address in the subnet range (default is 1).
- end: Ending IP address in the subnet range (default is 254).
-timeout: Timeout for the ping requests in milliseconds (default is 500 ms).

## Methods:
- getActiveHosts(): Returns a list of active hosts along with their device information.
- setStartAddress(int newStart): Change the starting IP address in the subnet range.
- setEndAddress(int newEnd): Change the ending IP address in the subnet range.
- setTimeOut(int newTimeOut): Set a new timeout value for ping requests.

## Limitations
The MAC address and network interface information may not always be available depending on the system configuration.
The scan might take longer for larger subnets or time out on slower networks.

## License
This project is licensed under the MIT License.