package com.tikal.fleettracker.monolithic.service.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
	
	private final Map<String, WorkerRunnable> workers = new HashMap<>(); 

	
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
			handleConnection(clientSocket);
		}
		System.out.println("Server Stopped.");
	}

	private void handleConnection(final Socket clientSocket) {
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			final String line = in.readLine();
			final String imei = line.split(",")[1];
			WorkerRunnable workerRunnable = workers.get(imei);
			if(workerRunnable==null){
				workerRunnable = new WorkerRunnable(clientSocket,gpsReadingService);
				workers.put(imei,workerRunnable);
				new Thread(workerRunnable).start();
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
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
