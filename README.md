# Java Network Programming (Sockets)

## Description
A collection of client-server applications demonstrating fundamental network programming in Java using both TCP (Transmission Control Protocol) and UDP (User Datagram Protocol).

## Concepts Demonstrated
* **TCP Sockets:** Establishing reliable, connection-oriented communication using `ServerSocket` and `Socket`.
* **UDP Datagrams:** Sending and receiving connectionless packets using `DatagramSocket` and `DatagramPacket`.
* **Multithreading:** Handling multiple clients concurrently on a single server port using `Runnable` threads.
* **I/O Streams:** Using `DataInputStream`, `DataOutputStream`, and `FileOutputStream` to transmit strings and raw file bytes over a network.

## Included Programs
1. **TCP Vowel Counter:** Client sends strings; Server counts and returns the number of vowels.
2. **UDP Time Server:** Client requests time; Server returns the formatted local system time.
3. **Multi-threaded Time Server:** A robust UDP server capable of handling requests from multiple clients simultaneously using threads.
4. **TCP File Transfer:** A client sends a physical file over the network, and the server saves it to its local directory.
5. **UDP Chat:** A basic 2-way command-line chat application.

## How to Run
For any pair of programs, compile the files. Run the `Server` file first to open the port (`2222`), then run the corresponding `Client` file in a separate terminal or IDE console.
