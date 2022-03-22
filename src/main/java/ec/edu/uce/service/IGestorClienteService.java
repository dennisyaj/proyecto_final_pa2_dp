package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;

@Service
public interface IGestorClienteService {

	List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo);

	void reservarVehiculo(String placa, String cedulaCliente, LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	void registrarCliente(Cliente cliente);

}
