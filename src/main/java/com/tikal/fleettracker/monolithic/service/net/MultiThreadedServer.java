package com.tikal.fleettracker.monolithic.service.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tikal.fleettracker.monolithic.service.GPSReadingService;

@Component
public class MultiThreadedServer {

	@Value("${serverPort:4100}")
	private int serverPort;
	
	@Autowired
	private GPSReadingService gpsReadingService;
	private ServerSocket serverSocket;
	private boolean isStopped;

	
	@PostConstruct
	private void init(){
		new Thread(()->run()).start();
	}
	
	public void run() {
		openServerSocket();
		while (!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (final IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			new Thread(new WorkerRunnable(clientSocket,gpsReadingService)).start();
		}
		System.out.println("Server Stopped.");
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (final IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (final IOException e) {
			throw new RuntimeException("Cannot open port 8080", e);
		}
	}

}
