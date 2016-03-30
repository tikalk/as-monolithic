package com.tikal.fleettracker.monolithic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.tikal.fleettracker.monolithic.domain.entity.Device;
import com.tikal.fleettracker.monolithic.domain.entity.Guardian;
import com.tikal.fleettracker.monolithic.domain.entity.Vehicle;
import com.tikal.fleettracker.monolithic.repository.jpa.DeviceRepository;
import com.tikal.fleettracker.monolithic.repository.jpa.GuardianRepository;
import com.tikal.fleettracker.monolithic.repository.jpa.VehicleRepository;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(final GuardianRepository guardianRepository,final VehicleRepository vehicleRepository,final DeviceRepository deviceRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(final String... args) throws Exception {
				populateGuardian1(guardianRepository, vehicleRepository, deviceRepository);
				populateGuardian2(guardianRepository, vehicleRepository, deviceRepository);
			}
		};
	}
	
	
	private void populateGuardian1(final GuardianRepository guardianRepository,
			final VehicleRepository vehicleRepository, final DeviceRepository deviceRepository) {
		final Vehicle a1 = vehicleRepository.save(new Vehicle("111-aaa","Mazda3"));
		final Vehicle b1 = vehicleRepository.save(new Vehicle("111-bbb","Mazda6"));
		final Vehicle c1 = vehicleRepository.save(new Vehicle("111-ccc","Volvo"));				
		final Vehicle d1 = vehicleRepository.save(new Vehicle("111-ddd","BMW"));
		final Vehicle e1 = vehicleRepository.save(new Vehicle("111-eee","Mercedes"));
		final Vehicle f1 = vehicleRepository.save(new Vehicle("111-fff","Audi"));
		final Vehicle g1 = vehicleRepository.save(new Vehicle("111-ggg","Fiat"));
		final Vehicle h1 = vehicleRepository.save(new Vehicle("111-hhh","Renaut"));
		final Vehicle i1 = vehicleRepository.save(new Vehicle("111-iii","Porche"));
		final Vehicle j1 = vehicleRepository.save(new Vehicle("111-jjj","Lancia"));				
		
		deviceRepository.save(new Device("1131", a1));
		deviceRepository.save(new Device("1277", b1));
		deviceRepository.save(new Device("8662", c1));				
		deviceRepository.save(new Device("2560", d1));
		deviceRepository.save(new Device("2669", e1));
		deviceRepository.save(new Device("3015", f1));
		deviceRepository.save(new Device("3557", g1));
		deviceRepository.save(new Device("3579", h1));
		deviceRepository.save(new Device("366", i1));
		deviceRepository.save(new Device("3781", j1));
		
		guardianRepository.save(new Guardian("g1", "g1","yanaif@gmail.com")
				.addVehicle(a1).addVehicle(b1).addVehicle(c1)
				.addVehicle(d1).addVehicle(e1).addVehicle(f1)
				.addVehicle(g1).addVehicle(h1).addVehicle(i1).addVehicle(j1));
	}
	
	
	private void populateGuardian2(final GuardianRepository guardianRepository,
			final VehicleRepository vehicleRepository, final DeviceRepository deviceRepository) {
		final Vehicle a2 = vehicleRepository.save(new Vehicle("222-aaa","Mazda3"));
		final Vehicle b2 = vehicleRepository.save(new Vehicle("222-bbb","Mazda6"));
		final Vehicle c2 = vehicleRepository.save(new Vehicle("222-ccc","Volvo"));				
		final Vehicle d2 = vehicleRepository.save(new Vehicle("222-ddd","BMW"));
		final Vehicle e2 = vehicleRepository.save(new Vehicle("222-eee","Mercedes"));
		final Vehicle f2 = vehicleRepository.save(new Vehicle("222-fff","Audi"));
		final Vehicle g2 = vehicleRepository.save(new Vehicle("222-ggg","Fiat"));
		final Vehicle h2 = vehicleRepository.save(new Vehicle("222-hhh","Renaut"));
		final Vehicle i2 = vehicleRepository.save(new Vehicle("222-iii","Porche"));
		final Vehicle j2 = vehicleRepository.save(new Vehicle("222-jjj","Lancia"));				
		
		deviceRepository.save(new Device("4798", a2));
		deviceRepository.save(new Device("5075", b2));
		deviceRepository.save(new Device("5099", c2));				
		deviceRepository.save(new Device("5860", d2));
		deviceRepository.save(new Device("6275", e2));
		deviceRepository.save(new Device("6656", f2));
		deviceRepository.save(new Device("6665", g2));
		deviceRepository.save(new Device("7105", h2));
		deviceRepository.save(new Device("7146", i2));
		deviceRepository.save(new Device("8179", j2));
		
		guardianRepository.save(new Guardian("g2", "g2","yanaif@gmail.com")
				.addVehicle(a2).addVehicle(b2).addVehicle(c2)
				.addVehicle(d2).addVehicle(e2).addVehicle(f2)
				.addVehicle(g2).addVehicle(h2).addVehicle(i2).addVehicle(j2));
	}
	
}
