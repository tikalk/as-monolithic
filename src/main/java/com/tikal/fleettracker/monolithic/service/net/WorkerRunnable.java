package com.tikal.fleettracker.monolithic.service.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.tikal.fleettracker.monolithic.service.GPSReadingService;

public class WorkerRunnable implements Runnable {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerRunnable.class);


	private final Socket clientSocket;
	private final GPSReadingService gpsReadingService;

	public WorkerRunnable(final Socket clientSocket, final GPSReadingService gpsReadingService) {
		this.clientSocket = clientSocket;
		this.gpsReadingService=gpsReadingService;
	}

	
	@Override
	public void run() {
		try {
			final InputStream input = clientSocket.getInputStream();
			final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			gpsReadingService.saveGPSReading(in.readLine());
			final OutputStream output = clientSocket.getOutputStream();
			output.close();
			in.close();
			input.close();
//			System.out.println("Request processed: " + time);
		} catch (final IOException e) {
			logger.error("Failed to apply run",e);
		}
	}
}