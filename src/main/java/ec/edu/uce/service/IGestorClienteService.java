package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;


import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;

public interface IGestorClienteService {

	List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo);

	void reservarVehiculo(String placa, String cedulaCliente, LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	void registrarCliente(Cliente cliente);

}
